package nl.bep3.teamtwee.restaurant.orders.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.messaging.RabbitMqEventPublisher;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.exchange.restaurant}")
    private String restaurantExchangeName;

    @Value("${messaging.queue.order-payments}")
    private String orderPaymentsQueueName;

    @Value("${messaging.routing-key.order-payments}")
    private String orderPaymentsRoutingKey;

    @Value("${messaging.queue.order-kitchen}")
    private String orderKitchenQueueName;

    @Value("${messaging.routing-key.order-kitchen}")
    private String orderKitchenRoutingKey;

    @Value("${messaging.queue.order-deliveries}")
    private String orderDeliveriesQueueName;

    @Value("${messaging.routing-key.order-deliveries}")
    private String orderDeliveriesRoutingKey;

    @Bean
    public TopicExchange restaurantExchange() {
        return new TopicExchange(restaurantExchangeName);
    }

    @Bean
    public Queue orderPaymentsQueue() {
        return QueueBuilder.durable(orderPaymentsQueueName).build();
    }

    @Bean
    public Binding orderPaymentsBinding() {
        return BindingBuilder
                .bind(orderPaymentsQueue())
                .to(restaurantExchange())
                .with(orderPaymentsRoutingKey);
    }
    @Bean
    public Queue orderKitchenQueue() {
        return QueueBuilder.durable(orderKitchenQueueName).build();
    }


    @Bean
    public Binding orderKitchenBinding() {
        return BindingBuilder
                .bind(orderKitchenQueue())
                .to(restaurantExchange())
                .with(orderKitchenRoutingKey);
    }

    @Bean
    public Queue orderDeliveriesQueue() {
        return QueueBuilder.durable(orderDeliveriesQueueName).build();
    }

    @Bean
    public Binding orderDeliveriesBinding() {
        return BindingBuilder
                .bind(orderDeliveriesQueue())
                .to(restaurantExchange())
                .with(orderDeliveriesRoutingKey);
    }

    @Bean
    public RabbitMqEventPublisher EventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, restaurantExchangeName);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        // We need to configure a message converter to be used by RabbitTemplate.
        // We could use any format, but we'll use JSON so it is easier to inspect.
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .build();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        // Set this in order to prevent deserialization using the sender-specific
        // __TYPEID__ in the message header.
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }
}
