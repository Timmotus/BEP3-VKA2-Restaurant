package com.example.ginos.core.domain.event;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document
public abstract class MenuEvent {
    @Id
    private final UUID eventId = UUID.randomUUID();
    private final Instant eventDate = Instant.now();

    public UUID getEventId() {
        return eventId;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public abstract String getEventKey();

}
