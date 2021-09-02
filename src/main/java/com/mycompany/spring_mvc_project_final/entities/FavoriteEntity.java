/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "favorite")
public class FavoriteEntity implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productFavorite;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    
    

    public FavoriteEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductEntity getProductFavorite() {
        return productFavorite;
    }

    public void setProductFavorite(ProductEntity productFavorite) {
        this.productFavorite = productFavorite;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

}
