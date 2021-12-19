package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.web.request;

import java.util.Set;

public class RegisterOrderRequest {
    // TODO: validation
    public String zipCode;
    public String street;
    public Integer streetNumber;
    public Set<RegisterOrderRequestItem> items;

    public static class RegisterOrderRequestItem {
        public String name;
        public Integer count;
        public Set<String> options;
    }
}
