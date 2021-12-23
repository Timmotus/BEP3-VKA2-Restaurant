package nl.teamtwee.bep3.restaurant.payment.core.domain;

import lombok.Getter;
import lombok.Setter;
import nl.teamtwee.bep3.restaurant.payment.core.domain.event.PaymentEvent;
import nl.teamtwee.bep3.restaurant.payment.core.domain.exception.NotPayed;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
@Getter
@Setter
public class Payment {

    @Id
    private UUID id;
    @Indexed
    private UUID orderId;
    @Indexed
    private double cost;
    @Indexed
    private boolean payed;
    @Transient
    private List<PaymentEvent> events = new ArrayList<>();


    public Payment(UUID orderId, double cost){
        this.id =  UUID.randomUUID();
        this.orderId =  orderId;
        this.cost = cost;
        this.payed = false;
    }


    public void EditPayment(boolean payed){
        if(payed == true){
            setPayed(payed);
            //naar order dat er betaald is
        }else{
            //naar order dat er niet betaald is
            throw new NotPayed(
                    String.format("No Payment of order: %s ",
                            id));
        }

    }

    public List<PaymentEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        this.events.clear();
    }

}
