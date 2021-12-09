package com.example.ginos.core.application;

import com.example.ginos.core.application.query.GetPizzaInfo;
import com.example.ginos.core.domain.Pizza;
import com.example.ginos.core.port.storage.PizzaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuQueryHandler {

    private final PizzaRepository repository;

    public MenuQueryHandler(PizzaRepository repository) {
        this.repository = repository;
    }

    public void handle(GetPizzaInfo query){
        GetPizzaInfo
        return this.repository.
    }

    public Item handle(GetItemById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new ItemNotFound(query.getId().toString()));
    }

    public List<Item> handle(FindItemsByProductName query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return this.repository.findByProductNameEquals(query.getProductName(), sort);
    }

    public List<Item> handle(ListItems query) {
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
