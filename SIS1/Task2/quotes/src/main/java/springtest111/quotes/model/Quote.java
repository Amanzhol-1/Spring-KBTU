package springtest111.quotes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Quote {
    private String type;
    private Value value;
}
