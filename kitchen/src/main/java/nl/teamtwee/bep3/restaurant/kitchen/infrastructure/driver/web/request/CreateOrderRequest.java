package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driver.web.request;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateOrderRequest {
    private final UUID orderId;
    private final Map<String, Long> items;
}
