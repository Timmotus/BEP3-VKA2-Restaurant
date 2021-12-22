package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nl.teamtwee.bep3.restaurant.menu.core.application.MenuCommandHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.MenuQueryHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.AdminCreateMenu;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetPizzaDetailsByName;
import nl.teamtwee.bep3.restaurant.menu.core.domain.OrderedPizzaResponse;
import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request.AdminAddMenuRequest;

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
        //TO DO: HANDLE DUPLICATES
        return this.menuCommandHandler
                .handle(new AdminCreateMenu(request.name, request.ingredients, request.options,
                        request.size, request.price, request.quantity));
    }

    @GetMapping("/{pizzaname}")
    public Pizza getItemById(@PathVariable String pizzaname) {
        //TO DO: give bad status if pizza not found..
      return this.menuQueryHandler.handle(new GetPizzaDetailsByName(pizzaname));
    }

    @GetMapping("/all")
    public List<Pizza> getAllPizza() {
        //TO DO: give bad status if list is empty
        return this.menuQueryHandler.handle();
    }

    @GetMapping("/pizza/available")
    public ResponseEntity<Map<String, Long>> areAvailable(@RequestParam List<String> names) {
        return ResponseEntity.ok(this.menuCommandHandler.handle(names));
    }
}
