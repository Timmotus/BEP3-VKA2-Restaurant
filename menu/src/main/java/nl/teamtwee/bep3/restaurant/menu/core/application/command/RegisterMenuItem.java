package nl.teamtwee.bep3.restaurant.menu.core.application.command;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Document
public class RegisterMenuItem {
    private String name;
    private Map<String, Long> ingredients;
    private Double price;
}
