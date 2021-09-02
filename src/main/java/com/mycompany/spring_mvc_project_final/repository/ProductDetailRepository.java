/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.ColorEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductDetailEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductEntity;
import com.mycompany.spring_mvc_project_final.entities.SizeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ProductDetailRepository extends CrudRepository<ProductDetailEntity, Integer> {

    @Query("Select pd From ProductDetailEntity pd order by pd.products.createDate DESC")
    List<ProductDetailEntity> findNew();

    List<ProductDetailEntity> findByProducts(ProductEntity product);

    List<ProductDetailEntity> findByColor(ColorEntity color);

    List<ProductDetailEntity> findBySize(SizeEntity size);

    @Query("Select pd From ProductDetailEntity pd Where pd.products.category.name = ?1")
    List<ProductDetailEntity> findByCategoryByName(String categoryName, org.springframework.data.domain.Pageable pageable);

    @Query("Select pd From ProductDetailEntity pd Where pd.products.name Like %?1% Or pd.products.category.name Like %?2%")
    List<ProductDetailEntity> searchProductNameOrCategory(String name, String category);

    @Query("Select pd From ProductDetailEntity pd order by pd.products.id ASC")
    List<ProductDetailEntity> getProductLimit(org.springframework.data.domain.Pageable pageable);

    @Query("select distinct pd.color.id from ProductDetailEntity pd where pd.products.id = ?1")
    List<Integer> searchColor(int productId);

    @Query("select distinct pd.size.id from ProductDetailEntity pd where pd.products.id = ?1")
    List<Integer> searchSize(int productId);

    @Query("select distinct pd.products.id from ProductDetailEntity pd")
    List<Integer> findProductDetailId();

    @Query("Select count(*) From ProductDetailEntity")
    int countProductDetail();
    
    @Query("Select pd.id From ProductDetailEntity pd Where pd.products.id = ?1 And pd.color.id = ?2 And pd.size.id = ?3")
    int ProductDetailId(int productId,int colorId,int sizeId);
    
     @Query("Select pd From ProductDetailEntity pd Where pd.price = ?1")
    List<ProductDetailEntity> searchPrice(double price);
    
    @Query("Select distinct pd.price From ProductDetailEntity pd Where pd.price between ?1 and ?2")
    List<ProductDetailEntity> searchPrice3(double price1,double price2);
    
    
}
