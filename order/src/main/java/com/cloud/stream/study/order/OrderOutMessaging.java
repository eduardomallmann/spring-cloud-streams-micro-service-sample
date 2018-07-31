package com.cloud.stream.study.order;

import com.cloud.stream.study.order.model.Order;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class OrderOutMessaging {

    private final Source source;

    public OrderOutMessaging(Source source) {
        this.source = source;
    }

    public void processOrder(Order order) {
        Message<Order> message = MessageBuilder.withPayload(order).build();
        source.output().send(message);
    }

}
