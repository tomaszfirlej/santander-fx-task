package com.santander.santanderfxtask.repository;

public interface MarketPricesRepository {

    void savePrice(MarketPriceBE marketPriceBE);

    MarketPriceBE getPrice(String key);

}
