package com.santander.santanderfxtask.rest;

import com.santander.santanderfxtask.services.FxPricesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.santander.santanderfxtask.converters.MarketPriceDtoConverter.convertInternalToExternalDo;

@RestController()
@Slf4j
public class FxPricesController {

    private final FxPricesService fxPricesService;

    public FxPricesController(final FxPricesService fxPricesService) {
        this.fxPricesService = fxPricesService;
    }

    @RequestMapping("/latestprice")
    public MarketPriceDO getLatestPrice(@RequestParam String firstCurrency, @RequestParam String secondCurrency) {
        return convertInternalToExternalDo(fxPricesService.getLatestPrice(firstCurrency + "/" + secondCurrency));
    }
}
