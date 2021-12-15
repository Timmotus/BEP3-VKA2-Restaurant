package nl.bep3.teamtwee.restaurant.orders.infrastructure.driver.messaging;

import nl.bep3.teamtwee.restaurant.orders.core.application.OrdersCommandHandler;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.CompleteOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.CreatedOrderPayment;
import nl.bep3.teamtwee.restaurant.orders.core.application.command.InvalidateOrderPayment;
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
        switch (event.eventKey) {
            case "payments.order.created":
                this.commandHandler.handle(
                        new CreatedOrderPayment(event.order, event.payment)
                );
                break;
            case "payments.order.completed":
                this.commandHandler.handle(
                        new CompleteOrderPayment(event.order, event.payment)
                );
                break;
            case "payments.order.failed":
                this.commandHandler.handle(
                        new InvalidateOrderPayment(event.order, event.payment)
                );
                break;
        }
    }
}
