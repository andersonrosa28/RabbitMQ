package com.example.apiconsumer.infrastructure.config.fanout;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("RABBITMQ_FANOUT")
@Import({ RabbitAutoConfiguration.class })
public class MachineAMQPFanoutConfig {
    public static final String QUEUE_ONE = "machines-created-fanout-one";
    public static final String QUEUE_TWO = "machines-created-fanout-two";
    public static final String QUEUE_THREE = "machines-created-fanout-three";
    public static final String EXCHANGE_NAME = "MachinesFanout";


    @Bean
    public Declarables fanoutBindings() {
        Queue directQueue1 = QueueBuilder.durable(QUEUE_ONE).build();
        Queue directQueue2 = QueueBuilder.durable(QUEUE_TWO).build();
        Queue directQueue3 = QueueBuilder.durable(QUEUE_THREE).build();

        FanoutExchange fanoutExchange = new FanoutExchange(EXCHANGE_NAME);

        return new Declarables(
                directQueue1,
                directQueue2,
                directQueue3,
                fanoutExchange,
                BindingBuilder.bind(directQueue1).to(fanoutExchange),
                BindingBuilder.bind(directQueue2).to(fanoutExchange),
                BindingBuilder.bind(directQueue3).to(fanoutExchange));
    }
}
