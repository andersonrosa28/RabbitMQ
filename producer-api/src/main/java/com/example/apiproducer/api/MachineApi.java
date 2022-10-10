package com.example.apiproducer.api;

import com.example.apiproducer.domain.machine.Machine;
import com.example.apiproducer.domain.machine.service.MachineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@Api(value = "/api/machines", produces = "application/json", tags = "Machines")
public interface MachineApi {

    @PostMapping(path = "/machines", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Metodo receve uma Maquina para armazenamento", response = Machine.class, httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucesso"),
            @ApiResponse(code = 400, message = "Erros de negócio"),
            @ApiResponse(code = 401, message = "Falha em autenticação"),
            @ApiResponse(code = 403, message = "Falta de privilégios"),
            @ApiResponse(code = 500, message = "Erros internos não identificados")
    })
    ResponseEntity<Machine> save(@RequestBody Machine machine);
}
