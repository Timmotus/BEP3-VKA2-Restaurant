package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage.HttpInventoryRepository;
import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.storage.HttpMenuRepository;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.inventory}")
    private String rootPathInventory;

    @Value("${http-client.root-path.menu}")
    private String rootPathMenu;

    @Bean
    public HttpInventoryRepository httpInventoryRepository() {
        return new HttpInventoryRepository(rootPathInventory, restTemplate());
    }

    @Bean
    public HttpMenuRepository httpMenuRepository() {
        return new HttpMenuRepository(rootPathMenu, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}