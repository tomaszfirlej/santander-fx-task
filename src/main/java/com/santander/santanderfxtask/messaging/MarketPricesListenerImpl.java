package com.santander.santanderfxtask.messaging;

import com.santander.santanderfxtask.converters.MarketPriceMessageConverter;
import com.santander.santanderfxtask.repository.MarketPriceBE;
import com.santander.santanderfxtask.repository.MarketPricesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MarketPricesListenerImpl implements MarketPricesListener {

    private final MarketPricesRepository marketPricesRepository;

    public MarketPricesListenerImpl(MarketPricesRepository marketPricesRepository) {
        this.marketPricesRepository = marketPricesRepository;
    }

    @Override
    public void onMessage(String message) {
        MarketPriceBE marketPriceBE = MarketPriceMessageConverter.convertMessageToEntity(message);
        // I decided to save raw market price into repository, as I've assumed that commission might not be constant for every client.
        marketPricesRepository.savePrice(marketPriceBE);
        log.info("Registered new market price for currency pair: " + marketPriceBE.getName() + " which is:" + marketPriceBE);
    }
}
