package nl.bep3.teamtwee.restaurant.orders.core.ports.storage;

import java.util.Map;
import java.util.UUID;

public interface KitchenRepository {
    UUID createReservation(UUID orderId, Map<String, Long> items);
}
