package springtest111.basicwebservice.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import springtest111.basicwebservice.model.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    public Greeting makeGreeting(String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
