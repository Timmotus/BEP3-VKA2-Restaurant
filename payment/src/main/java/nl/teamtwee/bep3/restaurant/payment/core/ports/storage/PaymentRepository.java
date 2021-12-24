package nl.teamtwee.bep3.restaurant.payment.core.ports.storage;

import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PaymentRepository  extends MongoRepository<Payment, UUID> {

}
