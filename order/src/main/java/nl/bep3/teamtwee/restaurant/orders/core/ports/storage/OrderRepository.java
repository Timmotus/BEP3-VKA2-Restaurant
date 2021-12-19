package nl.bep3.teamtwee.restaurant.orders.core.ports.storage;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;

import java.util.List;
import java.util.UUID;

/*
    Docs: https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongo.repositories
    Custom queries with @Query, see: https://stackabuse.com/spring-data-mongodb-guide-to-the-query-annotation/
 */
public interface OrderRepository extends MongoRepository<Order, UUID> {
}
