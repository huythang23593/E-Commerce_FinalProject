/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.ProductDetailEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductEntity;
import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {

    @Query("Select p From ProductEntity p order by p.createDate DESC")
    List<ProductEntity> findNewProduct();

    @Query("Select p From ProductEntity p Where p.category.name = ?1")
    List<ProductEntity> findByCategory(String categoryName, org.springframework.data.domain.Pageable pageable);

    @Query("Select p From ProductEntity p Where p.name Like %?1% Or p.category.name Like %?2%")
    List<ProductEntity> findProductNameOrCategory(String name, String category,org.springframework.data.domain.Pageable pageable);

 //   @Query("Select p From ProductEntity p Where p.price = ?1")
  //  List<ProductEntity> findProductPrice(double price);

  //  List<ProductEntity> findByPrice(double price);

    @Query("Select count(*) From ProductEntity p")
    int countProduct();
    
     @Query("Select count(*) From ProductEntity p Where p.name Like %?1% Or p.category.name Like %?2%")
    int countProductSearch(String name, String category);

    @Query("Select p From ProductEntity p order by p.id ASC")
    List<ProductEntity> getProduct(org.springframework.data.domain.Pageable pageable);

  //  List<ProductEntity> findByPriceBetween(double price1, double price2);

    @Query(value = "select * "
            + "From product p "
            + "Join product_detail d "
            + "On p.id = d.product_id "
            + "Join image i "
            + "On p.id = i.id "
            + "Where d.price between ?1 and ?2", nativeQuery = true)
    List<ProductEntity> searchPrice(double price1, double price2);
    
    @Query(value = "select distinct p.id "
            + "From product p "
            + "Join product_detail d "
            + "On p.id = d.product_id "
            + "Join image i "
            + "On p.id = i.id "
            + "Where d.price between ?1 and ?2", nativeQuery = true)
    List<Integer> searchPrice2(double price1, double price2);

//     @Query("select distinct p.productDetail.color.id from ProductEntity p where p.id = ?1")
//    List<ProductEntity> searchColor(int productId);
}
