package nl.teamtwee.bep3.restaurant.menu.core.application;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.RegisterMenuItem;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetPricesByNames;
import nl.teamtwee.bep3.restaurant.menu.core.domain.MenuItem;
import nl.teamtwee.bep3.restaurant.menu.core.domain.exception.MenuItemNotFoundException;
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

    public Map<String, Long> handle(GetPricesByNames command) {
        Map<String, Long> items = new HashMap<>();
        for (String name : command.getNames()) {
            MenuItem item = this.repository.findByName(name)
                    .orElseThrow(() -> new MenuItemNotFoundException(name));
            items.put(name, Double.valueOf(item.getPrice()).longValue());
        }
        return items;
    }
}
