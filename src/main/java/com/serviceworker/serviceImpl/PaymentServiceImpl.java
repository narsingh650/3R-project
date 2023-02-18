package com.serviceworker.serviceImpl;

import com.serviceworker.dto.PaymentCardDto;
import com.serviceworker.model.PaymentCard;
import com.serviceworker.repository.PaymentRepository;
import com.serviceworker.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PaymentCardDto addCard(PaymentCardDto paymentCardDto) {
        validateCardDetails(paymentCardDto);
        PaymentCard card = this.modelMapper.map(paymentCardDto,PaymentCard.class);
        PaymentCard saveCard = this.paymentRepository.save(card);
        return this.modelMapper.map(saveCard,PaymentCardDto.class);
    }

    private void validateCardDetails(PaymentCardDto paymentCardDto){
        if (paymentCardDto.getCardName() == null || paymentCardDto.getCardName().equals("")) {
            throw new RuntimeException("card name can not be null");
        }
        if ( paymentCardDto.getCardNumber() == null || paymentCardDto.getCardNumber().equals("")) {
            throw new RuntimeException("card Number can not be null");
        }
        if ( paymentCardDto.getCvvCode() == null || paymentCardDto.getCvvCode().equals("")) {
            throw new RuntimeException("cvv Number can not be null");
        }
    }

}
