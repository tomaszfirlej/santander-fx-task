package com.santander.santanderfxtask.services;

import com.santander.santanderfxtask.exception.CurrencyPairUnrecognizedException;
import com.santander.santanderfxtask.repository.MarketPriceBE;
import com.santander.santanderfxtask.repository.MarketPricesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FxPricesService implements FxPrices {

    private final MarketPricesRepository marketPricesRepository;

    public FxPricesService(final MarketPricesRepository marketPricesRepository) {
        this.marketPricesRepository = marketPricesRepository;
    }

    public MarketPriceInternalDO getLatestPrice(String currencyPair) {

        MarketPriceBE priceFromRepo = marketPricesRepository.getPrice(currencyPair);

        if (priceFromRepo == null) {
            throw new CurrencyPairUnrecognizedException(currencyPair);
        }

        return MarketPriceInternalDO.builder()
                .id(priceFromRepo.getId())
                .name(priceFromRepo.getName())
                .ask(priceFromRepo.getAsk().multiply(new BigDecimal("1.001"))) // +0.1%
                .bid(priceFromRepo.getBid().multiply(new BigDecimal("0.999"))) // -0.1%
                .timestamp(priceFromRepo.getTimestamp())
                .build();
    }

}
