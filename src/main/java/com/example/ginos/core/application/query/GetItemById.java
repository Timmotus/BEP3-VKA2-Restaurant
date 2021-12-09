package com.example.ginos.core.application.query;

import java.util.UUID;

public class GetItemById {

    private final UUID id;

    public GetItemById(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
