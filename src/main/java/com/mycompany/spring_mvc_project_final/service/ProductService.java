/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.entities.CategoryEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductDetailEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductEntity;
import com.mycompany.spring_mvc_project_final.repository.CategoryRepository;
import com.mycompany.spring_mvc_project_final.repository.FavoriteRepository;
import com.mycompany.spring_mvc_project_final.repository.ProductDetailRepository;
import com.mycompany.spring_mvc_project_final.repository.ProductRepository;
import com.mysql.cj.Session;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductDetailRepository detailRepository;

    public List<ProductEntity> getProductList() {

        return (List<ProductEntity>) productRepository.findAll();
    }

    public List<ProductEntity> getLimit(int page) {
        org.springframework.data.domain.Pageable a = PageRequest.of(page, 9);
        List<ProductEntity> listProduct = productRepository.getProduct(a);
        return listProduct;
    }

    public int getCount() {
        int total = productRepository.countProduct();
        int totalPage = total / 9;
        if (total % 2 == 0) {
            totalPage++;
        }
        return totalPage;
    }
    
    

    public ProductEntity findProductById(int id) {
        Optional<ProductEntity> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return new ProductEntity();
        }
    }

    public List<ProductEntity> getNewProduct() {
        return productRepository.findNewProduct();
    }

    public List<ProductEntity> getTop3Favorite() {

        List<Integer> ids = favoriteRepository.getTop3Favorite();
        if (ids != null && ids.size() > 0) {
//            for (Integer id : ids) { 
//            }
        }
        return null;
    }

    public List<ProductEntity> getProductByCategory(String categoryName) {

        List<ProductEntity> listCate = (List<ProductEntity>) productRepository.findAll();
        List<ProductEntity> listCategory = new ArrayList<>();

        if (listCate != null && listCategory.size() > 0) {
            for (ProductEntity list : listCate) {
                if (list.getCategory().getName().equalsIgnoreCase(categoryName)) {
                    listCategory.add(list);
                }
            }
        }
        System.out.println(listCategory);

        return listCategory;
    }

    public List<ProductEntity> getProductByCate(String categoryName, int page) {
        org.springframework.data.domain.Pageable a = PageRequest.of(page, 9);
        return productRepository.findByCategory(categoryName, a);
    }
    
    public int getCount1(String s) {
        int total = productRepository.countProductSearch(s, s);
        int totalPage = total / 9;
        if (total % 2 == 0) {
            totalPage++;
        }
        return totalPage;
    }
    public List<ProductEntity> searchProduct(String strSearch,int page) {
        org.springframework.data.domain.Pageable a = PageRequest.of(page, 9);
        return productRepository.findProductNameOrCategory(strSearch, strSearch,a);
    }
    
//    public List<ProductEntity> searchNameOrPrice(double price) {
//        return productRepository.findProductPrice(price);
//    }
//
//    public List<ProductEntity> searchProductByPrice(double price1,double price2) {
//        return productRepository.findByPriceBetween(price1, price2);
//    }
    
    public List<ProductEntity> searchPrice(double price1,double price2) {
        return productRepository.searchPrice(price1, price2);
    }
    
    public List<ProductEntity> searchPrice2(double price1,double price2){
        List<Integer> list = productRepository.searchPrice2(price1, price2);
        List<ProductEntity> products = new ArrayList<>();
        
        for(Integer l : list){
            ProductEntity product = findProductById(l);
            List<ProductDetailEntity> details = detailRepository.findByProducts(product);
            
            product.setProductDetail(details);
            products.add(product);
        }
        return products;
    }

//     public List<ProductEntity> searchColors(int id){
//         return productRepository.searchColor(id);
//     }
//    
}
