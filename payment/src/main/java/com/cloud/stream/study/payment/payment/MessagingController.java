package com.cloud.stream.study.payment.payment;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;

@Controller
@EnableBinding(value = {Sink.class, Source.class})
public class MessagingController {

    private final OrderPaymentService orderPaymentService;
    private final Source source;

    public MessagingController(OrderPaymentService orderPaymentService, Source source) {
        this.orderPaymentService = orderPaymentService;
        this.source = source;
    }

    @StreamListener(Sink.INPUT)
    public void saveOrder(final OrderPayment order) {
        this.orderPaymentService.saveOrder(order);
    }

    public void sendMessage(OrderPayment order) {
        Message<OrderPayment> message = MessageBuilder.withPayload(order).build();
        source.output().send(message);
    }




}
