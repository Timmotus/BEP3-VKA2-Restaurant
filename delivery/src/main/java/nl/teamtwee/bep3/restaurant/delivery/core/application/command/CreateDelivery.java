package nl.teamtwee.bep3.restaurant.delivery.core.application.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateDelivery {
    public UUID orderId;
}
