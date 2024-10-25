package spring.sis3.part2;

import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.stereotype.Component;

@Component
class TransactionalListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void handleAfterCommit(UserCreatedEvent event) {
        System.out.println("User created event after commit for: " + event.getName());
    }
}

