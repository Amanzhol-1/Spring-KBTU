package spring.sis3.part1;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class GenericSpringEventListener implements ApplicationListener<GenericSpringEvent<String>> {
    @Override
    public void onApplicationEvent(GenericSpringEvent<String> event) {
        System.out.println("Received spring generic event - " + event.getWhat());
    }
}

