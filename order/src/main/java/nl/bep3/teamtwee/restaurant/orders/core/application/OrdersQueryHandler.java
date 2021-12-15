package nl.bep3.teamtwee.restaurant.orders.core.application;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import nl.bep3.teamtwee.restaurant.orders.core.application.query.GetOrderById;
import nl.bep3.teamtwee.restaurant.orders.core.application.query.ListOrders;
import nl.bep3.teamtwee.restaurant.orders.core.domain.Order;
import nl.bep3.teamtwee.restaurant.orders.core.domain.exception.OrderNotFound;
import nl.bep3.teamtwee.restaurant.orders.core.ports.storage.OrderRepository;

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
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
