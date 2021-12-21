package nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.messaging.event;

import nl.teamtwee.bep3.restaurant.delivery.core.domain.DeliveryStatusEnum;
import java.time.Instant;
import java.util.UUID;

public class DeliveryKeywordEvent {
  public UUID eventId;
  public String eventKey;
  public Instant eventDate;
  public Enum<DeliveryStatusEnum> deliveryStatusEnum;
}
