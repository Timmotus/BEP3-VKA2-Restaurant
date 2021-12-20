package com.example.ginos.core.application.query;

import lombok.Getter;

@Getter
public class GetPizzaDetailsByName {
    private String name;

    public GetPizzaDetailsByName(String name){
        this.name =  name;
    }

}
