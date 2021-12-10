package com.example.ginos.infrastructure.driver.web.request;

import com.example.ginos.core.domain.Pizza;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class AddToCartRequest {

    @NotBlank
    public String customerName;

    @NotBlank
    public Pizza selectedPizza;

    @Positive
    public int quantity;

    @NotBlank
    public boolean finishedOrder;

}
