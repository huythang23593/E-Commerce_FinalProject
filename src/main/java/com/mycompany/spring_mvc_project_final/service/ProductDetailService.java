/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.entities.ColorEntity;
import com.mycompany.spring_mvc_project_final.entities.OrderDetailEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductDetailEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductEntity;
import com.mycompany.spring_mvc_project_final.entities.SizeEntity;
import com.mycompany.spring_mvc_project_final.repository.ProductDetailRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ProductDetailService {

    @Autowired
    private ProductDetailRepository detailRepository;

    @Autowired
    private ProductService productSer;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

    public List<ProductDetailEntity> getProDetailList() {
        return (List<ProductDetailEntity>) detailRepository.findAll();
    }

    public List<ProductDetailEntity> getNew() {
        return detailRepository.findNew();
    }

    public ProductDetailEntity findProductById(int id) {
        Optional<ProductDetailEntity> optional = detailRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return new ProductDetailEntity();
        }
    }

    public List<ProductDetailEntity> getProducts(ProductEntity product) {
        return detailRepository.findByProducts(product);
    }

    

    public List<ProductDetailEntity> searchProduct(String strSearch) {
        return detailRepository.searchProductNameOrCategory(strSearch, strSearch);
    }

    public List<ColorEntity> findColorDistin(int id) {
        List<ColorEntity> ce = new ArrayList<>();
        List<Integer> cli = detailRepository.searchColor(id);

        for (Integer c : cli) {
            ColorEntity color = colorService.findColorById(c);
            List<ProductDetailEntity> details = detailRepository.findByColor(color);

            color.setColorDetail(details);
            ce.add(color);
        }
        return ce;
    }

    public List<SizeEntity> findSizeDistin(int id) {
        List<SizeEntity> se = new ArrayList<>();
        List<Integer> sizeid = detailRepository.searchSize(id);

        for (Integer s : sizeid) {
            SizeEntity size = sizeService.findById(s);
            List<ProductDetailEntity> details = detailRepository.findBySize(size);

            size.setSizeDetail(details);
            se.add(size);
        }
        return se;
    }

    public void saveProductdetail(ProductDetailEntity detailEntity) {
        detailRepository.save(detailEntity);
    }

    public List<ProductDetailEntity> getProductByCategory(String categoryName, int page) {
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, 9);
        return detailRepository.findByCategoryByName(categoryName, pageable);
    }
    
    public List<ProductDetailEntity> getProductLimit(int page) {
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, 9);
        return detailRepository.getProductLimit(pageable);
    }
    
    public List<ProductDetailEntity> findProductDetailId() {
//        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, 9);
        List<Integer> lst = detailRepository.findProductDetailId();
        
        List<ProductDetailEntity> pde = new ArrayList<>();
        for(Integer l : lst){
            ProductDetailEntity detail = findProductById(l);
            pde.add(detail);
        }
        return pde;
    }
    
    
    public int getCount() {
        int total = detailRepository.countProductDetail();
        int totalPage = total / 9;
        if (total % 2 == 0) {
            totalPage++;
        }
        return totalPage;
    }
    
    public List<ProductDetailEntity> searchPrice(double price1,double price2){
        return detailRepository.searchPrice3(price1,price2);
    }
}
