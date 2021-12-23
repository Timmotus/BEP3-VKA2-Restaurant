package nl.bep3.teamtwee.kitchen.infrastructure.config;

import nl.bep3.teamtwee.kitchen.infrastructure.driven.storage.HttpInventoryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.inventory}")
    private String rootPathInventory;

    @Bean
    public HttpInventoryRepository httpInventoryRepository() {
        return new HttpInventoryRepository(rootPathInventory, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}