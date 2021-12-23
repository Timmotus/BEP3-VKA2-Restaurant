package nl.teamtwee.bep3.restaurant.menu.core.application;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.RegisterMenuItem;
import nl.teamtwee.bep3.restaurant.menu.core.domain.MenuItem;
import nl.teamtwee.bep3.restaurant.menu.core.port.storage.MenuItemRepository;

@AllArgsConstructor
@Service
public class MenuCommandHandler {
    private final MenuItemRepository repository;

    public MenuItem handle(RegisterMenuItem command) {
        MenuItem item = new MenuItem(command.getName(), command.getIngredients(), command.getPrice());
        this.repository.save(item);
        return item;
    }
}
