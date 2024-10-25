package spring.sis3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Sis3Application {

    public static void main(String[] args) {
        SpringApplication.run(Sis3Application.class, args);
    }

}
