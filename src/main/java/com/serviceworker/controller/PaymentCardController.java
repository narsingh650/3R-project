package com.serviceworker.controller;


import com.serviceworker.dto.PaymentCardDto;
import com.serviceworker.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentCardController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/addCard")
    public ResponseEntity<PaymentCardDto> addCard(@RequestBody  PaymentCardDto paymentCardDto){
        PaymentCardDto create = this.paymentService.addCard(paymentCardDto);
        return new ResponseEntity<PaymentCardDto >(create, HttpStatus.CREATED);
    }



}
