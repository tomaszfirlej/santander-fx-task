package com.santander.santanderfxtask.exception;

public class CurrencyPairUnrecognizedException extends RuntimeException {

    public CurrencyPairUnrecognizedException(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    private String currencyPair;

}
