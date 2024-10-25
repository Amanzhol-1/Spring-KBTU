package spring.sis3.part2;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class Publisher {

    private final ApplicationEventPublisher publisher;

    Publisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    void publishEvent(final String name) {
        publisher.publishEvent(new UserCreatedEvent(name));
    }
}
