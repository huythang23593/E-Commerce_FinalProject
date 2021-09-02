/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Entity
@Table (name = "comment")
public class CommentEntity implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    private String content;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productCM;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity users;

    public ProductEntity getProductCM() {
        return productCM;
    }

    public void setProductCM(ProductEntity productCM) {
        this.productCM = productCM;
    }
    
    
    
    public CommentEntity() {
    }

    public CommentEntity(int id, String content, Date createDate) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserEntity getUsers() {
        return users;
    }

    public void setUsers(UserEntity users) {
        this.users = users;
    }
    
    
}
