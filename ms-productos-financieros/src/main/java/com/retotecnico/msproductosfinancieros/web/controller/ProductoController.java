package com.retotecnico.msproductosfinancieros.web.controller;

import com.retotecnico.api.CuentasApi;
import com.retotecnico.model.*;
import com.retotecnico.msproductosfinancieros.domain.service.CuentaService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class ProductoController implements CuentasApi {
	private final CuentaService cuentaService;

	@Override
	public Mono<ResponseEntity<Flux<CuentaResponse>>> obtenerCuentas(ServerWebExchange exchange) {
		return Mono.just(ResponseEntity.ok(cuentaService.findAll()));
	}

	@Override
	public Mono<ResponseEntity<Flux<CuentaResponse>>> obtenerCuentasPorClienteId(@NotNull UUID clienteId,
			ServerWebExchange exchange) {
		Flux<CuentaResponse> listaCuentas = cuentaService.findByClienteId(clienteId);
		return Mono.just(ResponseEntity.ok(listaCuentas));
	}

}
