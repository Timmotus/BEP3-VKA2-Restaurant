package nl.teamtwee.bep3.restaurant.payment.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import nl.teamtwee.bep3.restaurant.payment.core.domain.event.PaymentEvent;
import nl.teamtwee.bep3.restaurant.payment.core.domain.event.PaymentPaid;

@Document
@Getter
public class Payment {
    @Id
    private UUID id;
    private UUID orderId;
    private Long amount;
    private Boolean paid;

    @Transient
    private List<PaymentEvent> events = new ArrayList<>();

    public Payment(UUID orderId, Long amount) {
        this.id = UUID.randomUUID();
        this.orderId = orderId;
        this.amount = amount;
        this.paid = false;
    }

    public void pay() {
        this.paid = true;
        this.events.add(new PaymentPaid(this.id, this.orderId));
    }

    public List<PaymentEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }
}
