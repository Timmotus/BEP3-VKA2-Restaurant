package nl.teamtwee.bep3.restaurant.menu.core.domain.exception;

import lombok.Getter;

@Getter
public class MenuItemNotFoundException extends RuntimeException {
    private String name;

    public MenuItemNotFoundException(String name) {
        super(String.format("MenuItem '{}' not found", name));
        this.name = name;
    }
}
