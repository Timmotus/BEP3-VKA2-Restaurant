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
        Pizza pizza = new Pizza(command.getName(), command.getIngredients(), command.getPrice(),
                command.getQuantity());
        // this.publishEventsFor(pizza);
        this.pizzaRepository.save(pizza);

        return pizza;
    }

    public ResponseEntity<OrderedPizzaResponse> handle(List<OrderedPizzaRequest> orderedPizzas) {
        OrderedPizzaResponse orderedPizzaResponse = new OrderedPizzaResponse();
        List<Pizza> pizzasAndTheirPrice = new ArrayList<>();
        List<String> pizzasWhichDontExist = new ArrayList<>();
        for (OrderedPizzaRequest op : orderedPizzas) {
            if(pizzaRepository.existsByName(op.getPizzaName())){
                Pizza pizza = new Pizza(pizzaRepository.findPizzaByName(op.getPizzaName()));
                pizzasAndTheirPrice.add(new Pizza(pizza.getName(), pizza.getPrice()));
                orderedPizzaResponse.setPizzasAndTheirPrice(pizzasAndTheirPrice);
                orderedPizzaResponse.setPizzasWhichDontExist(pizzasWhichDontExist);
                orderedPizzaResponse.setOrderCanBeMade(true);
            }
            else{
                pizzasWhichDontExist.add(op.getPizzaName());
                orderedPizzaResponse.setPizzasWhichDontExist(pizzasWhichDontExist);
                orderedPizzaResponse.setOrderCanBeMade(false);
            }
            return ResponseEntity.ok(orderedPizzaResponse);
        }

//    public Object handle(AvailablePizza command) {
//        List<Pizza> pizzas = new ArrayList<>();
//        command.getPizzas().forEach(name -> pizzas.add(new Pizza(name, new ArrayList<>(), 10, 10)));
//        publishEventsFor(pizzas);
//        return pizzas;
//    }
//
//    public Object handle(IngredientsChecked command) {
//        publishEventsFor(command.checkEnoughIngredients());
//        return null;
//
//    }

    // Moet nog gemaakt worden
//    private void publishEventsFor(boolean ingredientsChecked) {
        // List<MenuEvent> events = ingredientsChecked.listEvents();
        // events.forEach(eventPublisher::publish);
        // ingredientsChecked.clearEvents();
        return null;
    }

//    private void publishEventsFor(List<Pizza> pizza) {
//        for (Pizza p : pizza) {
//            List<MenuEvent> events = p.listEvents();
//            events.forEach(eventPublisher::publish);
//            p.clearEvents();
//        }
//    }
}
