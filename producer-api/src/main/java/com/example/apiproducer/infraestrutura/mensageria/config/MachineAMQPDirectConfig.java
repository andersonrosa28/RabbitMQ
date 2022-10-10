package com.example.apiproducer.infraestrutura.mensageria.config;

import com.example.apiproducer.domain.machine.MachineAMQPConfig;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("RABBITMQ_DIRECT")
@Import({ RabbitAutoConfiguration.class })
public class MachineAMQPDirectConfig implements MachineAMQPConfig {

    public static String EXCHANGE_NAME = "MachinesDirect";

    @Override
    public String getNameExchange() {
        return EXCHANGE_NAME;
    }

    /**
     * Direct: irá entregar a mensagem para as queues ligadas a ela.
     */
    @Bean
    @Override
    public Exchange declareExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }
}
