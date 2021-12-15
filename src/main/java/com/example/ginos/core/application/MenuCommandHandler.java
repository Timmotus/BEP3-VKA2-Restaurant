package com.example.ginos.core.application;

import com.example.ginos.core.application.command.AddToCart;
import com.example.ginos.core.application.command.AdminCreateMenu;
import com.example.ginos.core.domain.Cart;
import com.example.ginos.core.domain.Pizza;
import com.example.ginos.core.domain.event.MenuEvent;
import com.example.ginos.core.port.messaging.MenuEventPublisher;
import com.example.ginos.core.port.storage.CartRepository;
import com.example.ginos.core.port.storage.PizzaRepository;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Document
public class MenuCommandHandler {

    private final PizzaRepository pizzaRepository;
    private final CartRepository cartRepository;
    private final MenuEventPublisher eventPublisher;

    public MenuCommandHandler(PizzaRepository pizzaRepository, CartRepository cartRepository, MenuEventPublisher eventPublisher) {
        this.pizzaRepository = pizzaRepository;
        this.cartRepository = cartRepository;
        this.eventPublisher = eventPublisher;
    }

    public Pizza handle(AdminCreateMenu command) {
        Pizza pizza = new Pizza(command.getName(), command.getIngredients(), command.getPrice(),
                command.getQuantity());

        this.publishEventsFor(pizza);
        this.pizzaRepository.save(pizza);

        return pizza;
    }

    public Cart handle(AddToCart command) {
        Cart cart = new Cart(command.getCustomerName(), command.getSelectedPizza(), command.getQuantity(), command.isFinishedOrder());
        this.publishEventsFor(cart);
        this.cartRepository.save(cart);

        return cart;
    }


    private void publishEventsFor(Pizza pizza) {
        List<MenuEvent> events = pizza.listEvents();
        events.forEach(eventPublisher::publish);
        pizza.clearEvents();
    }

    private void publishEventsFor(Cart cart) {
        List<MenuEvent> events = cart.listEvents();
        events.forEach(eventPublisher::publish);
        cart.clearEvents();
    }

}
