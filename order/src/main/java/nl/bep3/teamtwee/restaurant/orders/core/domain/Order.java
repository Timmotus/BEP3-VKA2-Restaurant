package nl.bep3.teamtwee.restaurant.orders.core.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;

import java.util.*;

@Document
public class Order {
    @Id
    private UUID id;

    private String zipCode;
    private String street;
    private Integer streetNumber;

    private UUID paymentId;
    private String status;

    private Set<OrderItem> items;

    @Transient
    private List<OrderEvent> events = new ArrayList<>();

    // temporary constructor TODO: REMOVE
    public Order() {
    }

    public Order(String zipCode, String street, Integer streetNumber) {
        this.id = UUID.randomUUID();
        this.zipCode = zipCode;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public List<OrderEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }

    // TODO: make a builder
}
