package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage;

import java.net.URI;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.storage.InventoryRepository;

@AllArgsConstructor
public class HttpInventoryRepository implements InventoryRepository {
    private final String rootPath;
    private final RestTemplate client;

    @Override
    public void removeStock(Map<String, Long> ingredientAmountMap) {
        URI uri = URI.create(this.rootPath + "/inventory/stock/take");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(ingredientAmountMap, requestHeaders);

        // should also check if any items not found, deal with errors
        this.client.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                Void.class);
    }
}