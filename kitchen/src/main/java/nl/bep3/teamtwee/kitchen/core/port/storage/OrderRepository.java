package nl.bep3.teamtwee.kitchen.core.port.storage;

import nl.bep3.teamtwee.kitchen.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface OrderRepository extends MongoRepository<Order, UUID> {

}
