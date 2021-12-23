package nl.teamtwee.bep3.restaurant.delivery.core.port.storage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatus;

public interface DeliveryRepository extends MongoRepository<Delivery, UUID> {
    Optional<Delivery> findOneByOrderId(UUID orderId);

    List<Delivery> findByStatus(DeliveryStatus status);
}
