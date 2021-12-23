package nl.bep3.teamtwee.kitchen.core.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.bep3.teamtwee.kitchen.core.domain.OrderItem;

import java.util.List;

@Getter
@AllArgsConstructor
public class UploadOrder {
    private List<OrderItem> orderItems;
}