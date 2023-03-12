package com.example.tinkoffapiservice.Service;

import com.example.tinkoffapiservice.DTO.StockPrice;
import com.example.tinkoffapiservice.DTO.StockPriceDTO;
import com.example.tinkoffapiservice.DTO.StocksDTO;
import com.example.tinkoffapiservice.DTO.TickersDTO;
import com.example.tinkoffapiservice.exception.StockNotFoundException;
import com.example.tinkoffapiservice.exception.UncorrectData;
import com.example.tinkoffapiservice.model.Currency;
import com.example.tinkoffapiservice.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TinkoffStockService implements StockService {
    private final OpenApi openAPI;
    private final AsyncMethods asyncMethod;


    @Override
    public Stock getStockByTicker(String ticker) {
        List<MarketInstrument> marketInstruments = getMarketInstruments(ticker);

        if(marketInstruments.isEmpty()){
            throw  new StockNotFoundException(String.format("not found %S stock", ticker));
        }

        return convertToStock(marketInstruments.get(0));
    }

    @Override
    public StocksDTO getStocksByTickersList(TickersDTO tickers) {

        List<Stock> stockList = getMarketInstrumentListWithoutEmpty(tickers).stream()
                .map(marketInstruments-> convertToStock(marketInstruments.get(0)))
                .toList();

        return new StocksDTO(stockList);

    }

    @Override
    public StockPrice getPriceByTicker(String ticker) {
        List<MarketInstrument> marketInstruments = getMarketInstruments(ticker);

        if(marketInstruments.isEmpty()){
            throw  new StockNotFoundException(String.format("not found %S stock", ticker));
        }


        MarketInstrument instrument = marketInstruments.get(0);
        return convertToStockPrice(instrument);
    }



    @Override
    public StockPriceDTO getPricesByTicker(TickersDTO tickers) {

        if(tickers==null || tickers.getTickers().isEmpty()!=false) throw new UncorrectData("Некоректные данные");

        List <StockPrice> stockPriceList = getMarketInstrumentListWithoutEmpty(tickers).stream()
                .map(marketInstruments -> marketInstruments.get(0))
                .map(this::convertToStockPrice)
                .toList();

        if(stockPriceList.isEmpty()){
            throw  new StockNotFoundException(String.format("not found %S stock", tickers.getTickers().toString()));
        }

        return new StockPriceDTO(stockPriceList);
    }


    private List<List<MarketInstrument>> getMarketInstrumentListWithoutEmpty(TickersDTO tickers){
        return  tickers.getTickers().stream()
                .map(x->asyncMethod.getMarketList(x))
                .map(CompletableFuture::join)
                .map(MarketInstrumentList::getInstruments)
                .filter(marketInstruments->!marketInstruments.isEmpty()).toList();
    }

    private List<MarketInstrument> getMarketInstruments(String ticker) {
        CompletableFuture<MarketInstrumentList> market = asyncMethod.getMarketList(ticker);
        List<MarketInstrument> marketInstruments = market.join().getInstruments();
        return marketInstruments;
    }

    public StockPrice convertToStockPrice(MarketInstrument item){
        String figi = item.getFigi();
        Double price = asyncMethod.getOrderedBookByFigi(figi).join().get().getLastPrice().doubleValue();
        return new StockPrice(figi, price);
    }
    public Stock convertToStock(MarketInstrument item){
        return Stock.builder()
                .ticker(item.getTicker())
                .figi(item.getFigi())
                .name(item.getName())
                .type(item.getType().getValue())
                .currency(Currency.valueOf(item.getCurrency().getValue()))
                .source("TINKOFF")
                .build();
    }
}
