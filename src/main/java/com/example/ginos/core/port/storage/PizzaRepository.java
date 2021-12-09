package com.example.ginos.core.port.storage;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PizzaRepository extends MongoRepository<PizzaRepository, UUID> {
}
