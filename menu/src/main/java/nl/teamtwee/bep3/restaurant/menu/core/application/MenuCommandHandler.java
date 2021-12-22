package nl.teamtwee.bep3.restaurant.menu.core.application;

import nl.teamtwee.bep3.restaurant.menu.core.application.command.AdminCreateMenu;
import nl.teamtwee.bep3.restaurant.menu.core.domain.OrderedPizzaResponse;
import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import nl.teamtwee.bep3.restaurant.menu.core.domain.exception.ItemNotFound;
import nl.teamtwee.bep3.restaurant.menu.core.port.messaging.MenuEventPublisher;
import nl.teamtwee.bep3.restaurant.menu.core.port.storage.PizzaRepository;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request.OrderedPizzaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuCommandHandler {

    private final PizzaRepository pizzaRepository;
    private final MenuEventPublisher eventPublisher;

    public MenuCommandHandler(PizzaRepository pizzaRepository, MenuEventPublisher eventPublisher) {
        this.pizzaRepository = pizzaRepository;
        this.eventPublisher = eventPublisher;
    }

    public Pizza handle(AdminCreateMenu command) {
        Pizza pizza = new Pizza(command.getName(), command.getIngredients(),
                command.getOptions(), command.getSize(), command.getPrice(), command.getQuantity());
        this.pizzaRepository.save(pizza);

        return pizza;
    }

    public Map<String, Long> handle(List<String> orderedPizzas) {
        Map<String, Long> items = new HashMap<>();
        for (String name : orderedPizzas) {
            Pizza pizza = this.pizzaRepository.findByName(name).orElseThrow(() -> new ItemNotFound("Pizza " + name + "not found"));
            items.put(name, Double.valueOf(pizza.getPrice()).longValue());
        }
        return items;
    }
}
