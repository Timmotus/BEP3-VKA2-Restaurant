package com.example.ginos.core.port.storage;

import com.example.ginos.core.domain.Pizza;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PizzaRepository extends MongoRepository<Pizza, UUID> {
    Pizza findPizzaByName(String pizzaName);
}
