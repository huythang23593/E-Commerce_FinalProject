/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.service;

import com.mycompany.spring_mvc_project_final.entities.CreditCardEntity;
import com.mycompany.spring_mvc_project_final.repository.creditCardRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CreditCardService {

    @Autowired
    private creditCardRepository cardRepository;

    public CreditCardEntity getCreditCard(String cardNumber) {
        return cardRepository.getcreditCard(cardNumber);
    }

    public void saveCreditCard(CreditCardEntity cardEntity) {
        cardRepository.save(cardEntity);
    }
}
