package kbtuspring.springautowire.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component("barFormatter")
@Getter
@Setter
public class BarFormatter implements Formatter {
    public String format() {
        return "Bar";
    }

}
