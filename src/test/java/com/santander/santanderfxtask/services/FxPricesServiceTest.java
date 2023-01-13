package com.santander.santanderfxtask.services;

import com.santander.santanderfxtask.repository.MarketPriceBE;
import com.santander.santanderfxtask.repository.MarketPricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FxPricesServiceTest {

    public static final int ID = 123;
    @Mock
    private MarketPricesRepository marketPricesRepository;

    @InjectMocks
    private FxPricesService testedClass;

    @Test
    public void shouldReturnCorrectObject() {

        // given
        final String currencyPair = "EUR/USD";
        final BigDecimal ask = new BigDecimal("123.8"); // with 0.1% commission = 123.9238
        final BigDecimal bid = new BigDecimal("123.4"); // wit -0.1% commission = 123.2766
        final LocalDateTime timestamp = LocalDateTime.parse("2018-12-30T19:34:50.63");
        when(marketPricesRepository.getPrice(eq(currencyPair))).thenReturn(createMarketPriceBE(currencyPair, ask, bid, timestamp));

        // when
        MarketPriceInternalDO result = testedClass.getLatestPrice(currencyPair);

        // then
        assertThat(result.getId()).isEqualTo(ID);
        assertThat(result.getName()).isEqualTo(currencyPair);
        assertThat(result.getTimestamp()).isEqualTo(timestamp);
        assertThat(result.getBid()).isEqualTo(new BigDecimal("123.2766"));
        assertThat(result.getAsk()).isEqualTo(new BigDecimal("123.9238"));
    }


    private MarketPriceBE createMarketPriceBE(final String name,
                                              final BigDecimal ask,
                                              final BigDecimal bid,
                                              final LocalDateTime timestamp) {

        return MarketPriceBE.builder()
                .id(ID)
                .name(name)
                .ask(ask)
                .bid(bid)
                .timestamp(timestamp)
                .build();

    }


}