/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.entities.CategoryEntity;
import com.mycompany.spring_mvc_project_final.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<CategoryEntity> getListCategory(){
        return (List<CategoryEntity>) categoryRepository.findAll();
    }
    
    public List<CategoryEntity> getCategoryByName(String categoryName){
        return categoryRepository.findByNameContaining(categoryName);
    }
        
}
