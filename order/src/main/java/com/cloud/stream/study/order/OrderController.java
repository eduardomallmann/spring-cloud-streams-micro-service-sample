package com.cloud.stream.study.order;

import com.cloud.stream.study.order.model.CommerceItem;
import com.cloud.stream.study.order.model.Order;
import com.cloud.stream.study.order.model.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final MessagingController messagingController;
    private final OrderRepository repository;

    public OrderController(MessagingController messagingController, OrderRepository repository) {
        this.messagingController = messagingController;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Order> getOrder(@RequestBody List<CommerceItem> items) {
        Order order = Order.builder().id(String.valueOf(ObjectId.get())).items(items).build();
        this.repository.save(order);
        this.messagingController.processOrder(order);
        return ResponseEntity.ok(order);
    }
}
