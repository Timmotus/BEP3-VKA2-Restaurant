package nl.teamtwee.bep3.restaurant.payment.core.application;

import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetAllPayments;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetPaymentById;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;
import nl.teamtwee.bep3.restaurant.payment.core.domain.exception.PaymentNotFound;
import nl.teamtwee.bep3.restaurant.payment.core.ports.storage.PaymentRepository;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PaymentQueryHandler {
    private final PaymentRepository repository;

    public List<Payment> handle(GetAllPayments query) {
        return this.repository.findAll();
    }

    public Payment handle(GetPaymentById query) {
        return this.repository.findById(query.getId())
                .orElseThrow(() -> new PaymentNotFound(query.getId().toString()));
    }

    /*
     * public Payment handle(GetPaymentByOrderId query){
     * return this.repository.findPaymentByOrderID(query.getOrderId());
     * }
     */

}
