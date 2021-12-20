package nl.teamtwee.bep3.restaurant.menu.core.application;

import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetPizzaDetailsByName;
import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import nl.teamtwee.bep3.restaurant.menu.core.port.storage.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuQueryHandler {

    private final PizzaRepository repository;

    public MenuQueryHandler(PizzaRepository repository) {
        this.repository = repository;
    }

    public Pizza handle(GetPizzaDetailsByName query) {
        return this.repository.findPizzaByName(query.getName());
    }

    public List<Pizza> handle() {
        return this.repository.findAll();
    }

}
