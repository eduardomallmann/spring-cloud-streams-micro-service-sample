package com.cloud.stream.study.inventory.payment;

import com.cloud.stream.study.inventory.config.MultiSink;
import com.cloud.stream.study.inventory.order.Order;
import com.cloud.stream.study.inventory.product.ProductInventoryService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MultiSink.class)
public class PaymentInputMessaging {

    private final ProductInventoryService service;

    public PaymentInputMessaging(ProductInventoryService service) {
        this.service = service;
    }

    @StreamListener(MultiSink.PAYMENT)
    public void process(Order order) {
        this.service.decreaseAvailiability(order.getItems());
    }

}
