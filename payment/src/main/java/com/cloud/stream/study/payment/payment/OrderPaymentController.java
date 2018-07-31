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

    public OrderPaymentController(OrderPaymentService service) {
        this.service = service;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderPayment> updateOrder(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.service.informPayment(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderPayment>> getAllPayments() {
        return ResponseEntity.ok(this.service.getAllPayments());
    }
}
