package nl.teamtwee.bep3.restaurant.kitchen.core.application.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class GetOrderById {
    private final UUID id;
}