/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Table(name = "orders")
@Entity
public class OrdersEntity extends PersonalInfo implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "order_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;

    private double discount;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetail;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private UserEntity user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PaymentEntity> payment;

    public OrdersEntity() {
    }

    public OrdersEntity(int id, Date orderDate, double discount) {
        this.id = id;
        this.orderDate = orderDate;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<OrderDetailEntity> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailEntity> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PaymentEntity> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentEntity> payment) {
        this.payment = payment;
    }

    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFullname() {
        return super.getFullname(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "orderDate : " + orderDate + "\n"
                + "Address : " + getAddress() + "\n"
                + "Email : " + getEmail() + "\n"
                + "PhoneNumber : " + getPhoneNumber();
    }

}
