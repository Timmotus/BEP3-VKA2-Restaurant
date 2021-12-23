package nl.teamtwee.bep3.restaurant.menu.core.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetAllMenuItems;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetMenuItemsByNames;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetPricesByNames;
import nl.teamtwee.bep3.restaurant.menu.core.domain.MenuItem;
import nl.teamtwee.bep3.restaurant.menu.core.domain.exception.MenuItemNotFoundException;
import nl.teamtwee.bep3.restaurant.menu.core.port.storage.MenuItemRepository;

@AllArgsConstructor
@Service
public class MenuQueryHandler {
    private final MenuItemRepository repository;

    public List<MenuItem> handle(GetAllMenuItems query) {
        return this.repository.findAll();
    }

    public List<MenuItem> handle(GetMenuItemsByNames query) {
        return this.repository.findByNameIn(query.getNames());
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
