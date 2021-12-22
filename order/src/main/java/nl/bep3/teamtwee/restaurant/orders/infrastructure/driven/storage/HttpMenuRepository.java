package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.domain.MenuItem;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.MenuRepository;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto.MenuItemRequest;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto.MenuItemResult;

@AllArgsConstructor
public class HttpMenuRepository implements MenuRepository {
    private final String rootPath;
    private final RestTemplate client;

    @Override
    public List<MenuItem> getMenuItemsByName(List<String> items) {
        URI uri = URI.create(this.rootPath + "/menu/pizza/available");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<MenuItemRequest>> requestEntity = new HttpEntity<>(
                items.stream()
                        .map(item -> new MenuItemRequest(item))
                        .collect(Collectors.toList()),
                requestHeaders);

        // should also check if any items not found, deal with errors
        MenuItemResult[] results = this.client.exchange(
                uri,
                HttpMethod.GET,
                requestEntity,
                MenuItemResult[].class).getBody();

        if (results == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(results)
                .map(result -> new MenuItem(result.getName(), result.getPrice()))
                .collect(Collectors.toList());
    }
}
