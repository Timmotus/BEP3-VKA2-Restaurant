package nl.teamtwee.bep3.restaurant.menu.infrastructure.driver.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String title;
    private String detail;
}
