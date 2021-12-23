package nl.teamtwee.bep3.restaurant.payment.core.application;

import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetPaymentById;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;
import nl.teamtwee.bep3.restaurant.payment.core.domain.exception.PaymentNotFound;
import nl.teamtwee.bep3.restaurant.payment.core.ports.storage.PaymentRepository;
import org.springframework.stereotype.Service;


@Service
public class PaymentQueryHandler {

    private final PaymentRepository repository;

    public PaymentQueryHandler(PaymentRepository repository) {
        this.repository = repository;
    }

    public Payment handle(GetPaymentById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new PaymentNotFound(query.getId().toString()));
    }

   /* public Payment handle(GetPaymentByOrderId query){
        return this.repository.findPaymentByOrderID(query.getOrderId());
    }*/

}
