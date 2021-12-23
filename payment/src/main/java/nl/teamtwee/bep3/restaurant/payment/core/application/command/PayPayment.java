package nl.teamtwee.bep3.restaurant.payment.core.application.command;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PayPayment {
    private final UUID id;
}
