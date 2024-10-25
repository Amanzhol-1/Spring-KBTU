package spring.sis3.part2;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class UserCreatedEvent {
    private String name;

    UserCreatedEvent(String name) {
        this.name = name;
    }
}
