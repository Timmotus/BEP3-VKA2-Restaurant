package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MenuItemEvent {
    private UUID eventId;
    private String eventKey;
    private Instant eventDate;
}
