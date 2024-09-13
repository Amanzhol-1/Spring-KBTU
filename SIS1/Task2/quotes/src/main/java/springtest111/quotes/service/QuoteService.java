package springtest111.quotes.service;

import org.springframework.stereotype.Service;
import springtest111.quotes.model.Quote;
import springtest111.quotes.model.Value;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class QuoteService {
    private String[] quotes = {"Quote 1", "Quote 2", "Quote 3"};
    private Random random = new Random();
    private AtomicLong counter = new AtomicLong();

    public Quote getRandomQuote() {
        Quote quote = new Quote();
        Value value = new Value();

        value.setId(counter.incrementAndGet()); // Генерация уникального ID
        value.setQuote(quotes[random.nextInt(quotes.length)]);

        quote.setType("success");
        quote.setValue(value);

        return quote;
    }
}
