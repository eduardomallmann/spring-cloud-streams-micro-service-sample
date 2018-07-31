package com.cloud.stream.study.order;

import com.cloud.stream.study.order.model.CommerceItem;
import com.cloud.stream.study.order.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderOutMessaging outMessaging;

    public OrderController(OrderOutMessaging outMessaging) {
        this.outMessaging = outMessaging;
    }

    @PostMapping
    public ResponseEntity<Order> getOrder(@RequestBody List<CommerceItem> items) {
        Order order = Order.builder().items(items).build();
        this.outMessaging.processOrder(order);
        return ResponseEntity.ok(order);
    }
}
