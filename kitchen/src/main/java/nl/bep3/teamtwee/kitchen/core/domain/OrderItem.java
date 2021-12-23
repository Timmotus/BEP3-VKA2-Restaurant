package nl.bep3.teamtwee.kitchen.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.UUID;

@Getter
@Document
@AllArgsConstructor
public class OrderItem {
    private Map<UUID, Integer> ingredients;
}