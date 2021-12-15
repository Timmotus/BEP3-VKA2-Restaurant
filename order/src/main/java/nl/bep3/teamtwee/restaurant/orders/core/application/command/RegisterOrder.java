package nl.bep3.teamtwee.restaurant.orders.core.application.command;

import java.util.Set;

public class RegisterOrder {
    private final String zipCode;
    private final String street;
    private final Integer streetNumber;
    private final String status;
    private final Set<String> contents;

    public RegisterOrder(String zipCode, String street, Integer streetNumber, String status, Set<String> contents) {
        this.zipCode = zipCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.status = status;
        this.contents = contents;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public String getStreet() {
        return this.street;
    }

    public Integer getStreetNumber() {
        return this.streetNumber;
    }

    public String getStatus() {
        return this.status;
    }

    public Set<String> getContents() {
        return this.contents;
    }
}
