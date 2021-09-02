/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.entities.FavoriteEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductDetailEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductEntity;
import com.mycompany.spring_mvc_project_final.repository.FavoriteRepository;
import com.mycompany.spring_mvc_project_final.repository.ProductDetailRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductService productSer;
    
    @Autowired
    private ProductDetailRepository detailRepository;

    public List<FavoriteEntity> getTop3Favorite() {
        List<Integer> ids = favoriteRepository.getTop3Favorite();
        if (ids != null && ids.size() > 0) {
//            for (Integer id : ids) {
//                
//            }   
        }
        return null;
    }

    public List<ProductEntity> getFavorite() {
        List<Integer> favs = favoriteRepository.getFavorite();
        List<ProductEntity> products = new ArrayList<>();
        
        int count = 0;
        for (Integer fav : favs) {
            
            
            ProductEntity product = productSer.findProductById(fav);
            List<ProductDetailEntity> details = detailRepository.findByProducts(product);
            
            product.setProductDetail(details);
            products.add(product);
            
            if (count == 3) {
                break;
            }
            count++;
        }
        return products;
    }
    
    
}
