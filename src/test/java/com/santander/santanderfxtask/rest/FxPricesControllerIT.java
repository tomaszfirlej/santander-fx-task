package com.santander.santanderfxtask.rest;

import com.santander.santanderfxtask.exception.ApiErrorResponse;
import com.santander.santanderfxtask.messaging.MarketPricesListener;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FxPricesControllerIT {

    @LocalServerPort
    private int randomServerPort;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MarketPricesListener marketPricesListener;

    @Test
    public void shouldGetExceptionForNonExistingCurrencies() {

        //given
        // marketPricesListener.onMessage();

        // when
        ResponseEntity<ApiErrorResponse> response = testRestTemplate.getForEntity(
                urlPrefix() + "latestprice?firstCurrency=USD&secondCurrency=EUR",
                ApiErrorResponse.class);


        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(400));
        ApiErrorResponse body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.message()).isEqualTo("WRONG CURRENCY PAIR");
    }

    @Test
    public void shouldGetResponse() {

        //given
        String message = "106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001";
        marketPricesListener.onMessage(message);

        // when
        ResponseEntity<MarketPriceDO> response = testRestTemplate.getForEntity(
                urlPrefix() + "latestprice?firstCurrency=EUR&secondCurrency=USD",
                MarketPriceDO.class);


        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
        MarketPriceDO body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getAsk()).isEqualTo(new BigDecimal("1.2000").multiply(new BigDecimal("1.001")));
        assertThat(body.getBid()).isEqualTo(new BigDecimal("1.1000").multiply(new BigDecimal("0.999")));
    }

    private String urlPrefix() {
        return "http://localhost:" + randomServerPort + "/";
    }

}