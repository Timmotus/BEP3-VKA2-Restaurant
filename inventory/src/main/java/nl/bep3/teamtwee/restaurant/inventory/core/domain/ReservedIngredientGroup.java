package nl.bep3.teamtwee.restaurant.inventory.core.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import nl.bep3.teamtwee.restaurant.inventory.core.domain.event.ReservedIngredientGroupEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document
public class ReservedIngredientGroup {

    @Id
    private UUID id;

    private HashMap<Ingredient, Integer> reservation = new HashMap<>();

    @Transient
    @Setter(value = AccessLevel.NONE)
    private final List<ReservedIngredientGroupEvent> events = new ArrayList<>();

    public ReservedIngredientGroup() {
        this.id = UUID.randomUUID();
    }

    // Event Methods
    public void clearEvents() {
        this.events.clear();
    }

    public List<ReservedIngredientGroupEvent> listEvents() {
        return events;
    }

}
