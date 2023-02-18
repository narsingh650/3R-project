package com.serviceworker.repository;

import com.serviceworker.model.PaymentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends JpaRepository<PaymentCard, Long> {


}
