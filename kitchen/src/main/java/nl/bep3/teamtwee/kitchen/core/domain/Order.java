package nl.bep3.teamtwee.kitchen.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.bep3.teamtwee.kitchen.core.domain.event.OrderEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class Order {
    @Id
    private UUID id;

    @Transient
    @Setter(value = AccessLevel.NONE)
    private final List<OrderEvent> events = new ArrayList<>();

    public Order() {
        this.id = UUID.randomUUID();
    }

    // Event Methods
    public void clearEvents() {
        this.events.clear();
    }
    public List<OrderEvent> listEvents() {
        return events;
    }
}
