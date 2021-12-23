package nl.teamtwee.bep3.restaurant.payment.core.application.query;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class GetPaymentById {
    private final UUID id;

    public GetPaymentById(UUID id) {
        this.id = id;
    }

}
