package com.santander.santanderfxtask.converters;

import com.santander.santanderfxtask.rest.MarketPriceDO;
import com.santander.santanderfxtask.services.MarketPriceInternalDO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MarketPriceDtoConverter {

    public static MarketPriceDO convertInternalToExternalDo(final MarketPriceInternalDO marketPriceInternalDO) {
        return MarketPriceDO.builder()
                .id(marketPriceInternalDO.getId())
                .name(marketPriceInternalDO.getName())
                .ask(marketPriceInternalDO.getAsk())
                .bid(marketPriceInternalDO.getBid())
                .timestamp(marketPriceInternalDO.getTimestamp())
                .build();
    }

}
