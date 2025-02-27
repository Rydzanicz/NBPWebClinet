package com.example.NBPWebClinet.NbpClient;

import com.example.NBPWebClinet.NbpClient.Currency.Currency;
import com.example.NBPWebClinet.NbpClient.Currency.CurrencyRate;
import com.example.NBPWebClinet.NbpClient.Currency.CurrencyRatesAllList;
import com.example.NBPWebClinet.NbpClient.Table_A_B.RateA_B;
import com.example.NBPWebClinet.NbpClient.Table_AllCurrency.RateAllCurrency;
import com.example.NBPWebClinet.NbpClient.Table_AllCurrency.RateEmbAllCurrency;
import com.example.NBPWebClinet.NbpClient.Table_All_C.RateAllC;
import com.example.NBPWebClinet.NbpClient.Table_All_C.RateEmbAllC;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class NBPClient {

    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/";
    private static final String NBP_API_URL_All_CURRENCY_A = "http://api.nbp.pl/api/exchangerates/tables/a";
    private static final String NBP_API_URL_All_CURRENCY_C = "http://api.nbp.pl/api/exchangerates/tables/c";
    private static final String FORMAT_JSON = "/?format=json";
    private static final String FORMAT_JSON_LAST_ONE_HUNDRED = "/last/100/?format=json/";
    private static final String SLASH = "/";

    public CurrencyRatesAllList getCurrencyExchangeRateMediumLastHundred(final Currency currency) throws IOException {

        final String jsonUrl = NBP_API_URL + NBPTable.A + SLASH + currency + FORMAT_JSON_LAST_ONE_HUNDRED;
        final URL url = new URL(jsonUrl);
        final InputStreamReader reader = new InputStreamReader(url.openStream());
        final CurrencyRatesAllList currencyRateList = new CurrencyRatesAllList();

        final RateA_B rateA_b = new Gson().fromJson(reader, RateA_B.class);
        final Collection<CurrencyRate> localCurrencyRate = rateA_b.getRates()
                                                                  .stream()
                                                                  .map(x -> new CurrencyRate(currency,
                                                                                             null,
                                                                                             null,
                                                                                             null,
                                                                                             LocalDate.parse(x.getEffectiveDate()),
                                                                                             x.getMid()))
                                                                  .collect(Collectors.toCollection(LinkedList::new));
        for (int x = 0; x < localCurrencyRate.size(); x++) {
            currencyRateList.addCurrencyRate(localCurrencyRate.stream()
                                                              .toList()
                                                              .get(x));

        }
        return currencyRateList;
    }

    private CurrencyRatesAllList getCurrencyExchangeAllBuyAndSell() throws IOException {
        final CurrencyRatesAllList currencyRateList = new CurrencyRatesAllList();

        final String jsonUrl = NBP_API_URL_All_CURRENCY_C + FORMAT_JSON;
        final URL url = new URL(jsonUrl);
        final InputStreamReader reader = new InputStreamReader(url.openStream());
        final RateAllC[] rateAllCS = new Gson().fromJson(reader, RateAllC[].class);
        final RateEmbAllC[] rateEmbC = rateAllCS[0].getRates();

        final LocalDate localDate = LocalDate.parse(rateAllCS[0].getEffectiveDate());

        for (RateEmbAllC rateEmbAllC : rateEmbC) {
            for (Currency currency : Currency.values()) {
                if (currency.name()
                            .equalsIgnoreCase(rateEmbAllC.getCode())) {
                    CurrencyRate localCurrencyRate = new CurrencyRate(rateEmbAllC.getCode(),
                                                                      localDate,
                                                                      rateEmbAllC.getBid(),
                                                                      rateEmbAllC.getAsk(),
                                                                      null,
                                                                      null);
                    currencyRateList.addCurrencyRate(localCurrencyRate);
                }
            }
        }

        return currencyRateList;
    }

    private CurrencyRatesAllList getNewCurrencyExchangeAll() throws IOException {
        final CurrencyRatesAllList currencyRateList = new CurrencyRatesAllList();

        final String jsonUrl = NBP_API_URL_All_CURRENCY_A + FORMAT_JSON;
        final URL url = new URL(jsonUrl);
        final InputStreamReader reader = new InputStreamReader(url.openStream());
        final Gson gson = new Gson();
        final RateAllCurrency[] rateAllCurrencies = gson.fromJson(reader, RateAllCurrency[].class);

        final RateEmbAllCurrency[] rateEmbAllCurrency = rateAllCurrencies[0].getRates();

        final LocalDate localDate = LocalDate.parse(rateAllCurrencies[0].getEffectiveDate());

        for (RateEmbAllCurrency embAllCurrency : rateEmbAllCurrency) {
            for (Currency currency : Currency.values()) {
                if (currency.name()
                            .equalsIgnoreCase(embAllCurrency.getCode())) {
                    CurrencyRate localCurrencyRate = new CurrencyRate(embAllCurrency.getCode(),
                                                                      null,
                                                                      null,
                                                                      null,
                                                                      localDate,
                                                                      embAllCurrency.getMid());
                    currencyRateList.addCurrencyRate(localCurrencyRate);
                }
            }
        }

        return currencyRateList;
    }

    public CurrencyRatesAllList getCurrencyExchangeRate() throws IOException {
        final CurrencyRatesAllList listMid = getNewCurrencyExchangeAll();
        final CurrencyRatesAllList listBuySell = getCurrencyExchangeAllBuyAndSell();
        final CurrencyRatesAllList rateList = new CurrencyRatesAllList();

        final int length = Math.max(listMid.getCurrencyRate()
                                           .size(),
                                    listBuySell.getCurrencyRate()
                                               .size());

        for (int i = 0; i < length; i++) {
            CurrencyRate localCurrencyRate = new CurrencyRate(listBuySell.getCurrencyRate()
                                                                         .get(i)
                                                                         .getCurrency()
                                                                         .name(),
                                                              listBuySell.getCurrencyRate()
                                                                         .get(i)
                                                                         .getExchangeRateDateForBuyAndSell(),
                                                              listBuySell.getCurrencyRate()
                                                                         .get(i)
                                                                         .getBuy(),
                                                              listBuySell.getCurrencyRate()
                                                                         .get(i)
                                                                         .getSell(),
                                                              listMid.getCurrencyRate()
                                                                     .get(i)
                                                                     .getExchangeRateDateForMid(),
                                                              listMid.getCurrencyRate()
                                                                     .get(i)
                                                                     .getMedium());
            rateList.addCurrencyRate(localCurrencyRate);
        }

        return rateList;
    }
}
