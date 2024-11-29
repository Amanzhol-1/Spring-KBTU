package spring.part2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.consumingwebservice.wsdl.GetCountryResponse;

@SpringBootApplication
public class ConsumingWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumingWebServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient countryClient) {
        return args -> {
            String country = "Spain";
            if (args.length > 0) {
                country = args[0];
            }
            GetCountryResponse response = countryClient.getCountry(country);
            System.out.println("Country: " + response.getCountry().getName());
            System.out.println("Capital: " + response.getCountry().getCapital());
            System.out.println("Currency: " + response.getCountry().getCurrency());
            System.out.println("Population: " + response.getCountry().getPopulation());
        };
    }
}
