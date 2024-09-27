package kbtuspring.constructordependency.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Engine {
    private String type;
    private int cylinders;

    public Engine(String type, int cylinders) {
        this.type = type;
        this.cylinders = cylinders;
    }
}
