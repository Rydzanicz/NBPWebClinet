package com.example.NBPWebClinet.NbpClient.Currency;

import java.time.LocalDate;

public class CurrencyRate {
    private final Currency currency;
    private LocalDate exchangeRateDateForBuyAndSell;
    private Double buy;
    private Double sell;


    public CurrencyRate(final Currency currency, final LocalDate exchangeRateDateForBuyAndSell, final Double buy, final Double sell) {
        this.currency = currency;
        this.exchangeRateDateForBuyAndSell = exchangeRateDateForBuyAndSell;
        this.buy = buy;
        this.sell = sell;
    }

    public CurrencyRate(final String currency, final LocalDate exchangeRateDateForBuyAndSell, final Double buy, final Double sell) {
        this.currency = Currency.valueOf(currency);
        this.exchangeRateDateForBuyAndSell = exchangeRateDateForBuyAndSell;
        this.buy = buy;
        this.sell = sell;

    }

    public CurrencyRate() {
        this.currency = null;
        this.exchangeRateDateForBuyAndSell = null;
        this.buy = null;
        this.sell = null;
    }

    public CurrencyRate(final Currency currency) {
        this.currency = currency;
        this.exchangeRateDateForBuyAndSell = null;
        this.buy = null;
        this.sell = null;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setBuy(final Double buy) {
        this.buy = buy;
    }

    public Double getBuy() {
        return buy;
    }

    public void setSell(final Double sell) {
        this.sell = sell;
    }

    public Double getSell() {
        return sell;
    }

    public void setExchangeRateDateForBuyAndSell(final String exchangeRateDate) {
        this.exchangeRateDateForBuyAndSell = LocalDate.parse(exchangeRateDate);
    }

    public LocalDate getExchangeRateDateForBuyAndSell() {
        return exchangeRateDateForBuyAndSell;
    }
}


