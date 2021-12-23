package nl.teamtwee.bep3.restaurant.kitchen.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DeleteOrder {
    private final UUID id;
}