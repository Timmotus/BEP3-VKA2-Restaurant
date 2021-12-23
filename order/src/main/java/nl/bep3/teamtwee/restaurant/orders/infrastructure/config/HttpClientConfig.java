package nl.bep3.teamtwee.restaurant.orders.infrastructure.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.HttpKitchenRepository;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.HttpMenuRepository;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.HttpPaymentRepository;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.kitchen}")
    private String rootPathKitchen;

    @Value("${http-client.root-path.menu}")
    private String rootPathMenu;

    @Value("${http-client.root-path.payment}")
    private String rootPathPayment;

    @Bean
    public HttpKitchenRepository httpKitchenRepository() {
        return new HttpKitchenRepository(rootPathKitchen, restTemplate());
    }

    @Bean
    public HttpMenuRepository httpMenuRepository() {
        return new HttpMenuRepository(rootPathMenu, restTemplate());
    }

    @Bean
    public HttpPaymentRepository httpPaymentRepository() {
        return new HttpPaymentRepository(rootPathPayment, restTemplate());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
