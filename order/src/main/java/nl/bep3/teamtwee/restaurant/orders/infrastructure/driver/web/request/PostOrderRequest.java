package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web.request;

import java.util.Set;
import java.util.UUID;

public class PostOrderRequest {
    // TODO: validation
    public String zipCode;
    public String street;
    public Integer streetNumber;

    public UUID paymentId;
    public String status;

    public Set<String> contents;
}
