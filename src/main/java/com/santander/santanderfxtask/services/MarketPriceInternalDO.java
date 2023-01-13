package com.santander.santanderfxtask.services;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MarketPriceInternalDO {

    private Integer id;
    // instrument name
    private String name;
    private BigDecimal bid;
    private BigDecimal ask;
    private LocalDateTime timestamp;
}
