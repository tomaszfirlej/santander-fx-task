package com.santander.santanderfxtask.messaging;

import com.santander.santanderfxtask.repository.MarketPriceBE;
import com.santander.santanderfxtask.repository.MarketPricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.santander.santanderfxtask.converters.MarketPriceMessageConverter.DATE_TIME_FORMATTER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MarketPricesListenerImplTest {


    @Mock
    private MarketPricesRepository marketPricesRepository;

    @InjectMocks
    private MarketPricesListenerImpl testedClass;

    @Test
    public void shouldSaveIncomingPrice() {

        // given
        String message = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";

        // when
        testedClass.onMessage(message);

        // then
        ArgumentCaptor<MarketPriceBE> marketPriceBE = ArgumentCaptor.forClass(MarketPriceBE.class);
        verify(marketPricesRepository).savePrice(marketPriceBE.capture());
        assertThat(marketPriceBE.getValue().getId()).isEqualTo(106);
        assertThat(marketPriceBE.getValue().getName()).isEqualTo("EUR/USD");
        assertThat(marketPriceBE.getValue().getAsk()).isEqualTo(new BigDecimal("1.2000"));
        assertThat(marketPriceBE.getValue().getBid()).isEqualTo(new BigDecimal("1.1000"));
        assertThat(marketPriceBE.getValue().getTimestamp()).isEqualTo(LocalDateTime.parse("01-06-2020 12:01:01:001", DATE_TIME_FORMATTER));
    }
}