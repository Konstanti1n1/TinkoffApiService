package com.example.tinkoffapiservice.DTO;

import com.example.tinkoffapiservice.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StocksDTO {
    private List<Stock> stocks;
}
