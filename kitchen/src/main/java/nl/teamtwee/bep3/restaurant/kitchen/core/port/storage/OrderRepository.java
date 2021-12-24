package nl.teamtwee.bep3.restaurant.kitchen.core.port.storage;

import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import nl.teamtwee.bep3.restaurant.kitchen.core.domain.Order;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {
    List<Order> findByOrderStatus(OrderStatus status);
}