package spring.sis3.part2;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class UserCreatedListener {

    @EventListener
    void handleUserCreatedEvent(UserCreatedEvent event) {
        System.out.println("User created with name: " + event.getName());
    }
}

