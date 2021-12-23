package nl.teamtwee.bep3.restaurant.delivery.core.port.storage;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;

public interface DeliveryRepository extends MongoRepository<Delivery, UUID>{
    Optional<Delivery> findOneByOrderId(UUID orderId);
}
