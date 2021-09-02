/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "size_all")
public class SizeEntity implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    private String sizes;
    
    @OneToMany(mappedBy = "size" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductDetailEntity> sizeDetail;

    public SizeEntity() {
    }

  
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return sizes;
    }

    public void setSize(String size) {
        this.sizes = size;
    }

    public List<ProductDetailEntity> getSizeDetail() {
        return sizeDetail;
    }

    public void setSizeDetail(List<ProductDetailEntity> sizeDetail) {
        this.sizeDetail = sizeDetail;
    }

   
    
    
}
