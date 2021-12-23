package nl.teamtwee.bep3.restaurant.kitchen.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.teamtwee.bep3.restaurant.kitchen.core.domain.OrderItem;

import java.util.List;

@Getter
@AllArgsConstructor
public class UploadOrder {
    private List<OrderItem> orderItems;
}