package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web;

import nl.teamtwee.bep3.restaurant.menu.core.application.MenuCommandHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.MenuQueryHandler;
import nl.teamtwee.bep3.restaurant.menu.core.application.command.AdminCreateMenu;
import nl.teamtwee.bep3.restaurant.menu.core.application.query.GetPizzaDetailsByName;
import nl.teamtwee.bep3.restaurant.menu.core.domain.OrderedPizzaResponse;
import nl.teamtwee.bep3.restaurant.menu.core.domain.Pizza;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request.AdminAddMenuRequest;
import nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.request.OrderedPizzaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/pizza/available")
    public ResponseEntity<OrderedPizzaResponse> createAccessToken(@RequestBody List<OrderedPizzaRequest> orderedPizzaRequest) {
        if (orderedPizzaRequest.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            return this.menuCommandHandler.handle(orderedPizzaRequest);
        }
    }
}
