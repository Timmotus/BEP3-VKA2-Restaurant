package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationRequest {
    private UUID orderId;
    private List<String> items;
}
