package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.MenuItem;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.storage.MenuRepository;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage.dto.MenuItemResult;

@AllArgsConstructor
public class HttpMenuRepository implements MenuRepository {
    private final String rootPath;
    private final RestTemplate client;

    @Override
    public List<MenuItem> getMenuItemsByNames(List<String> names) {
        URI uri = URI.create(this.rootPath + "/menu/items?names=" + String.join(",", names));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        // should also check if any items not found, deal with errors
        MenuItemResult[] result = this.client.exchange(
                uri,
                HttpMethod.GET,
                null,
                MenuItemResult[].class).getBody();

        return Arrays.stream(result)
                .map(item -> new MenuItem(item.getId(), item.getName(), item.getIngredients()))
                .collect(Collectors.toList());
    }
}