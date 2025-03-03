package com.example.NBPWebClinet.controler;

import com.example.NBPWebClinet.NbpClient.Currency.Currency;
import com.example.NBPWebClinet.NbpClient.Currency.CurrencyRate;
import com.example.NBPWebClinet.NbpClient.Currency.CurrencyRatesAllList;
import com.example.NBPWebClinet.NbpClient.NBPClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CalculationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CalculationController.class);

    final NBPClient nbpClient;

    public CalculationController(final NBPClient nbpClient) {
        this.nbpClient = nbpClient;
    }

    @GetMapping(value = "/get-last-hundred", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getLastHundred(@RequestParam String currency,
                                                 @RequestParam(required = false, defaultValue = "ASC") String sortDirection,
                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 @RequestParam(required = false) String startDate,
                                                 @RequestParam(required = false) String endDate) {
        try {
            if (currency == null || currency.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("Error: 'currency' parameter is required and cannot be empty.");
            }

            if (currency.equalsIgnoreCase("PLN")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                     .body("Error: Currency 'PLN' is not supported.");
            }
            Optional<LocalDate> startLocalDate = Optional.ofNullable(startDate)
                                                         .map(LocalDate::parse);
            Optional<LocalDate> endLocalDate = Optional.ofNullable(endDate)
                                                       .map(LocalDate::parse);
            Currency newCurrency;
            try {
                newCurrency = Currency.valueOf(currency.toUpperCase());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                     .body("Error: Invalid currency value: " + currency);
            }

            final CurrencyRatesAllList currencyRatesAllList = nbpClient.getCurrencyExchangeRateMediumLastHundred(newCurrency);

            final List<CurrencyRate> filteredSortedPaginatedData = applyFiltersSortAndPagination(currencyRatesAllList.getCurrencyRate(),
                                                                                                 sortDirection,
                                                                                                 page,
                                                                                                 size,
                                                                                                 startLocalDate,
                                                                                                 endLocalDate);

            final Map<String, Object> response = new HashMap<>();
            response.put("currentPage", page);
            response.put("pageSize", size);
            response.put("totalItems",
                         currencyRatesAllList.getCurrencyRate()
                                             .size());
            response.put("totalPages",
                         (int) Math.ceil((double) currencyRatesAllList.getCurrencyRate()
                                                                      .size() / size));
            response.put("data", filteredSortedPaginatedData);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            LOGGER.error("Error occurred while processing getLastHundred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error: Unable to process the request. Please try again later.");
        }
    }

    private List<CurrencyRate> applyFiltersSortAndPagination(final List<CurrencyRate> data,
                                                             final String sortDirection,
                                                             final int page,
                                                             final int size,
                                                             final Optional<LocalDate> startDate,
                                                             final Optional<LocalDate> endDate) {
        return data.stream()
                   .filter(rate -> applyStartDateFilter(rate, startDate))
                   .filter(rate -> applyEndDateFilter(rate, endDate))
                   .sorted(getComparator(sortDirection))
                   .skip((long) page * size)
                   .limit(size)
                   .toList();
    }

    private boolean applyStartDateFilter(final CurrencyRate rate, final Optional<LocalDate> startDate) {
        if (startDate.isEmpty()) {
            return true;
        }
        return !rate.getExchangeRateDateForBuyAndSell()
                    .isBefore(startDate.get());
    }

    private boolean applyEndDateFilter(final CurrencyRate rate, final Optional<LocalDate> endDate) {
        if (endDate.isEmpty()) {
            return true;
        }
        return !rate.getExchangeRateDateForBuyAndSell()
                    .isAfter(endDate.get());
    }

    private Comparator<CurrencyRate> getComparator(final String sortDirection) {
        Comparator<CurrencyRate> comparator;

        comparator = Comparator.comparing(CurrencyRate::getExchangeRateDateForBuyAndSell);
        if ("DESC".equalsIgnoreCase(sortDirection)) {
            comparator = comparator.reversed();
        }

        return comparator;
    }
}