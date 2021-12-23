package nl.teamtwee.bep3.restaurant.payment.core.application;


import nl.teamtwee.bep3.restaurant.payment.core.application.command.AddPayment;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.EditPayment;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;
import nl.teamtwee.bep3.restaurant.payment.core.domain.event.PaymentEvent;
import nl.teamtwee.bep3.restaurant.payment.core.domain.exception.PaymentNotFound;
import nl.teamtwee.bep3.restaurant.payment.core.ports.messaging.PaymentEventPublisher;
import nl.teamtwee.bep3.restaurant.payment.core.ports.storage.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentCommandHandler {
    private final PaymentRepository repository;
    private final PaymentEventPublisher eventPublisher;

    public PaymentCommandHandler(PaymentRepository repository, PaymentEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public Payment handle(AddPayment command) {
        Payment payment = new Payment(command.getOrderId(),command.getCost());

        this.publishEventsAndSave(payment);

        return payment;
    }

    public Payment handle(EditPayment command){
        Payment payment = this.getPaymentById(command.getId());
        payment.EditPayment(command.isPayed());

        this.publishEventsAndSave(payment);

        return payment;
    }

    private Payment getPaymentById(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new PaymentNotFound(id.toString()));
    }




    private void publishEventsAndSave(Payment payment) {
        List<PaymentEvent> events = payment.listEvents();
        events.forEach(eventPublisher::publish);
        payment.clearEvents();

        this.repository.save(payment);
    }


}
