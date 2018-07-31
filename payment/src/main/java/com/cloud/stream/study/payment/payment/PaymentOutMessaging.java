package com.cloud.stream.study.payment.payment;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class PaymentOutMessaging {

    private final Source source;

    public PaymentOutMessaging(Source source) {
        this.source = source;
    }

    public void processOrder(OrderPayment order) {
        Message<OrderPayment> message = MessageBuilder.withPayload(order).build();
        source.output().send(message);
    }
}
