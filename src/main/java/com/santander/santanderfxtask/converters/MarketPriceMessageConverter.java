package com.santander.santanderfxtask.converters;

import com.santander.santanderfxtask.repository.MarketPriceBE;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class MarketPriceMessageConverter {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    public static MarketPriceBE convertMessageToEntity(String marketPriceMessage) {

        try {

            String[] res = marketPriceMessage.split(",");
            return MarketPriceBE.builder()
                    .id(Integer.valueOf(res[0].replaceAll("\\s+", "")))
                    .name(res[1].replaceAll("\\s+", ""))
                    .bid(new BigDecimal(res[2].replaceAll("\\s+", "")))
                    .ask(new BigDecimal(res[3].replaceAll("\\s+", "")))
                    .timestamp(LocalDateTime.parse(res[4], DATE_TIME_FORMATTER))
                    .build();

        } catch (Exception e) {
            log.error("There's been an exception while converting market price message.", e);
            throw e;
        }
    }

}
