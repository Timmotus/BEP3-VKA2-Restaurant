package nl.teamtwee.bep3.restaurant.kitchen.core.application.command;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadOrder {
    private final UUID orderId;
    private final Map<String, Long> items;
}