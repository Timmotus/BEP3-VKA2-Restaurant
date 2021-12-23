package nl.teamtwee.bep3.restaurant.delivery.core.application;

import nl.teamtwee.bep3.restaurant.delivery.core.application.query.GetAllDeliveries;
import nl.teamtwee.bep3.restaurant.delivery.core.application.query.GetDeliveryByOrderId;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.exception.DeliveryNotFoundException;
import nl.teamtwee.bep3.restaurant.delivery.core.port.storage.DeliveryRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryQueryHandler {
    private final DeliveryRepository repository;

    public List<Delivery> handle(GetAllDeliveries query) {
        return this.repository.findAll();
    }

    public Delivery handle(GetDeliveryByOrderId query) {
        return this.repository.findOneByOrderId(query.getOrderId()).orElseThrow(
                () -> new DeliveryNotFoundException(String.format("Delivery for orderId '{}' not found.", query.getOrderId())));
    }
}
