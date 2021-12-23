package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.storage.InventoryRepository;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage.dto.IngredientResult;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage.dto.RemoveStockRequest;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage.dto.RemoveStockResult;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
public class HttpInventoryRepository implements InventoryRepository {
    private final String rootPath;
    private final RestTemplate client;

    @Override
    public void removeStock(Map<UUID, Integer> ingredientAmountMap) {
        URI uri = URI.create(this.rootPath + "/inventory/stock/take");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<UUID, Integer>> requestEntity = new HttpEntity<>(ingredientAmountMap, requestHeaders);

        // should also check if any items not found, deal with errors
        List<IngredientResult> result = this.client.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<IngredientResult>>(){}).getBody();
    }
}