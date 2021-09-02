/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */

@Entity
@Table(name = "vote")
public class VoteEntity implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    private int vote;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productVote;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    public VoteEntity(int id, int vote) {
        this.id = id;
        this.vote = vote;
    }

    public VoteEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public ProductEntity getProductVote() {
        return productVote;
    }

    public void setProductVote(ProductEntity productVote) {
        this.productVote = productVote;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }
    
    
}
