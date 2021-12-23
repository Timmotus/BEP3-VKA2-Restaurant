package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationRequest {
    private final UUID orderId;
    private final Map<String, Long> items;
}
