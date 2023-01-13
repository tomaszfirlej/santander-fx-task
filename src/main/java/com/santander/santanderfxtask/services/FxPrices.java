package com.santander.santanderfxtask.services;

public interface FxPrices {
    MarketPriceInternalDO getLatestPrice(String currencyPair);
}
