package com.example.tinkoffapiservice.Service;

import org.springframework.scheduling.annotation.Async;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AsyncMethods {

    public CompletableFuture<MarketInstrumentList> getMarketList(String ticker);
    public CompletableFuture<Optional<Orderbook>>  getOrderedBookByFigi(String figi);
}
