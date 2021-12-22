package nl.bep3.teamtwee.restaurant.orders.core.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Document
public class OrderItem {
    private Double price;
    private Long count;
}
