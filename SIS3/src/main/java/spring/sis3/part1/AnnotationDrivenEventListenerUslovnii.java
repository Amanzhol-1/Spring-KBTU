package spring.sis3.part1;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationDrivenEventListenerUslovnii {
    @EventListener(condition = "#event.success")
    public void handleSuccessful(GenericSpringEvent<String> event) {
        System.out.println("Handling generic event (conditional).");
    }
}
