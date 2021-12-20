package nl.teamtwee.bep3.restaurant.payment.core.application.command;

import java.util.UUID;

public class ChangeJobFunction {
    private final UUID id;
    private final String function;

    public ChangeJobFunction(UUID id, String function) {
        this.id = id;
        this.function = function;
    }

    public UUID getId() {
        return id;
    }

    public String getFunction() {
        return function;
    }
}
