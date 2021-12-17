package nl.bep3.teamtwee.restaurant.orders.core.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;

@Getter
@Document
public class Order {
    @Id
    private UUID id;

    private String zipCode;
    private String street;
    private Integer streetNumber;

    private UUID paymentId;
    @Setter
    private String status;

    private Set<OrderItem> items;

    @Transient
    private List<OrderEvent> events;

    public Order(String zipCode, String street, Integer streetNumber, UUID paymentId, String status,
            Set<OrderItem> items) {
        this.id = UUID.randomUUID();
        this.zipCode = zipCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.paymentId = paymentId;
        this.status = status;
        this.items = items;
        this.events = new ArrayList<>();
    }

    public List<OrderEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        private String zipCode;
        private String street;
        private Integer streetNumber;
        private UUID paymentId;
        private String status;
        private Set<OrderItem> items;

        public OrderBuilder() {
            this.items = new HashSet<>();
        }

        public OrderBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public OrderBuilder street(String street) {
            this.street = street;
            return this;
        }

        public OrderBuilder streetNumber(Integer streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public OrderBuilder paymentId(UUID paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public OrderBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderBuilder items(Set<OrderItem> items) {
            this.items = items;
            return this;
        }

        public OrderBuilder addItem(OrderItem orderItem) {
            this.items.add(orderItem);
            return this;
        }

        public Order build() {
            return new Order(zipCode, street, streetNumber, paymentId, status, items);
        }
    }
}
