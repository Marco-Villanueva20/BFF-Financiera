package com.retotecnico.msbff.controller;

import com.retotecnico.msbff.dto.ClienteResponseDTO;
import com.retotecnico.msbff.service.CustomerBffService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/resumen")
@RequiredArgsConstructor
public class BffController {
    private final CustomerBffService bffService;

    @GetMapping()
    public Mono<ClienteResponseDTO> getResumen() {
        return bffService.getConsolidatedData();
    }
}
