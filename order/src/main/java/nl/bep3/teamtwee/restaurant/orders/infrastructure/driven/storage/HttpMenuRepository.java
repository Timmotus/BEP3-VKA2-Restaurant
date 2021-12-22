package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.domain.MenuItem;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.MenuRepository;

@AllArgsConstructor
public class HttpMenuRepository implements MenuRepository {
    private final String rootPath;
    private final RestTemplate client;

    @Override
    public List<MenuItem> getMenuItemsByName(List<String> items) {
        URI uri = URI.create(this.rootPath + "/menu/pizza/available?names=" + String.join(",", items));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        ParameterizedTypeReference<HashMap<String, Long>> responseType = new ParameterizedTypeReference<>() {};

        // should also check if any items not found, deal with errors
        Map<String, Long> results = this.client.exchange(
                uri,
                HttpMethod.GET,
                null,
                responseType).getBody();

        if (results == null) {
            return new ArrayList<>();
        }
        List<MenuItem> res = new ArrayList<>();
        results.forEach((name, price) -> res.add(new MenuItem(name, price)));
        return res;
    }
}
