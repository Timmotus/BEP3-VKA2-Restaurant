package nl.teamtwee.bep3.restaurant.payment.core.application.command;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EditPayment {
    private final UUID id;
    private final boolean payed;

    public EditPayment(UUID id, boolean payed) {
        this.id = id;
        this.payed = payed;
    }
}
