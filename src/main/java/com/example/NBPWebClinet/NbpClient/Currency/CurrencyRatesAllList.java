package com.example.NBPWebClinet.NbpClient.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrencyRatesAllList {

    private List<CurrencyRate> currencyRate;

    public CurrencyRatesAllList(final List<CurrencyRate> currencyRates) {
        this.currencyRate = currencyRates;
    }

    public CurrencyRatesAllList() {

        this.currencyRate = new ArrayList<>();
    }

    public List<CurrencyRate> getCurrencyRate() {
        return currencyRate;
    }

    public void addCurrencyRate(final CurrencyRate currencyRate) {
        this.currencyRate.add(currencyRate);
    }

    public void setCurrencyRateList(List<CurrencyRate> rates) {
        currencyRate = rates;
    }
}


