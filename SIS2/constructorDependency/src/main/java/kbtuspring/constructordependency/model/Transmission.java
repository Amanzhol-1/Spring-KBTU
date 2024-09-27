package kbtuspring.constructordependency.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transmission {
    private String type;

    public Transmission(String type) {
        this.type = type;
    }
}
