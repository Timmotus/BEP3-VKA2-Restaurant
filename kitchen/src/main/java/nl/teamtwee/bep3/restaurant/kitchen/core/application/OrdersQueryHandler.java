package nl.teamtwee.bep3.restaurant.kitchen.core.application;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.query.GetOrderById;
import nl.teamtwee.bep3.restaurant.kitchen.core.application.query.ListOrders;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.Order;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.exception.OrderNotFoundException;
import nl.teamtwee.bep3.restaurant.kitchen.core.port.storage.OrderRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrdersQueryHandler {
    private final OrderRepository repository;

    public Order handle(GetOrderById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new OrderNotFoundException(query.getId().toString()));
    }

    public List<Order> handle(ListOrders query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        return Sort.by(Sort.Direction.fromString(direction), orderBy);
    }
}