package com.example.tinkoffapiservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@AllArgsConstructor
public class Stock {

    String ticker;
    String figi;
    String name;
    String type;
    Currency currency;
    String source;
}
