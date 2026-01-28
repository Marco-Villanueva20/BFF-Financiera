package com.retotecnico.msproductosfinancieros.web.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import com.retotecnico.api.TarjetasCreditoApi;
import com.retotecnico.model.TarjetaCreditoRequest;
import com.retotecnico.model.TarjetaCreditoResponse;
import com.retotecnico.msproductosfinancieros.domain.service.TarjetaCreditoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tarjeta-credito")
@RequiredArgsConstructor
public class TarjetaCreditoController implements TarjetasCreditoApi {

	private final TarjetaCreditoService tarjetaCreditoService;

	@Override
	public Mono<ResponseEntity<TarjetaCreditoResponse>> agregarTarjetaCredito(
			@Valid Mono<TarjetaCreditoRequest> tarjetaCreditoRequest, ServerWebExchange exchange) 
	{
		return tarjetaCreditoRequest.flatMap(request -> tarjetaCreditoService.crearTarjetaCredito(request))
				.map(tarjetaCreada -> {
					URI location = UriComponentsBuilder
						.fromUri(exchange.getRequest().getURI())
						.path("/{id}")
						.buildAndExpand(tarjetaCreada.getCuentaId())
						.toUri();
				return ResponseEntity.created(location).body(tarjetaCreada);
			});
		
	}

	@Override
	public Mono<ResponseEntity<Flux<TarjetaCreditoResponse>>> obtenerTarjetasCredito(ServerWebExchange exchange) {
		Flux<TarjetaCreditoResponse> listaTarjetaCredito = tarjetaCreditoService.encontrarTarjetasCredito();
		return Mono.just(ResponseEntity.ok(listaTarjetaCredito));
	}

}
