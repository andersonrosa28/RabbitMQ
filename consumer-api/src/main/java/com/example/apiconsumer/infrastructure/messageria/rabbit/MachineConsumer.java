package com.example.apiconsumer.infrastructure.messageria.rabbit;

import com.example.apiconsumer.infrastructure.config.direct.MachineAMQPDirectConfig;
import com.example.apiconsumer.infrastructure.messageria.ConsumerMessage;
import com.example.apiconsumer.infrastructure.websocket.MachineWebSocketConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MachineConsumer implements ConsumerMessage<Message> {

    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues = {
            MachineAMQPDirectConfig.QUEUE_ONE,
            MachineAMQPDirectConfig.QUEUE_TWO,
            MachineAMQPDirectConfig.QUEUE_THREE
    })
    public void consumer(Message message) {
        simpMessagingTemplate.convertAndSend(
                MachineWebSocketConfiguration.BROKER, new String(message.getBody())
        );
    }

}
