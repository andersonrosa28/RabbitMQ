package com.example.apiconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication(exclude = { RabbitAutoConfiguration.class })
@EnableWebSocket
@EnableWebSocketMessageBroker
public class ApiConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiConsumerApplication.class, args);
    }

}
