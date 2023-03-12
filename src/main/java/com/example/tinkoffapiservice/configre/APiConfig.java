package com.example.tinkoffapiservice.configre;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api")
@Data
@AllArgsConstructor
public class APiConfig {
    private Boolean isSandBoxMode;
}
