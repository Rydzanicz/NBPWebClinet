package com.example.NBPWebClinet;

import com.example.NBPWebClinet.NbpClient.Currency.Currency;
import com.example.NBPWebClinet.NbpClient.Currency.CurrencyRate;
import com.example.NBPWebClinet.NbpClient.Currency.CurrencyRatesAllList;
import com.example.NBPWebClinet.NbpClient.NBPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;

public class Calculation {
    private final Currency selectHaveCurrency;
    private final Currency selectGetCurrency;
    private final Double amount;
    private final Double commission;
    private final NBPClient clientNBP;
    private CurrencyRatesAllList currencyRateList;
    private static final Logger logger = LogManager.getLogger(Calculation.class);

    public Calculation(final Currency selectHaveCurrency, final Currency selectGetCurrency, final Double amount, final Double commission) {
        this.selectGetCurrency = selectGetCurrency;
        this.selectHaveCurrency = selectHaveCurrency;
        this.amount = amount;
        this.commission = commission;
        this.clientNBP = new NBPClient();
        this.currencyRateList = new CurrencyRatesAllList();
        try {
            currencyRateList = clientNBP.getCurrencyExchangeRate();

        } catch (IOException e) {
            logger.error("Nie udało się pobrać dane");
        }
    }

    public Calculation(final Currency selectHaveCurrency, final Currency selectGetCurrency) {
        this.selectGetCurrency = selectGetCurrency;
        this.selectHaveCurrency = selectHaveCurrency;
        this.clientNBP = new NBPClient();
        this.currencyRateList = new CurrencyRatesAllList();
        this.amount = 1.0;
        this.commission = 0.05;
        try {
            currencyRateList = clientNBP.getCurrencyExchangeRate();

        } catch (IOException e) {
            logger.error("Nie udało się pobrać dane");
        }
    }

    public double getCalculation() {
        double currencyRat = getCurrencyRate();
        double commissionLocal = calculationCommission(currencyRat);
        return amount * currencyRat - commissionLocal;
    }


    public double getCurrencyRate() {

        final Optional<CurrencyRate> getCurrencyRate = getCurrencyRate(this.selectGetCurrency);
        final Optional<CurrencyRate> haveCurrencyRate = getCurrencyRate(this.selectHaveCurrency);

        if (selectGetCurrency.equals(selectHaveCurrency)) {
            return amount;
        }

        if (selectHaveCurrency.equals(Currency.PLN) && getCurrencyRate.map(CurrencyRate::getBuy)
                                                                      .isPresent()) {
            return getCurrencyRate.map(CurrencyRate::getBuy)
                                  .get();
        }

        if (selectGetCurrency.equals(Currency.PLN) && haveCurrencyRate.map(CurrencyRate::getSell)
                                                                      .isPresent()) {
            return haveCurrencyRate.map(CurrencyRate::getSell)
                                   .get();
        }

        return getCurrencyRate.map(CurrencyRate::getBuy)
                              .get() / haveCurrencyRate.map(CurrencyRate::getSell)
                                                       .get();
    }

    private double calculationCommission(final double currencyRat) {
        return currencyRat * amount * commission;
    }

    private Optional<CurrencyRate> getCurrencyRate(final Currency currency) {
        return currencyRateList.getCurrencyRate()
                               .stream()
                               .filter(x -> x.getCurrency()
                                             .equals(currency))
                               .findFirst();
    }
}
