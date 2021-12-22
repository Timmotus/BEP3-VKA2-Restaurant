package nl.bep3.teamtwee.restaurant.orders.core.application.command;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterOrder {
    private String zipCode;
    private String street;
    private Long streetNumber;
    private Map<String, Long> itemCounts;
}
