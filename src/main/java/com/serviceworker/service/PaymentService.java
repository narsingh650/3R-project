package com.serviceworker.service;

import com.serviceworker.dto.PaymentCardDto;

public interface PaymentService {

    PaymentCardDto addCard(PaymentCardDto paymentCardDto);
}
