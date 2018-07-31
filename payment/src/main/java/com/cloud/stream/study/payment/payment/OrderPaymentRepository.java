package com.cloud.stream.study.payment.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long> {
}
