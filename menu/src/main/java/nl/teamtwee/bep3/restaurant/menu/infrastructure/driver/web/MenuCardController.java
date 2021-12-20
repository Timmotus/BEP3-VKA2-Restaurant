package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web;

import nl.teamtwee.bep3.restaurant.menu.core.application.MenuCommandHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.MenuQueryHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.AdminCreateMenu;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetPizzaDetailsByName;
import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request.AdminAddMenuRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuCardController {

    private final MenuCommandHandler menuCommandHandler;
    private final MenuQueryHandler menuQueryHandler;

    public MenuCardController(MenuCommandHandler menuCommandHandler, MenuQueryHandler menuQueryHandler) {
        this.menuCommandHandler = menuCommandHandler;
        this.menuQueryHandler = menuQueryHandler;
    }

    @PostMapping("admin/addmenu")
    public Pizza adminAddPizza(@Valid @RequestBody AdminAddMenuRequest request) {
        return this.menuCommandHandler
                .handle(new AdminCreateMenu(request.name, request.ingredients, request.price, request.quantity));
    }

    @GetMapping("/{pizzaname}")
    public Pizza getItemById(@PathVariable String pizzaname) {
        return this.menuQueryHandler.handle(new GetPizzaDetailsByName(pizzaname));
    }

    @GetMapping("/all")
    public List<Pizza> getAllPizza() {
        return this.menuQueryHandler.handle();
    }

}
