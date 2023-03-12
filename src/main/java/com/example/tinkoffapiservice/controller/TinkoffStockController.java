package com.example.tinkoffapiservice.controller;

import com.example.tinkoffapiservice.DTO.StockPrice;
import com.example.tinkoffapiservice.DTO.StockPriceDTO;
import com.example.tinkoffapiservice.DTO.StocksDTO;
import com.example.tinkoffapiservice.DTO.TickersDTO;
import com.example.tinkoffapiservice.Service.StockService;
import com.example.tinkoffapiservice.model.Stock;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/stocks")
@Slf4j
public class TinkoffStockController {

    private final StockService stockService;


    @GetMapping("/{ticker}")
    public Stock getStock(@PathVariable("ticker") String ticker) {
        return stockService.getStockByTicker(ticker);
    }

    @PostMapping("/ticker/list")
    public StocksDTO getStocksList(@RequestBody TickersDTO tickers) {
        return stockService.getStocksByTickersList(tickers);
    }

    @GetMapping("/{ticker}/price")
    public StockPrice getStockPrice (@PathVariable String ticker){
        return stockService.getPriceByTicker(ticker);
    }

    @PostMapping("/prices")
    public StockPriceDTO getPrices(TickersDTO tickers){
        return stockService.getPricesByTicker(tickers);
    }
}
