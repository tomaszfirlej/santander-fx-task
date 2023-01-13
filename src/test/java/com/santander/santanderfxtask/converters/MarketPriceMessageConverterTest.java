package com.santander.santanderfxtask.converters;

import com.santander.santanderfxtask.repository.MarketPriceBE;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.santander.santanderfxtask.converters.MarketPriceMessageConverter.DATE_TIME_FORMATTER;
import static org.assertj.core.api.Assertions.assertThat;

class MarketPriceMessageConverterTest {


    @Test
    public void shouldCorrectlyFormatMessage() {

        // given
        String message = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";

        // when
        MarketPriceBE marketPriceBE = MarketPriceMessageConverter.convertMessageToEntity(message);

        // then
        assertThat(marketPriceBE.getId()).isEqualTo(106);
        assertThat(marketPriceBE.getName()).isEqualTo("EUR/USD");
        assertThat(marketPriceBE.getAsk()).isEqualTo(new BigDecimal("1.2000"));
        assertThat(marketPriceBE.getBid()).isEqualTo(new BigDecimal("1.1000"));
        assertThat(marketPriceBE.getTimestamp()).isEqualTo(LocalDateTime.parse("01-06-2020 12:01:01:001", DATE_TIME_FORMATTER));
    }
}