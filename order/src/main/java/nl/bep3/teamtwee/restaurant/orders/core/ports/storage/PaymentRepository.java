package nl.bep3.teamtwee.restaurant.orders.core.ports.storage;

import java.util.UUID;

public interface PaymentRepository {
    UUID createPayment(UUID orderId, Long amount);

}
