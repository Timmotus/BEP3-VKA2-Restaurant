package nl.bep3.teamtwee.restaurant.orders.core.ports.storage;

import java.util.List;

import nl.bep3.teamtwee.restaurant.orders.core.domain.MenuItem;

public interface MenuRepository {
    List<MenuItem> getMenuItemsByName(List<String> items);
}
