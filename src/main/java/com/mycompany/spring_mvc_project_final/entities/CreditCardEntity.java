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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "credit_card")
public class CreditCardEntity implements Serializable{
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    
    @Column(length = 100)
    private String name;
    
    private String cardNumber;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "exp_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date expDate; 
    
    private String cvcCode;
    
    private double balance;
    
    @OneToMany(mappedBy = "creditcard" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<PaymentEntity> payment;

    public CreditCardEntity() {
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getCvcCode() {
        return cvcCode;
    }

    public void setCvcCode(String cvcCode) {
        this.cvcCode = cvcCode;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<PaymentEntity> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentEntity> payment) {
        this.payment = payment;
    }
    
    
}
