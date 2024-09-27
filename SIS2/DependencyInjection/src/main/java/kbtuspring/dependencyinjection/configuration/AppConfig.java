package kbtuspring.dependencyinjection.configuration;

import kbtuspring.dependencyinjection.model.Item;
import kbtuspring.dependencyinjection.model.ItemImpl;
import kbtuspring.dependencyinjection.model.Store;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Item item() {
        return new ItemImpl();
    }

//    @Bean
//    public Store store() {
//        return new Store(item());
//    }
//
//    @Bean
//    public Store store() {
//        Store store = new Store();
//        store.setItem(item());
//        return store;
//    }

    @Bean
    public Store store() {
        return new Store();
    }
}
