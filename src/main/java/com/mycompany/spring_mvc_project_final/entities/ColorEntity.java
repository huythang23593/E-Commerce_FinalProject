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
@Table(name = "color")
public class ColorEntity implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    private String color;
    
    @OneToMany(mappedBy = "color" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductDetailEntity> colorDetail;

    public ColorEntity() {
    }

    public ColorEntity(int id, String color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ProductDetailEntity> getColorDetail() {
        return colorDetail;
    }

    public void setColorDetail(List<ProductDetailEntity> colorDetail) {
        this.colorDetail = colorDetail;
    }
    
    
}
