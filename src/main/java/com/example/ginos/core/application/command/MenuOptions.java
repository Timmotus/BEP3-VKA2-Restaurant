package com.example.ginos.core.application.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuOptions {

    private String name;
    private int quantity;

    public MenuOptions(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }
}
