/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.entities.ColorEntity;
import com.mycompany.spring_mvc_project_final.entities.SizeEntity;
import com.mycompany.spring_mvc_project_final.repository.SizeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class SizeService {
    
    @Autowired
    private SizeRepository sizeRepository;
    
    public List<SizeEntity> getSize(){
       return (List<SizeEntity>) sizeRepository.findAll();
    }
    
    public SizeEntity findById(int id){
        Optional<SizeEntity> optional = sizeRepository.findById(id);
                if (optional.isPresent()) {
            return optional.get();
        } else {
            return new SizeEntity();
        }
    }
    
//    public String findSizes(int sizeId){
//         String sizeName = sizeRepository.findBySizes(sizeId);
//         return sizeName;
//     }
    
    public int findSize(String size){
        return  sizeRepository.findById(size);
    }
}
