package com.example.ginos.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class MenuOptionsRequest {
    @NotBlank
    public String name;

    @Positive
    public int quantity;

}
