package kbtuspring.constructordependency;

import kbtuspring.constructordependency.model.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConstructorDependencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConstructorDependencyApplication.class, args);
    }

}
