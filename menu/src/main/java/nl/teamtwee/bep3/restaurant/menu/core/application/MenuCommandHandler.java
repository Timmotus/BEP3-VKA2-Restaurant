package nl.teamtwee.bep3.restaurant.menu.core.application;

import nl.teamtwee.bep3.restaurant.menu.core.application.command.AdminCreateMenu;
import nl.teamtwee.bep3.restaurant.menu.core.domain.OrderedPizzaResponse;
import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import nl.teamtwee.bep3.restaurant.menu.core.port.messaging.MenuEventPublisher;
import nl.teamtwee.bep3.restaurant.menu.core.port.storage.PizzaRepository;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request.OrderedPizzaRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ResponseEntity<OrderedPizzaResponse> handle(List<OrderedPizzaRequest> orderedPizzas) {
        OrderedPizzaResponse orderedPizzaResponse = new OrderedPizzaResponse();
        List<Pizza> pizzasAndTheirPrice = new ArrayList<>();
        List<String> pizzasWhichDontExist = new ArrayList<>();
        for (OrderedPizzaRequest op : orderedPizzas) {
            if (pizzaRepository.existsByName(op.getPizzaName())) {
                Pizza pizza = pizzaRepository.findPizzaByName(op.getPizzaName());
                pizzasAndTheirPrice.add(new Pizza(pizza.getName(), pizza.getIngredients(),
                        pizza.getOptions(), pizza.getSize(), pizza.getPrice(), pizza.getQuantity()));
                orderedPizzaResponse.setPizzasAndTheirPrice(pizzasAndTheirPrice);
                orderedPizzaResponse.setPizzasWhichDontExist(pizzasWhichDontExist);
                orderedPizzaResponse.setOrderCanBeMade(true);
            } else {
                pizzasWhichDontExist.add(op.getPizzaName());
                orderedPizzaResponse.setPizzasWhichDontExist(pizzasWhichDontExist);
                orderedPizzaResponse.setOrderCanBeMade(false);
            }
        }
        return ResponseEntity.ok(orderedPizzaResponse);
    }
}
