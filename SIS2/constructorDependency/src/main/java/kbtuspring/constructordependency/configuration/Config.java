package kbtuspring.constructordependency.configuration;

import kbtuspring.constructordependency.model.Engine;
import kbtuspring.constructordependency.model.Transmission;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("kbtuspring.constructordependency.model")
public class Config {

    @Bean
    public Engine engine() {
        return new Engine("v8", 5); // Assuming Engine constructor takes these arguments
    }

    @Bean
    public Transmission transmission() {
        return new Transmission("sliding"); // Assuming Transmission constructor takes these arguments
    }
}

