package kbtuspring.constructordependency.test;

import kbtuspring.constructordependency.configuration.Config;
import kbtuspring.constructordependency.model.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        Car car = context.getBean(Car.class);

        System.out.println("Car with engine: " + car.getEngine().getType() + " and transmission: " + car.getTransmission().getType());
    }
}

