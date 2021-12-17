package nl.bep3.teamtwee.restaurant.orders.core.application.command;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RegisterOrder {
    private String zipCode;
    private String street;
    private Integer streetNumber;
    private Set<RegisterOrderItem> items;

    public RegisterOrder(String zipCode, String street, Integer streetNumber) {
        this.zipCode = zipCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.items = new HashSet<>();
    }

    public void addItem(String name, Integer count, Set<String> options) {
        this.items.add(new RegisterOrderItem(name, count, options));
    }

    @Getter
    @AllArgsConstructor
    public class RegisterOrderItem {
        private String name;
        private Integer count;
        private Set<String> options;
    }
}
