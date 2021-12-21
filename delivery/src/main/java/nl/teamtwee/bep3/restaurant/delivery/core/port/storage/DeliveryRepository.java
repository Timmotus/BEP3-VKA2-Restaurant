package nl.teamtwee.bep3.restaurant.delivery.core.port.storage;

import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface DeliveryRepository extends MongoRepository<Delivery, UUID>{
    List<Delivery> findDeliveryByDeliveryStatusEnum(Enum<DeliveryStatusEnum> deliveryStatusEnum);

}
