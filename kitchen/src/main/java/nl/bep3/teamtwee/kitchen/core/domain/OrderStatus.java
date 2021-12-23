package nl.bep3.teamtwee.kitchen.core.domain;

public enum OrderStatus {
    RECEIVED,
    PREPARATION,
    BAKING,
    READY,
    COMPLETE;

    private static final OrderStatus[] values = values();
    public OrderStatus next() {
        return values[(this.ordinal()+1) % values.length];
    }
}