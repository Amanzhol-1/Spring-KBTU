package kbtuspring.springautowire.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component("fooFormatter")
@Getter
@Setter
public class FooFormatter implements Formatter{
    public String format() {
        return "foo";
    }
}
