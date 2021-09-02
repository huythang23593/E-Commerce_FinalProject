/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.FavoriteEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductDetailEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface FavoriteRepository extends CrudRepository<FavoriteEntity, Integer> {

    @Query(value = "select product_id from favorite group by product_id", nativeQuery = true)
    List<Integer> getTop3Favorite();

//    @Query(value = "select p.name as nameProduct from product p join (select distinct fv.product_id from favorite as fv join (select  product_id, count(*) as count\n" +
//" from favorite group by product_id order by count(*) DESC limit 4) as favr on fv.product_id = favr.product_id)as pid on p.id = pid.product_id", nativeQuery = true)
//    List<String> getFavorite();
    @Query(value = "select product_id from favorite group by product_id order by count(1) DESC", nativeQuery = true)
    List<Integer> getFavorite();

}
