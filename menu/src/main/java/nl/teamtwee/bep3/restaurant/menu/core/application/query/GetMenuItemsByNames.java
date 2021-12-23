package nl.teamtwee.bep3.restaurant.menu.core.application.query;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetMenuItemsByNames {
    private final List<String> names;
}
