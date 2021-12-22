package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.PaymentRepository;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto.PaymentRequest;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto.PaymentResult;

@AllArgsConstructor
public class HttpPaymentRepository implements PaymentRepository {
    private final String rootPath;
    private final RestTemplate client;

    @Override
    public UUID createPayment(UUID orderId, Long amount) {
        URI uri = URI.create(this.rootPath + "/payment");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PaymentRequest> requestEntity = new HttpEntity<>(
                new PaymentRequest(orderId, amount),
                requestHeaders);

        // should also check if any items not found, deal with errors
        PaymentResult result = this.client.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                PaymentResult.class).getBody();

        return result.getPaymentId();
    }

}
