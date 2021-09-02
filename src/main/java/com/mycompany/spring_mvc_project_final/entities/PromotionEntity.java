/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import com.mycompany.spring_mvc_project_final.enums.PromotionStatus;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "promotions")
public class PromotionEntity {
    
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(length = 100)
    private String name;
    
    @Column(length = 500)
    private String description;
    
    private int discount;
    
    private String code;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;
    
    @Enumerated(EnumType.STRING)
    private PromotionStatus status = PromotionStatus.OPEN;
    
    @ManyToMany(mappedBy = "promotion",cascade = CascadeType.ALL)
    private List<ProductEntity> product;

    public List<ProductEntity> getProduct() {
        return product;
    }

    public void setProduct(List<ProductEntity> product) {
        this.product = product;
    }
    
    

    public PromotionEntity() {
    }

    public PromotionEntity(int id, String name, String description, int discount, String code, Date createDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.code = code;
        this.createDate = createDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PromotionStatus getStatus() {
        return status;
    }

    public void setStatus(PromotionStatus status) {
        this.status = status;
    }
    
    
}
