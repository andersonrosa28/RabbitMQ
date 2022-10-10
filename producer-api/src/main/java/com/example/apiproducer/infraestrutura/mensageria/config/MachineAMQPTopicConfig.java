package com.example.apiproducer.infraestrutura.mensageria.config;

import com.example.apiproducer.domain.machine.MachineAMQPConfig;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("RABBITMQ_TOPIC")
@Import({ RabbitAutoConfiguration.class })
public class MachineAMQPTopicConfig implements MachineAMQPConfig {

    public static String EXCHANGE_NAME = "MachinesTopic";

    @Override
    public String getNameExchange() {
        return EXCHANGE_NAME;
    }

    /**
     * Topic: mistura entre direct e fanout, onde uma exchange pode ter varias queues ligadas,
     *        e cada mensagem recebida pela exchange será replicada para as queues. A diferença é que
     *        no topic é possível usar a routing key usando alguns patterns. Exemplo: # ou *
     */
    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }
}
