/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import com.mycompany.spring_mvc_project_final.enums.ProductStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @NotNull
    @Column(length = 100)
    private String name;
    
//    private double price;

    @Column(length = 500)
    private String description;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.IN_STOCK;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ImageEntity image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_promotion",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id"))
    private List<PromotionEntity> promotion;

    @OneToMany(mappedBy = "productCM", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CommentEntity> comment;

    @OneToMany(mappedBy = "productVote", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VoteEntity> vote;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private ManufacturerEntity manufacturer;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductDetailEntity> productDetail;

    @OneToMany(mappedBy = "productOrderDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetail;

    @OneToMany(mappedBy = "productFavorite", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FavoriteEntity> favorite;

    public ProductEntity() {
    }

    public ProductEntity(int id, String name, String description, Date createDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<PromotionEntity> getPromotion() {
        return promotion;
    }

    public void setPromotion(List<PromotionEntity> promotion) {
        this.promotion = promotion;
    }

    public List<CommentEntity> getComment() {
        return comment;
    }

    public void setComment(List<CommentEntity> comment) {
        this.comment = comment;
    }

    public List<VoteEntity> getVote() {
        return vote;
    }

    public void setVote(List<VoteEntity> vote) {
        this.vote = vote;
    }

    public ManufacturerEntity getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerEntity manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<ProductDetailEntity> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(List<ProductDetailEntity> productDetail) {
        this.productDetail = productDetail;
    }



    public List<OrderDetailEntity> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailEntity> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public List<FavoriteEntity> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<FavoriteEntity> favorite) {
        this.favorite = favorite;
    }

//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }

    public ProductEntity(List<OrderDetailEntity> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public ProductEntity setProductDetail(int colorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ProductEntity getProductDetail(int colorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        
    
}
