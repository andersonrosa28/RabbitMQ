package com.example.apiproducer.domain.machine.service;

import com.example.apiproducer.domain.machine.Machine;
import com.example.apiproducer.domain.machine.MachineAMQPConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MachineService {

    private MachineAMQPConfig machineAMQPConfig;
    private RabbitTemplate rabbitTemplate;

    public Machine save(Machine machine) {
        //Machine machineSaved = machineRepository.save(machine);
        sendMachineToRabbit(machine);
        return machine;
    }

    public void sendMachineToRabbit(Machine machine) {
        try {
            if(this.machineAMQPConfig != null && this.rabbitTemplate != null) {
                String json = new ObjectMapper().writeValueAsString(machine);
                rabbitTemplate.convertAndSend(machineAMQPConfig.getNameExchange(), machine.getRoutingKey(), json);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
