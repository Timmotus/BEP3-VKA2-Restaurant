package nl.teamtwee.bep3.restaurant.kitchen.core.port.storage;

import java.util.List;

import nl.teamtwee.bep3.restaurant.kitchen.core.domain.MenuItem;

public interface MenuRepository {
    List<MenuItem> getMenuItemsByNames(List<String> names);
}
