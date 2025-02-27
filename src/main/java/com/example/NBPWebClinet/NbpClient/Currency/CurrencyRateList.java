package com.example.NBPWebClinet.NbpClient.Currency;

import java.util.List;

public class CurrencyRateList {
    private final Currency currency;
    private final List<CurrencyRate> currencyRate;

    public CurrencyRateList(final Currency currency, final List<CurrencyRate> currencyRates) {
        this.currency = currency;
        this.currencyRate = currencyRates;
    }

    public void addCurrencyRate(final CurrencyRate currencyRate) {
        this.currencyRate.add(currencyRate);
    }

    public Currency getCurrency() {
        return currency;
    }
}


