package com.example.apiproducer.domain.machine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Machine {
    private String brand;
    private String model;
    private String fabricationYear;
    private String routingKey;
}
