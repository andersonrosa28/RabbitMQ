package com.example.apiproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication(exclude = { RabbitAutoConfiguration.class })
public class ApiProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiProducerApplication.class, args);
    }

}
