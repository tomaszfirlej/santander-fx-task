package com.santander.santanderfxtask.converters;

import com.santander.santanderfxtask.rest.MarketPriceDO;
import com.santander.santanderfxtask.services.MarketPriceInternalDO;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.error.Mark;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class MarketPriceDtoConverterTest {


    public static final int ID = 123;
    public static final String MXN_PLN = "MXN/PLN";
    public static final BigDecimal ASK = BigDecimal.ONE;
    public static final BigDecimal BID = BigDecimal.TEN;
    public static final LocalDateTime TIMESTAMP = LocalDateTime.parse("2018-12-30T19:34:50.63");

    @Test
    public void shouldCOnvertCorrectly() {

        // given
        final MarketPriceInternalDO marketPriceInternalDto = getMarketPriceInternalDto();

        // when
        final MarketPriceDO result = MarketPriceDtoConverter.convertInternalToExternalDo(marketPriceInternalDto);

        // then
        assertThat(result.getId()).isEqualTo(ID);
        assertThat(result.getName()).isEqualTo(MXN_PLN);
        assertThat(result.getAsk()).isEqualTo(ASK);
        assertThat(result.getBid()).isEqualTo(BID);
        assertThat(result.getTimestamp()).isEqualTo(TIMESTAMP);
    }


    private MarketPriceInternalDO getMarketPriceInternalDto() {
        return MarketPriceInternalDO.builder()
                .id(ID)
                .name(MXN_PLN)
                .ask(ASK)
                .bid(BID)
                .timestamp(TIMESTAMP)
                .build();

    }

}