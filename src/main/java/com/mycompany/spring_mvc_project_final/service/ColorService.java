/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.entities.ColorEntity;
import com.mycompany.spring_mvc_project_final.entities.ProductEntity;
import com.mycompany.spring_mvc_project_final.repository.ColorRepository;
import com.mycompany.spring_mvc_project_final.repository.ProductDetailRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ColorService {
    
    @Autowired
    private ColorRepository colorRepository;
    
    @Autowired
    private ProductDetailRepository productDetailRepository;
    
    public List<ColorEntity> getColor(){
       return (List<ColorEntity>) colorRepository.findAll();
    }
    
    public ColorEntity findColorById(int id){
        Optional<ColorEntity> optional = colorRepository.findById(id);
                if (optional.isPresent()) {
            return optional.get();
        } else {
            return new ColorEntity();
        }
    }
    
   
     public int findColor(String color){
         return  colorRepository.findById(color);
     }
}
