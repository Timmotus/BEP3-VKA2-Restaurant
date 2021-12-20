package nl.teamtwee.bep3.restaurant.menu.core.application;

import nl.teamtwee.bep3.restaurant.menu.core.application.command.AdminCreateMenu;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.AvailablePizza;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.IngredientsChecked;
import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import nl.teamtwee.bep3.restaurant.menu.core.domain.event.MenuEvent;
import nl.teamtwee.bep3.restaurant.menu.core.port.messaging.MenuEventPublisher;
import nl.teamtwee.bep3.restaurant.menu.core.port.storage.PizzaRepository;
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
        Pizza pizza = new Pizza(command.getName(), command.getIngredients(), command.getPrice(),
                command.getQuantity());

        // this.publishEventsFor(pizza);
        this.pizzaRepository.save(pizza);

        return pizza;
    }

    public Object handle(AvailablePizza command) {
        List<Pizza> pizzas = new ArrayList<>();
        command.getPizzas().forEach(name -> pizzas.add(new Pizza(name, new ArrayList<>(), 10, 10)));
        publishEventsFor(pizzas);
        return pizzas;
    }

    public Object handle(IngredientsChecked command) {
        publishEventsFor(command.checkEnoughIngredients());
        return null;

    }

    // Moet nog gemaakt worden
    private void publishEventsFor(boolean ingredientsChecked) {
        // List<MenuEvent> events = ingredientsChecked.listEvents();
        // events.forEach(eventPublisher::publish);
        // ingredientsChecked.clearEvents();
    }

    private void publishEventsFor(List<Pizza> pizza) {
        for (Pizza p : pizza) {
            List<MenuEvent> events = p.listEvents();
            events.forEach(eventPublisher::publish);
            p.clearEvents();
        }
    }
}
