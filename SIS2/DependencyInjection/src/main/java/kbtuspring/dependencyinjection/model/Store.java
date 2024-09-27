package kbtuspring.dependencyinjection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
public class Store {
    @Autowired
    private Item item;

    public Store(Item item) {
        this.item = item;
    }

    //setter
    public void setItem(Item item) {
        this.item = item;
    }
}