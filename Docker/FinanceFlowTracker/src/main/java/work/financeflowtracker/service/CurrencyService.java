package work.financeflowtracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import work.financeflowtracker.entity.CurrencyRate;
import work.financeflowtracker.repository.CurrencyRateRepository;
import work.financeflowtracker.dto.CurrencyRatesResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CurrencyService {

    private final CurrencyRateRepository currencyRateRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(CurrencyRateRepository currencyRateRepository, RestTemplate restTemplate) {
        this.currencyRateRepository = currencyRateRepository;
        this.restTemplate = restTemplate;
    }

    // Метод для получения курса валюты на дату транзакции или последнюю доступную дату
    public BigDecimal getExchangeRate(String currencyCode, LocalDate date) {
        Optional<CurrencyRate> optionalRate = currencyRateRepository.findByCurrencyAndDate(currencyCode, date);

        if (optionalRate.isPresent()) {
            return optionalRate.get().getRate();
        } else {
            CurrencyRate latestRate = currencyRateRepository.findTopByCurrencyAndDateBeforeOrderByDateDesc(currencyCode, date);
            if (latestRate != null) {
                return latestRate.getRate();
            } else {
                throw new RuntimeException("Нет доступных курсов для валюты: " + currencyCode);
            }
        }
    }

    // Метод для обновления курсов валют (ежедневно)
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateDailyRates() {
        String apiKey = "YOUR API KEY";
        String[] currencies = {"KZT", "RUB"};

        for (String currency : currencies) {
            String symbol = "USD/" + currency;
            String url = "https://api.twelvedata.com/time_series?symbol=" + symbol + "&interval=1day&apikey=" + apiKey + "&format=JSON&outputsize=1";

            try {
                CurrencyRatesResponse response = restTemplate.getForObject(url, CurrencyRatesResponse.class);

                if (response != null && response.getValues() != null && !response.getValues().isEmpty()) {
                    saveRates(response, currency);
                } else {
                    usePreviousCloseRate(currency);
                }
            } catch (Exception e) {
                System.err.println("Error fetching currency rates for " + currency + ": " + e.getMessage());
                usePreviousCloseRate(currency);
            }
        }
    }

    private void saveRates(CurrencyRatesResponse response, String currency) {
        LocalDate rateDate = LocalDate.now();
        CurrencyRatesResponse.Value latestValue = response.getValues().get(0);

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrency(currency);
        currencyRate.setRate(new BigDecimal(latestValue.getClose()));
        currencyRate.setDate(rateDate);

        currencyRateRepository.save(currencyRate);
        System.out.println("Saved rate for " + currency + ": " + latestValue.getClose());
    }

    private void usePreviousCloseRate(String currency) {
        LocalDate today = LocalDate.now();
        CurrencyRate lastRate = currencyRateRepository.findTopByCurrencyAndDateBeforeOrderByDateDesc(currency, today);
        if (lastRate != null) {
            CurrencyRate newRate = new CurrencyRate();
            newRate.setCurrency(currency);
            newRate.setRate(lastRate.getRate());
            newRate.setDate(today);
            currencyRateRepository.save(newRate);
            System.out.println("Used previous close rate for " + currency + ": " + lastRate.getRate());
        } else {
            System.err.println("No previous rate available for currency: " + currency);
        }
    }
}
