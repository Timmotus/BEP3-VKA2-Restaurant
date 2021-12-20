package nl.teamtwee.bep3.restaurant.payment.core.ports.messaging;

import nl.teamtwee.bep3.restaurant.payment.core.domain.event.JobEvent;

public interface JobEventPublisher {
    void publish(JobEvent event);
}
