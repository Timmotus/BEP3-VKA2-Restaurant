package com.example.ginos.infrastructure.driver.web.request;

import com.example.ginos.core.domain.Ingredient;
import com.example.ginos.core.domain.Pizza;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

public class AdminAddMenuRequest {
    @NotBlank
    public String name;

    @NotBlank
    public List<Ingredient> ingredients;

    @Positive
    public double price;

    @Positive
    public int quantity;

}
