package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.domain.MenuItem;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.MenuRepository;
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
        HttpEntity<List<String>> requestEntity = new HttpEntity<>(
                items,
                requestHeaders);

                System.out.println(requestEntity);

        // should also check if any items not found, deal with errors
        MenuItemResult results = this.client.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                MenuItemResult.class).getBody();

        if (results == null) {
            return new ArrayList<>();
        }
        List<MenuItem> res = new ArrayList<>();
        results.getItems().forEach((name, price) -> res.add(new MenuItem(name, price)));
        return res;
    }
}
