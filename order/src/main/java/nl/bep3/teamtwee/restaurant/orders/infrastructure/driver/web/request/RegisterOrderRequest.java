package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web.request;

import java.util.Map;

public class RegisterOrderRequest {
    // TODO: validation
    public String zipCode;
    public String street;
    public Long streetNumber;
    public Map<String, Long> itemCounts;
}
