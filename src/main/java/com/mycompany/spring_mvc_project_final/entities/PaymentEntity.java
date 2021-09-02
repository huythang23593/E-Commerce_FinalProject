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
@Table(name = "payment")
public class PaymentEntity implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    private double amount;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "payment_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paymentDate;
    
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrdersEntity orders;
    
    @ManyToOne
    @JoinColumn(name = "creditcard_id")
    private CreditCardEntity creditcard;
    

    public PaymentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }

    public CreditCardEntity getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(CreditCardEntity creditcard) {
        this.creditcard = creditcard;
    }
    
    
    
}
