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
@Profile("RABBITMQ_FANOUT")
@Import({ RabbitAutoConfiguration.class })
public class MachineAMQPFanoutConfig implements MachineAMQPConfig {

    public static String EXCHANGE_NAME = "MachinesFanout";

    @Override
    public String getNameExchange() {
        return EXCHANGE_NAME;
    }

    /**
     * Fanout: irá entregar a mensagem para as queues ligadas a ela e não suporta routing key.
     */
    @Bean
    public Exchange declareExchange() {
        return ExchangeBuilder.fanoutExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }
}
