package work.financeflowtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.financeflowtracker.service.CurrencyService;

@RestController
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    // Эндпоинт для обновления курсов валют
    @GetMapping("/update-currency-rates")
    public String updateCurrencyRates() {
        currencyService.updateDailyRates();
        return "Currency rates updated";
    }
}
