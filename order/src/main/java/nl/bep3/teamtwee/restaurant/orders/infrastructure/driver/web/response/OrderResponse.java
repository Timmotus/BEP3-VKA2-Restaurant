package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web.response;

import java.util.Map;
import java.util.UUID;

import lombok.Getter;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;
import nl.bep3.teamtwee.restaurant.orders.core.domain.OrderItem;

@Getter
public class OrderResponse {
    private UUID id;
    private UUID paymentId;
    private UUID reservationId;

    private String zipCode;
    private String street;
    private Long streetNumber;
    private String status;
    private Map<String, OrderItem> items;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.paymentId = order.getPaymentId();
        this.reservationId = order.getReservationId();
        this.zipCode = order.getZipCode();
        this.street = order.getStreet();
        this.streetNumber = order.getStreetNumber();
        this.status = order.getStatus();
        this.items = order.getItems();
    }
}
