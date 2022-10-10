package com.example.apiconsumer.infrastructure.config.direct;

import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("RABBITMQ_DIRECT")
@Import({ RabbitAutoConfiguration.class })
public class MachineAMQPDirectConfig {
    public static final String QUEUE_ONE = "machines-created-direct-one";
    public static final String QUEUE_TWO = "machines-created-direct-two";
    public static final String QUEUE_THREE = "machines-created-direct-three";
    public static final String QUEUE_FOUR = "machines-created-direct-four";
    public static final String ROUTING_KEY_ONE = "one";
    public static final String ROUTING_KEY_TWO = "two";
    public static final String ROUTING_KEY_THREE = "three";
    public static final String EXCHANGE_NAME = "MachinesDirect";

    @Bean
    public Declarables directBindings() {
        Queue directQueue1 = QueueBuilder.durable(QUEUE_ONE).build();
        Queue directQueue2 = QueueBuilder.durable(QUEUE_TWO).build();
        Queue directQueue3 = QueueBuilder.durable(QUEUE_THREE).build();
        Queue directQueue4 = QueueBuilder.durable(QUEUE_FOUR).build();

        DirectExchange directExchange = new DirectExchange(EXCHANGE_NAME, true, false);

        return new Declarables(
                directQueue1,
                directQueue2,
                directQueue3,
                directQueue4,
                directExchange,
                BindingBuilder.bind(directQueue1).to(directExchange).with(ROUTING_KEY_ONE),
                BindingBuilder.bind(directQueue2).to(directExchange).with(ROUTING_KEY_TWO),
                BindingBuilder.bind(directQueue3).to(directExchange).with(ROUTING_KEY_THREE),
                BindingBuilder.bind(directQueue4).to(directExchange).with(""));
    }

}
