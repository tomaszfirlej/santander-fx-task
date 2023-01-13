package com.santander.santanderfxtask.repository;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
public class MarketPriceBE {

    private Integer id;
    // instrument name
    private String name;
    private BigDecimal bid;
    private BigDecimal ask;
    private LocalDateTime timestamp;
}
