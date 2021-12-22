package nl.bep3.teamtwee.restaurant.orders.core.ports.storage;

import java.util.List;
import java.util.UUID;

public interface KitchenRepository {
    UUID createReservation(UUID orderId, List<String> items);
}
