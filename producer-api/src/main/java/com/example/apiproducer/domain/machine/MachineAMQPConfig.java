package com.example.apiproducer.domain.machine;

public interface MachineAMQPConfig<OUTPUT> {

    String getNameExchange();

    OUTPUT declareExchange();
}
