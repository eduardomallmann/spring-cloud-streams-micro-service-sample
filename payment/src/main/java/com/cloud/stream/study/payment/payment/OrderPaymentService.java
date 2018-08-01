package com.cloud.stream.study.payment.payment;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Service
@EnableBinding(value = {Sink.class, Source.class})
public class OrderPaymentService {

    private final OrderPaymentRepository paymentRepository;
    private final Source source;

    public OrderPaymentService(OrderPaymentRepository paymentRepository, Source source) {
        this.paymentRepository = paymentRepository;
        this.source = source;
    }

    @StreamListener(Sink.INPUT)
    public void saveOrder(final OrderPayment order) {
        this.paymentRepository.saveAndFlush(order);
    }

    public OrderPayment informPayment(final Long id) {

        OrderPayment order = this.paymentRepository.findById(id)
                .orElseThrow(() -> new RestClientException("Payment id: ".concat(String.valueOf(id))
                        .concat(" not found!")));

        order.setPayed(true);

        this.paymentRepository.saveAndFlush(order);

        this.sendMessage(order);

        return order;
    }

    private void sendMessage(OrderPayment order) {
        Message<OrderPayment> message = MessageBuilder.withPayload(order).build();
        source.output().send(message);
    }

    public List<OrderPayment> getAllPayments() {
        return this.paymentRepository.findAll();
    }
}
