package nl.teamtwee.bep3.restaurant.kitchen.core.port.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

import nl.teamtwee.bep3.restaurant.kitchen.core.domain.Order;

import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {

}