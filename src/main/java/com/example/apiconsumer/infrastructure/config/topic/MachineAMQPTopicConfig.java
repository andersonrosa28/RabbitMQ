package com.example.apiconsumer.infrastructure.config.topic;

import org.springframework.amqp.core.*;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("RABBITMQ_TOPIC")
@Import({ RabbitAutoConfiguration.class })
public class MachineAMQPTopicConfig {
    public static final String QUEUE_ONE = "machines-created-topic-one";
    public static final String QUEUE_TWO = "machines-created-topic-two";
    public static final String QUEUE_THREE = "machines-created-topic-three";
    public static final String EXCHANGE_NAME = "MachinesTopic";

    @Bean
    public Declarables topicBindings() {
        Queue topicQueue1 = new Queue(QUEUE_ONE, true);
        Queue topicQueue2 = new Queue(QUEUE_TWO, true);
        Queue topicQueue3 = new Queue(QUEUE_THREE, true);

        TopicExchange topicExchange = new TopicExchange(EXCHANGE_NAME);

        return new Declarables(
                topicQueue1,
                topicQueue2,
                topicQueue3,
                topicExchange,
                BindingBuilder
                        .bind(topicQueue1)
                        .to(topicExchange).with("*.important.*"),
                BindingBuilder
                        .bind(topicQueue2)
                        .to(topicExchange).with("#.error"),
                BindingBuilder
                        .bind(topicQueue3)
                        .to(topicExchange).with("#.error"));
    }
}
