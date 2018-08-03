package com.cloud.stream.study.payment.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class OrderPaymentController {

    private final OrderPaymentService service;
    private final MessagingController messagingController;


    public OrderPaymentController(OrderPaymentService service, MessagingController messagingController) {
        this.service = service;
        this.messagingController = messagingController;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderPayment> updateOrder(@PathVariable("id") Long id) {

        OrderPayment order = this.service.informPayment(id);

        this.messagingController.sendMessage(order);

        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<OrderPayment>> getAllPayments() {
        return ResponseEntity.ok(this.service.getAllPayments());
    }
}
