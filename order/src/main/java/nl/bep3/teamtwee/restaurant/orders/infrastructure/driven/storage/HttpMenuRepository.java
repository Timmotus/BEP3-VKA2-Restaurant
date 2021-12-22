package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage;

import java.net.ConnectException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.domain.MenuItem;
import nl.bep3.teamtwee.restaurant.orders.core.domain.exception.RepositoryTimeoutException;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.MenuRepository;

@AllArgsConstructor
public class HttpMenuRepository implements MenuRepository {
    private final String rootPath;
    private final RestTemplate client;

    @Override
    public List<MenuItem> getMenuItemsByName(List<String> items) {
        URI uri = URI.create(this.rootPath + "/menu/prices?names=" + String.join(",", items));
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        ParameterizedTypeReference<HashMap<String, Double>> responseType = new ParameterizedTypeReference<>() {};

        // should also check if any items not found, deal with errors
        Map<String, Double> results = new HashMap<>();
        try {
            results = this.client.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    responseType).getBody();
        } catch (RestClientException e) {
            if (e.contains(ConnectException.class)) {
                throw new RepositoryTimeoutException();
            }
            throw e;
        }

        return results.entrySet().stream()
                .map(entry -> new MenuItem(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
