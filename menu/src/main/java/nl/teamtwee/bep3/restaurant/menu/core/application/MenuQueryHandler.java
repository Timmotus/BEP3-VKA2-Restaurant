package com.example.ginos.core.application;

import com.example.ginos.core.application.query.GetPizzaDetailsByName;
import com.example.ginos.core.domain.Pizza;
import com.example.ginos.core.port.storage.PizzaRepository;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuQueryHandler {

    private final PizzaRepository repository;

    public MenuQueryHandler(PizzaRepository repository) {
        this.repository = repository;
    }

    public Pizza handle(GetPizzaDetailsByName query){
        return this.repository.findPizzaByName(query.getName());
    }

    public List<Pizza> handle(){
        return  this.repository.findAll();
    }

}
