package com.retotecnico.msproductosfinancieros.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class WebSecurityFilter {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         ProblemDetailsAuthenticationEntryPoint entryPoint) throws Exception {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex -> ex
                                .pathMatchers("/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/api/v1/swagger-ui.html",
                                        "/api/v1/swagger-ui/**",
                                        "/api/v1/v3/api-docs/**",
                                        "/api/openapi.yaml").permitAll()
                        .anyExchange().authenticated()
                        //.anyExchange().denyAll()
                ).oauth2ResourceServer((oauth2) -> oauth2
                        .authenticationEntryPoint(entryPoint)
                        .jwt(jwt->
                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .build();
    }

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        return jwt -> {

            List<String> roles = jwt.getClaimAsStringList("roles");

            List<GrantedAuthority> authorities =
                    roles == null
                            ? List.of()
                            : roles.stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

            return Mono.just(new JwtAuthenticationToken(jwt, authorities));
        };
    }


}
