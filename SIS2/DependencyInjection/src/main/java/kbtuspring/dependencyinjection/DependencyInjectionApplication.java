package kbtuspring.dependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import kbtuspring.dependencyinjection.model.Store;

@SpringBootApplication
public class DependencyInjectionApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);

        Store store = context.getBean(Store.class);

        if (store.getItem() != null) {
            System.out.println("Dependency injection successful. Item is not null.");
        } else {
            System.out.println("Dependency injection failed. Item is null.");
        }

        context.close();
    }
}
