package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.KitchenRepository;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto.ReservationRequest;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto.ReservationResult;

@AllArgsConstructor
public class HttpKitchenRepository implements KitchenRepository {
    private final String rootPath;
    private final RestTemplate client;

    @Override
    public UUID createReservation(UUID orderId, Map<String, Long> items) {
        URI uri = URI.create(this.rootPath + "/kitchen");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ReservationRequest> requestEntity = new HttpEntity<>(
                new ReservationRequest(orderId, items),
                requestHeaders);

        // should also check if any items not found, deal with errors
        ReservationResult result = this.client.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                ReservationResult.class).getBody();

        return result.getId();
    }
}
