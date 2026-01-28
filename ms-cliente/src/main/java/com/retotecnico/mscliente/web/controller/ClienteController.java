package com.retotecnico.mscliente.web.controller;

import com.retotecnico.mscliente.domain.dto.ClienteRequest;
import com.retotecnico.mscliente.domain.dto.ClienteResponse;
import com.retotecnico.mscliente.domain.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Mono<ClienteResponse>> create(@RequestBody ClienteRequest request){
        return ResponseEntity.ok(clienteService.create(request));
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<Mono<ClienteResponse>> buscarClientePorUsuarioAuth(@PathVariable UUID userId){
        return ResponseEntity.ok(clienteService.findByUserId(userId));
    }


    @GetMapping
    public ResponseEntity<Flux<ClienteResponse>> findAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }

}
