package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResult {
    private UUID paymentId;
}
