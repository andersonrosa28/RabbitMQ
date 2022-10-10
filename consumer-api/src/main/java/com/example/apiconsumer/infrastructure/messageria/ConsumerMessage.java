package com.example.apiconsumer.infrastructure.messageria;

public interface ConsumerMessage<INPUT> {

    void consumer(INPUT objectInput);

}
