package nl.bep3.teamtwee.restaurant.orders.core.ports.storage;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;

public interface OrderRepository extends MongoRepository<Order, UUID> {
}
