package com.example.tinkoffapiservice.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.tinkoff.invest.openapi.MarketContext;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;
import ru.tinkoff.invest.openapi.model.rest.Orderbook;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
@Component
@RequiredArgsConstructor
public class AsyncMethodForTinkoff implements AsyncMethods {

    private final OpenApi openApi;

    @Async
    @Override
    public CompletableFuture<MarketInstrumentList> getMarketList(String ticker){
        MarketContext marketContext = openApi.getMarketContext();
        return marketContext.searchMarketInstrumentsByTicker(ticker);
    }

    @Async
    @Override
    public CompletableFuture<Optional<Orderbook>>  getOrderedBookByFigi(String figi){
        return openApi.getMarketContext().getMarketOrderbook(figi,0);
    }
}
