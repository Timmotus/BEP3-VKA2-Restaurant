package nl.bep3.teamtwee.restaurant.orders.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
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
    private String status;

    private Set<OrderItem> items;

    @Transient
    private List<OrderEvent> events;

    // temporary constructor TODO: REMOVE
    public Order() {
    }

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

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setStreetNumber(Integer streetNumber) {
            this.streetNumber = streetNumber;
        }

        public void setPaymentId(UUID paymentId) {
            this.paymentId = paymentId;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setItems(Set<OrderItem> items) {
            this.items = items;
        }

        public Order build() {
            return new Order(zipCode, street, streetNumber, paymentId, status, items);
        }
    }
}
