package nl.teamtwee.bep3.restaurant.payment.core.application;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.CreatePayment;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.PayPayment;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetPaymentById;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;
import nl.teamtwee.bep3.restaurant.payment.core.domain.event.PaymentEvent;
import nl.teamtwee.bep3.restaurant.payment.core.ports.messaging.PaymentEventPublisher;
import nl.teamtwee.bep3.restaurant.payment.core.ports.storage.PaymentRepository;

@AllArgsConstructor
@Service
public class PaymentCommandHandler {
    private final PaymentRepository repository;
    private final PaymentEventPublisher eventPublisher;
    private final PaymentQueryHandler queryHandler;

    public Payment handle(CreatePayment command) {
        Payment payment = new Payment(command.getOrderId(),command.getCost());
        publishEventsAndSave(payment);
        return payment;
    }

    public Payment handle(PayPayment command) {
        Payment payment = this.queryHandler.handle(new GetPaymentById(command.getId()));
        payment.pay();
        publishEventsAndSave(payment);
        return payment;
    }

    private void publishEventsAndSave(Payment payment) {
        List<PaymentEvent> events = new ArrayList<>(payment.listEvents());
        payment.clearEvents();
        this.repository.save(payment);
        events.forEach(eventPublisher::publishSend);
    }
}
