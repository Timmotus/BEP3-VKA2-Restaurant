package com.example.ginos.core.port.storage;

import com.example.ginos.core.domain.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CartRepository extends MongoRepository<Cart, UUID> {
}
