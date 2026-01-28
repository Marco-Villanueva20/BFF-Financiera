package com.retotecnico.msbff;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class MsBffApplication {

    public static void main(String[] args) {
        Hooks.enableAutomaticContextPropagation();
        SpringApplication.run(MsBffApplication.class, args);
    }

    @Bean
    CommandLineRunner probarEncriptacion(TextEncryptor encryptor) {
        return args -> {
            System.out.println("ID Encriptado: " + encryptor.encrypt("f9f12122-425e-4398-9f07-96199f40a1f4"));
        };
    }

}
