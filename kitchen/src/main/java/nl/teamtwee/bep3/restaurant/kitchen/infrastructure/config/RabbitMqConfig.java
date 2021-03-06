package nl.teamtwee.bep3.restaurant.kitchen.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import nl.teamtwee.bep3.restaurant.kitchen.infrastructure.driven.messaging.RabbitMqEventPublisher;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.exchange.restaurant}")
    private String restaurantExchangeName;

    @Value("${messaging.queue.kitchen-orders}")
    private String kitchenOrdersQueueName;

    @Value("${messaging.routing-key.kitchen-orders}")
    private String kitchenOrdersRoutingKey;

    @Bean
    public TopicExchange restaurantExchange() {
        return new TopicExchange(restaurantExchangeName);
    }

    @Bean
    public Queue kitchenOrdersQueue() {
        return QueueBuilder.durable(kitchenOrdersQueueName).build();
    }

    @Bean
    public Binding kitchenOrdersBinding() {
        return BindingBuilder
                .bind(kitchenOrdersQueue())
                .to(restaurantExchange())
                .with(kitchenOrdersRoutingKey);
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
        // We could use any format, but we'll use JSON, so it is easier to inspect.
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .build();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        // Set this in order to prevent deserialization using the sender-specific
        // __TYPE-ID__ in the message header.
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }
}