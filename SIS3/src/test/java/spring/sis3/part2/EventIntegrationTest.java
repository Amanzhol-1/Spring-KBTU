package spring.sis3.part2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RecordApplicationEvents
class EventIntegrationTest {

    @Autowired
    private Publisher publisher;

    @Test
    void testUserCreatedEvent_SynchronousListener(ApplicationEvents applicationEvents) {
        publisher.publishEvent("TestUser");

        assertTrue(applicationEvents.stream(UserCreatedEvent.class)
                        .anyMatch(event -> event.getName().equals("TestUser")),
                "UserCreatedEvent should have been published and handled.");
    }

    @Test
    void testAsyncListener(ApplicationEvents applicationEvents) {
        publisher.publishEvent("AsyncUser");

        assertTrue(applicationEvents.stream(UserCreatedEvent.class)
                        .anyMatch(event -> event.getName().equals("AsyncUser")),
                "UserCreatedEvent should have been handled asynchronously.");
    }
}
