package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging;

import nl.bep3.teamtwee.restaurant.orders.core.application.OrdersCommandHandler;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.CompleteOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.FailedOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.OrderDeliveryDelivered;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.OrderDeliveryStarted;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging.event.OrderDeliveryEvent;
import nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging.event.OrderPaymentEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final OrdersCommandHandler commandHandler;

    public RabbitMqEventListener(OrdersCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.order-payments}'}")
    void listen(OrderPaymentEvent event) {
        switch (event.getEventKey()) {
            case "payments.order.completed":
                this.commandHandler.handle(
                        new CompleteOrderPayment(event.getOrderId(), event.getPaymentId()));
                break;
            case "payments.order.failed":
                this.commandHandler.handle(
                        new FailedOrderPayment(event.getOrderId(), event.getPaymentId()));
                break;
        }
    }

    @RabbitListener(queues = "#{'${messaging.queue.order-deliveries}'}")
    void listen(OrderDeliveryEvent event) {
        switch (event.getEventKey()) {
            case "delivery.order.delivered":
                this.commandHandler.handle(
                        new OrderDeliveryDelivered(event.getOrderId(), event.getDeliveryId()));
                break;
            case "delivery.order.started":
                this.commandHandler.handle(
                        new OrderDeliveryStarted(event.getOrderId(), event.getDeliveryId()));
                break;
        }
    }
}
