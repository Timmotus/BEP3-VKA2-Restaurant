package nl.teamtwee.bep3.restaurant.delivery.core.application;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.delivery.core.application.command.DeliveryDelivered;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatus;
import nl.teamtwee.bep3.restaurant.delivery.core.port.storage.DeliveryRepository;

@AllArgsConstructor
@Component
public class DelivererTask {
    private final DeliveryRepository repository;
    private final DeliveryCommandHandler commandHandler;
    private final long pollingRate = 10000;
    private final long deliverySpeedSeconds = 30;

    @Scheduled(fixedDelay = pollingRate)
    public void deliver() {
        List<Delivery> deliveries = this.repository.findByStatus(DeliveryStatus.DELIVERING);
        deliveries.forEach(delivery -> {
            LocalDateTime startedAt = delivery.getStartedAt();
            LocalDateTime deliveryThreshold = startedAt.plusSeconds(deliverySpeedSeconds);
            if (LocalDateTime.now().isAfter(deliveryThreshold)) {
                this.commandHandler.handle(new DeliveryDelivered(delivery.getId(), LocalDateTime.now()));
            }
        });
    }
}
