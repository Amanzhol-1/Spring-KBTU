package spring.sis3.part2;

import org.springframework.scheduling.annotation.Async;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class AsyncListener {

    @Async
    @EventListener
    void handleAsyncEvent(UserCreatedEvent event) {
        System.out.println("Asynchronously handling user created event for: " + event.getName());
    }
}

