package spring.sis3.part1;

import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class GenericSpringEvent<T> extends ApplicationEvent {
    private T what;
    protected boolean success;

    public GenericSpringEvent(Object source, T what, boolean success) {
        super(source);
        this.what = what;
        this.success = success;
    }

    public T getWhat() {
        return what;
    }

    public boolean isSuccess() {
        return success;
    }
}

