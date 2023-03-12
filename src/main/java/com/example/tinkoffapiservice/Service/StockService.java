package com.example.tinkoffapiservice.Service;

import com.example.tinkoffapiservice.DTO.StockPrice;
import com.example.tinkoffapiservice.DTO.StockPriceDTO;
import com.example.tinkoffapiservice.DTO.StocksDTO;
import com.example.tinkoffapiservice.DTO.TickersDTO;
import com.example.tinkoffapiservice.model.Stock;

public interface StockService {
    Stock getStockByTicker(String ticker);
    StocksDTO getStocksByTickersList(TickersDTO tickers);

    StockPrice getPriceByTicker(String ticker);

    StockPriceDTO getPricesByTicker(TickersDTO tickersDTO);
}
