package com.example.ginos.core.application;

import com.example.ginos.core.application.command.AdminCreateMenu;
import com.example.ginos.core.application.command.AvailablePizza;
import com.example.ginos.core.application.command.IngredientsChecked;
import com.example.ginos.core.domain.Pizza;
import com.example.ginos.core.domain.event.MenuEvent;
import com.example.ginos.core.port.messaging.MenuEventPublisher;
import com.example.ginos.core.port.storage.PizzaRepository;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

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

//        this.publishEventsFor(pizza);
        this.pizzaRepository.save(pizza);

        return pizza;
    }

    public void handle(AvailablePizza command) {
        publishEventsFor(command.getPizzas());
    }

    public void handle(IngredientsChecked command){
      publishEventsFor(command.checkEnoughIngredients());

    }

    //Moet nog gemaakt worden
    private void publishEventsFor(boolean ingredientsChecked){
//      List<MenuEvent> events = ingredientsChecked.listEvents();
//      events.forEach(eventPublisher::publish);
//      ingredientsChecked.clearEvents();
    }



    private void publishEventsFor(List<Pizza> pizza) {
        for (Pizza p : pizza) {
          List<MenuEvent> events = p.listEvents();
          events.forEach(eventPublisher::publish);
          p.clearEvents();
        }
    }
}
