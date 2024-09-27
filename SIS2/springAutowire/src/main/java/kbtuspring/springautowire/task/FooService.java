package kbtuspring.springautowire.task;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class FooService {

    @Autowired
    @Qualifier("fooFormatter")
    private Formatter formatter;

}
