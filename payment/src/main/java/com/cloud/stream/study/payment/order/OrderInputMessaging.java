package com.cloud.stream.study.payment.order;

import com.cloud.stream.study.payment.payment.OrderPayment;
import com.cloud.stream.study.payment.payment.OrderPaymentService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class OrderInputMessaging {

    private final OrderPaymentService service;

    public OrderInputMessaging(OrderPaymentService service) {
        this.service = service;
    }

    @StreamListener(Sink.INPUT)
    public void process(OrderPayment orderReceived) {
        this.service.saveOrder(orderReceived);
    }

}
