/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.repository;

import com.mycompany.spring_mvc_project_final.entities.SizeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Integer>{
    
  //  String findBySizes(int sizeId);
    
    @Query("Select id from SizeEntity Where sizes = ?1")
    int findById(String size);
}
