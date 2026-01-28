package com.retotecnico.msproductosfinancieros.web.controller;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import com.retotecnico.api.CuentasAhorroApi;
import com.retotecnico.model.CuentaAhorroRequest;
import com.retotecnico.model.CuentaAhorroResponse;
import com.retotecnico.msproductosfinancieros.domain.service.CuentaAhorroService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CuentaAhorroController implements CuentasAhorroApi {

	private final CuentaAhorroService cuentaAhorroService;

	@Override
	public Mono<ResponseEntity<CuentaAhorroResponse>> agregarCuentaAhorro(
			@Valid Mono<CuentaAhorroRequest> cuentaAhorroRequest, ServerWebExchange exchange) {
		return cuentaAhorroRequest.flatMap(request -> cuentaAhorroService.crearCuentaAhorro(request)) // 2 inserts
				.map(cuentaAhorroGuardado -> {
					URI location = UriComponentsBuilder.fromUri(exchange.getRequest().getURI()).path("/{id}")
							.buildAndExpand(cuentaAhorroGuardado.getCuentaId()).toUri();
					return ResponseEntity.created(location).body(cuentaAhorroGuardado);
				});
	}

	@Override
	public Mono<ResponseEntity<Flux<CuentaAhorroResponse>>> obtenerCuentaAhorroPorClienteId(@NotNull UUID clienteId,
			ServerWebExchange exchange) {
		Flux<CuentaAhorroResponse> listaCuentas = cuentaAhorroService.findCuentaAhorroByClienteId(clienteId);

		return Mono.just(ResponseEntity.ok(listaCuentas));
	}

	@Override
	public Mono<ResponseEntity<Flux<CuentaAhorroResponse>>> listarCuentasAhorro(ServerWebExchange exchange) {
		Flux<CuentaAhorroResponse> listaCuentaAhorro = cuentaAhorroService.findAllCuentas();
		return Mono.just(ResponseEntity.ok(listaCuentaAhorro));
	}

	@Override
	public Mono<ResponseEntity<CuentaAhorroResponse>> obtenerCuentaAhorroPorId(@NotNull UUID id,
			ServerWebExchange exchange) {
		// TODO Auto-generated method stub
		return null;
	}

}
