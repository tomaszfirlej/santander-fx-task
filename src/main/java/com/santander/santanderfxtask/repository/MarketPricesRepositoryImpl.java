package com.santander.santanderfxtask.repository;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class MarketPricesRepositoryImpl implements MarketPricesRepository {

    private final HashMap<String, MarketPriceBE> latestMarketPricesMap = new HashMap<>();

    @Override
    public void savePrice(MarketPriceBE marketPriceBE) {
        latestMarketPricesMap.put(marketPriceBE.getName(), marketPriceBE);
    }

    @Override
    public MarketPriceBE getPrice(String key) {
        return latestMarketPricesMap.get(key);
    }
}
