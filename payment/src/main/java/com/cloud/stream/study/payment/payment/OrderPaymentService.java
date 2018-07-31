package com.cloud.stream.study.payment.payment;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
public class OrderPaymentService {

    private final OrderPaymentRepository paymentRepository;
    private final PaymentOutMessaging messaging;

    public OrderPaymentService(OrderPaymentRepository paymentRepository, PaymentOutMessaging messaging) {
        this.paymentRepository = paymentRepository;
        this.messaging = messaging;
    }

    public OrderPayment saveOrder(final OrderPayment order) {
        return this.paymentRepository.saveAndFlush(order);
    }

    public OrderPayment informPayment(final Long id) {

        OrderPayment order = this.paymentRepository.findById(id)
                .orElseThrow(() -> new RestClientException("Payment id: ".concat(String.valueOf(id))
                        .concat(" not found!")));

        order.setPayed(true);

        this.paymentRepository.saveAndFlush(order);

        this.messaging.processOrder(order);

        return order;
    }

    public List<OrderPayment> getAllPayments() {
        return this.paymentRepository.findAll();
    }
}
