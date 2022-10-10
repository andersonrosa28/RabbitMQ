package com.example.apiproducer.api;

import com.example.apiproducer.domain.machine.Machine;
import com.example.apiproducer.domain.machine.service.MachineService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
public class MachineController implements MachineApi {

    private MachineService machineService;

    @Override
    public ResponseEntity<Machine> save(@RequestBody Machine machine) {
        Machine machineSaved = machineService.save(machine);
        System.out.println(String.format("Machine saved: %s", machineSaved.toString()));
        return ResponseEntity.ok(machineSaved);
    }
}
