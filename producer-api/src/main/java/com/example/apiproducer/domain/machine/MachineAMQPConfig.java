package com.example.apiproducer.domain.machine;

import org.springframework.amqp.core.Exchange;

public interface MachineAMQPConfig {

    String getNameExchange();

    Exchange declareExchange();
}
