package spring.sis3.part1;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionalEventListenerExample {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleCustom(CustomSpringEvent event) {
        System.out.println("Handling event inside a transaction BEFORE COMMIT.");
    }
}
