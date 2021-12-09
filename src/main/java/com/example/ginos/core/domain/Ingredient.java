package com.example.ginos.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Ingredient {
    private String name;
    public Ingredient(String name){
        this.name = name;
    }
}
