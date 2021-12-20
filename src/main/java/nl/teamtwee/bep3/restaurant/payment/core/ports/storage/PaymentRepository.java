package nl.teamtwee.bep3.restaurant.payment.core.ports.storage;

import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository  extends MongoRepository<Payment, UUID> {
    Optional<Payment> findById(UUID Id);

    Payment findPaymentbyOrderID(UUID orderId);
}
