package com.example.NBPWebClinet.NbpClient;

import com.example.NBPWebClinet.NbpClient.Currency.Currency;
import com.example.NBPWebClinet.NbpClient.Currency.CurrencyRate;
import com.example.NBPWebClinet.NbpClient.Currency.CurrencyRatesAllList;
import com.example.NBPWebClinet.NbpClient.Table_C.RateC;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NBPClient {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/";
    private static final String FORMAT_JSON_LAST_ONE_HUNDRED = "/last/100?format=json";
    private static final String SLASH = "/";


    public CurrencyRatesAllList getCurrencyExchangeRateMediumLastHundred(final Currency currency) throws IOException {
        if (currency == null) {
            throw new IllegalArgumentException("Currency cannot be null.");
        }

        try {
            final String jsonUrl = NBP_API_URL + NBPTable.C + SLASH + currency + FORMAT_JSON_LAST_ONE_HUNDRED;
            final URL url = new URL(jsonUrl);

            try (InputStreamReader reader = new InputStreamReader(url.openStream())) {
                final RateC rateC = new Gson().fromJson(reader, RateC.class);
                return mapRatesToCurrencyRatesAllList(rateC, currency);
            }
        } catch (MalformedURLException e) {
            throw new IOException("Malformed URL for NBP API: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new IOException("Error while fetching data from NBP API: " + e.getMessage(), e);
        }
    }

    private CurrencyRatesAllList mapRatesToCurrencyRatesAllList(final RateC rateC, final Currency currency) {
        final CurrencyRatesAllList currencyRatesAllList = new CurrencyRatesAllList();

        final  List<CurrencyRate> rates = rateC.getRates()
                                        .stream()
                                        .map(rate -> new CurrencyRate(currency,
                                                                      LocalDate.parse(rate.getEffectiveDate()),
                                                                      rate.getBid(),
                                                                      rate.getAsk()))
                                        .collect(Collectors.toList());

        currencyRatesAllList.setCurrencyRateList(rates);
        return currencyRatesAllList;
    }
}