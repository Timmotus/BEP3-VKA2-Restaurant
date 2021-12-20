package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.messaging;

import nl.teamtwee.bep3.restaurant.menu.core.application.MenuCommandHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.AvailablePizza;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.IngredientsChecked;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.messaging.event.PizzaKeywordEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final MenuCommandHandler commandHandler;

    public RabbitMqEventListener(MenuCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.pizza-keywords}'}")
    void listen(PizzaKeywordEvent event) {
        switch (event.eventKey) {
            case "keywords.pizza.available":
                this.commandHandler.handle(
                        new AvailablePizza(event.pizza, event.keyword));
                break;
            case "keywords.pizza.ingredientschecked":
                this.commandHandler.handle(
                        new IngredientsChecked(event.enoughIngredients, event.keyword));
                break;

        }
    }
}
