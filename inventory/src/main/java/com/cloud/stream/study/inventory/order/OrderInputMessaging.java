package com.cloud.stream.study.inventory.order;

import com.cloud.stream.study.inventory.config.MultiSink;
import com.cloud.stream.study.inventory.product.ProductInventoryService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MultiSink.class)
public class OrderInputMessaging {

    private final ProductInventoryService service;

    public OrderInputMessaging(ProductInventoryService service) {
        this.service = service;
    }

    @StreamListener(MultiSink.ORDER)
    public void process(Order orderReceived) {
        this.service.decreaseDisposability(orderReceived.getItems());
    }

}
