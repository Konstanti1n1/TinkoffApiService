package com.example.tinkoffapiservice.configre;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.tinkoff.invest.openapi.okhttp.OkHttpOpenApi;

@Configuration
@EnableConfigurationProperties(APiConfig.class)
@AllArgsConstructor
public class ApplicationConfig {
    private final APiConfig aPiConfig;
    @Bean
    public OkHttpOpenApi getOpenApi(){
        String token = System.getenv("ssoToken");
        return new OkHttpOpenApi(token, aPiConfig.getIsSandBoxMode());
    }
}
