package com.example.NBPWebClinet.NbpClient.Currency;

import java.time.LocalDate;

public class CurrencyRate {
    private final Currency currency;
    private LocalDate exchangeRateDateForBuyAndSell;
    private Double buy;
    private Double sell;
    private LocalDate exchangeRateDateForMid;
    private Double medium;

    public CurrencyRate(final Currency currency,
                        final LocalDate exchangeRateDateForBuyAndSell,
                        final Double buy,
                        final Double sell,
                        final LocalDate exchangeRateDateForMid,
                        final Double medium) {
        this.currency = currency;
        this.exchangeRateDateForBuyAndSell = exchangeRateDateForBuyAndSell;
        this.exchangeRateDateForMid = exchangeRateDateForMid;
        this.buy = buy;
        this.sell = sell;
        this.medium = medium;
    }

    public CurrencyRate(final String currency,
                        final LocalDate exchangeRateDateForBuyAndSell,
                        final Double buy,
                        final Double sell,
                        final LocalDate exchangeRateDateForMid,
                        final Double medium) {
        this.currency = Currency.valueOf(currency);
        this.exchangeRateDateForBuyAndSell = exchangeRateDateForBuyAndSell;
        this.exchangeRateDateForMid = exchangeRateDateForMid;
        this.buy = buy;
        this.sell = sell;
        this.medium = medium;


    }

    public CurrencyRate() {
        this.currency = null;
        this.exchangeRateDateForBuyAndSell = null;
        this.exchangeRateDateForMid = null;
        this.buy = null;
        this.sell = null;
        this.medium = null;


    }

    public CurrencyRate(final Currency currency) {
        this.currency = currency;
        this.exchangeRateDateForBuyAndSell = null;
        this.exchangeRateDateForMid = null;
        this.buy = null;
        this.sell = null;
        this.medium = null;

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

    public void setMedium(final Double medium) {
        this.medium = medium;
    }

    public Double getMedium() {
        return medium;
    }

    public void setExchangeRateDateForBuyAndSell(final String exchangeRateDate) {
        this.exchangeRateDateForBuyAndSell = LocalDate.parse(exchangeRateDate);
    }

    public LocalDate getExchangeRateDateForBuyAndSell() {
        return exchangeRateDateForBuyAndSell;
    }

    public void setExchangeRateDateForMid(final String exchangeRateDate) {
        this.exchangeRateDateForMid = LocalDate.parse(exchangeRateDate);
    }

    public LocalDate getExchangeRateDateForMid() {
        return exchangeRateDateForMid;
    }
}


