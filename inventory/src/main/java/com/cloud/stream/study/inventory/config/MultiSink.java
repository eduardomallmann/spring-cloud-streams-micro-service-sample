package com.cloud.stream.study.inventory.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MultiSink {

    String ORDER = "order";
    String PAYMENT = "payment";

    @Input(ORDER)
    SubscribableChannel order();

    @Input(PAYMENT)
    SubscribableChannel payment();

}
