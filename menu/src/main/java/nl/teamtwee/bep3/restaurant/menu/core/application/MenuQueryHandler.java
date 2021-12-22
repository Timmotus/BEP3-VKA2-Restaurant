package nl.teamtwee.bep3.restaurant.menu.core.application;

import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetAllMenuItems;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetMenuItemByName;
import nl.teamtwee.bep3.restaurant.menu.core.domain.MenuItem;
import nl.teamtwee.bep3.restaurant.menu.core.domain.exception.MenuItemNotFoundException;
import nl.teamtwee.bep3.restaurant.menu.core.port.storage.MenuItemRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Service
public class MenuQueryHandler {
    private final MenuItemRepository repository;

    public List<MenuItem> handle(GetAllMenuItems query) {
        return this.repository.findAll();
    }

    public MenuItem handle(GetMenuItemByName query) {
        return this.repository.findByName(query.getName())
                .orElseThrow(() -> new MenuItemNotFoundException("MenuItem '" + query.getName() + "' not found"));
    }
}
