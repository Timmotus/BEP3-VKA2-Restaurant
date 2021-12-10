package com.example.ginos.infrastructure.driver.web;

import com.example.ginos.core.application.MenuCommandHandler;
import com.example.ginos.core.application.MenuQueryHandler;
import com.example.ginos.core.application.command.AddToCart;
import com.example.ginos.core.domain.Cart;
import com.example.ginos.infrastructure.driver.web.request.AddToCartRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cart")
public class CartController {

    private final MenuCommandHandler menuCommandHandler;
    private final MenuQueryHandler menuQueryHandler;


    public CartController(MenuCommandHandler menuCommandHandler, MenuQueryHandler menuQueryHandler) {
        this.menuCommandHandler = menuCommandHandler;
        this.menuQueryHandler = menuQueryHandler;
    }

    @PostMapping("/add-to-cart")
    public Cart adminAddPizza(@Valid @RequestBody AddToCartRequest request) {
        return this.menuCommandHandler.handle(new AddToCart(request.customerName, request.selectedPizza, request.quantity, request.finishedOrder));
    }

}
