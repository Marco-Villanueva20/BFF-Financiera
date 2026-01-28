package com.retotecnico.msbff.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Configuration
public class CryptoConfig {


    @Bean
    public TextEncryptor textEncryptor() {
        String password = "bff-secret-key";
        String salt = "8f26f1ff9f29e696"; //0-9 - a-f
        return Encryptors.text(password, salt);
    }

}