package com.serviceworker.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    private String cardNumber;
    private String cvvCode;

    @DateTimeFormat(pattern = "MM/yy")
    private String expiredDate;

    public PaymentCard() {
    }

    public PaymentCard(Long id, String cardName, String cardNumber, String cvvCode, String expiredDate) {
        this.id = id;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.cvvCode = cvvCode;
        this.expiredDate = expiredDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

}
