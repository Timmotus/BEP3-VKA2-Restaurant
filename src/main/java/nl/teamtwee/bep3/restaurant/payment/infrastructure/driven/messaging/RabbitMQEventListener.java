package nl.teamtwee.bep3.restaurant.payment.infrastructure.driven.messaging;


import nl.teamtwee.bep3.restaurant.payment.core.application.PaymentCommandHandler;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.AddPayment;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driven.messaging.event.OrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final PaymentCommandHandler commandHandler;

    public RabbitMqEventListener(PaymentCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }
    //wat krijg ik precies binnen
    @RabbitListener(queues = "#{'${messaging.queue.payment-order}'}")
    void listen(OrderEvent event) {

        this.commandHandler.handle(
                new AddPayment(event.orderId, event.cost)
        );
        }
    }