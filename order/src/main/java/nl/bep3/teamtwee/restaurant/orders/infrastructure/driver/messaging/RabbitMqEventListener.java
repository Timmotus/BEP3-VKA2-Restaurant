package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import nl.bep3.teamtwee.restaurant.orders.core.application.OrdersCommandHandler;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.CompleteOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.OrderDeliveryDelivered;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.OrderDeliveryStarted;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.OrderPreparingReservation;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging.event.OrderDeliveryEvent;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging.event.OrderKitchenEvent;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging.event.OrderPaymentEvent;

@AllArgsConstructor
@Component
public class RabbitMqEventListener {
    private final OrdersCommandHandler commandHandler;

    @RabbitListener(queues = "#{'${messaging.queue.order-payments}'}")
    void listen(OrderPaymentEvent event) {
        switch (event.getEventKey()) {
            case "payments.order.completed":
                this.commandHandler.handle(
                        new CompleteOrderPayment(event.getOrderId(), event.getPaymentId()));
                break;
        }
    }

    @RabbitListener(queues = "#{'${messaging.queue.order-kitchen}'}")
    void listen(OrderKitchenEvent event) {
        switch (event.getEventKey()) {
            case "kitchen.order.started":
                this.commandHandler.handle(
                        new OrderPreparingReservation(event.getOrderId(), event.getReservationId()));
                break;
        }
    }

    @RabbitListener(queues = "#{'${messaging.queue.order-deliveries}'}")
    void listen(OrderDeliveryEvent event) {
        switch (event.getEventKey()) {
            case "order.delivery.started":
                this.commandHandler.handle(
                        new OrderDeliveryStarted(event.getOrderId(), event.getDeliveryId()));
                break;
            case "order.delivery.delivered":
                this.commandHandler.handle(
                        new OrderDeliveryDelivered(event.getOrderId(), event.getDeliveryId()));
                break;
        }
    }
}
