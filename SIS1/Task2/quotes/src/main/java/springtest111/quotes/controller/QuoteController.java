package springtest111.quotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springtest111.quotes.model.Quote;
import springtest111.quotes.service.QuoteService;

@RestController
public class QuoteController {
    @Autowired
    private QuoteService quoteService;

    @GetMapping("/api/random")
    public Quote getRandomQuote() {
        return quoteService.getRandomQuote();
    }
}
