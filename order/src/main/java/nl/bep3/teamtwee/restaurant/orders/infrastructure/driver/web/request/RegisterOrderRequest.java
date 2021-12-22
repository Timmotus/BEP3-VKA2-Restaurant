package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web.request;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;

public class RegisterOrderRequest {
    // TODO: validation
    public String zipCode;
    public String street;
    public Long streetNumber;
    @JsonAlias({ "items" })
    public Map<String, Long> itemCounts;
}
