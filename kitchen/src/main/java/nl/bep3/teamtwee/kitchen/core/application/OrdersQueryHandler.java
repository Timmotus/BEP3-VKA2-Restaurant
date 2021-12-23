package nl.bep3.teamtwee.kitchen.core.application;

import nl.bep3.teamtwee.kitchen.core.application.query.GetOrderById;
import nl.bep3.teamtwee.kitchen.core.application.query.ListOrders;
import nl.bep3.teamtwee.kitchen.core.domain.Order;
import nl.bep3.teamtwee.kitchen.core.domain.exception.OrderNotFound;
import nl.bep3.teamtwee.kitchen.core.port.storage.OrderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersQueryHandler {
    private final OrderRepository repository;

    public OrdersQueryHandler(OrderRepository repository) {
        this.repository = repository;
    }

    public Order handle(GetOrderById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new OrderNotFound(query.getId().toString()));
    }

    public List<Order> handle(ListOrders query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        return Sort.by(Sort.Direction.fromString(direction), orderBy);
    }
}
