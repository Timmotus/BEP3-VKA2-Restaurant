package nl.teamtwee.bep3.restaurant.delivery.core.application;

import nl.teamtwee.bep3.restaurant.delivery.core.application.command.DeliveryStatus;
import nl.teamtwee.bep3.restaurant.delivery.core.domain.Delivery;
import nl.teamtwee.bep3.restaurant.delivery.core.port.messaging.MenuEventPublisher;
import nl.teamtwee.bep3.restaurant.delivery.core.port.storage.DeliveryRepository;
import nl.teamtwee.bep3.restaurant.delivery.infrastructure.driver.web.request.DeliveryStatusRequest;
import org.springframework.stereotype.Service;

@Service
public class DeliveryCommandHandler {

    private final DeliveryRepository deliveryRepository;
    private final MenuEventPublisher eventPublisher;

    public DeliveryCommandHandler(DeliveryRepository deliveryRepository, MenuEventPublisher eventPublisher) {
        this.deliveryRepository = deliveryRepository;
        this.eventPublisher = eventPublisher;
    }

    public Delivery handle(DeliveryStatus delivery) {
        return null; //moet nog gemaakt worden
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
//        return null;
//}

//    private void publishEventsFor(List<Pizza> pizza) {
//        for (Pizza p : pizza) {
//            List<MenuEvent> events = p.listEvents();
//            events.forEach(eventPublisher::publish);
//            p.clearEvents();
//        }
//    }
}
