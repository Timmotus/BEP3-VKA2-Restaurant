package nl.bep3.teamtwee.restaurant.orders.core.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderEvent;
import nl.bep3.teamtwee.restaurant.orders.core.domain.event.OrderInitiateDelivery;

@Getter
@Document
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    @Id
    private final UUID id;
    private UUID paymentId;
    private UUID reservationId;

    private String zipCode;
    private String street;
    private Long streetNumber;

    private String status;

    private Map<String, OrderItem> items;

    @Transient
    private List<OrderEvent> events;

    public List<OrderEvent> listEvents() {
        return this.events;
    }

    public void clearEvents() {
        this.events.clear();
    }

    public void completePayment() {
        this.status = "PAYMENT_COMPLETE";
        this.events.add(new OrderInitiateDelivery(this.id, "READY_TO_BE_DELIVERED"));
    }

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {
        @Getter
        private UUID id;
        private UUID paymentId;
        private UUID reservationId;

        private String zipCode;
        private String street;
        private Long streetNumber;
        private String status;
        private Map<String, OrderItem> items;

        public OrderBuilder() {
            this.id = UUID.randomUUID();
            this.items = new HashMap<>();
        }

        public OrderBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public OrderBuilder paymentId(UUID paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public OrderBuilder reservationId(UUID reservationId) {
            this.reservationId = reservationId;
            return this;
        }

        public OrderBuilder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public OrderBuilder street(String street) {
            this.street = street;
            return this;
        }

        public OrderBuilder streetNumber(Long streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public OrderBuilder status(String status) {
            this.status = status;
            return this;
        }

        public OrderBuilder items(HashMap<String, OrderItem> items) {
            this.items = items;
            return this;
        }

        public OrderBuilder addItem(String name, Double price, Long count) {
            this.items.put(name, new OrderItem(price, count));
            return this;
        }

        public Order build() {
            return new Order(
                    this.id,
                    this.paymentId,
                    this.reservationId,
                    this.zipCode,
                    this.street,
                    this.streetNumber,
                    this.status,
                    this.items, new ArrayList<>());
        }
    }
}
