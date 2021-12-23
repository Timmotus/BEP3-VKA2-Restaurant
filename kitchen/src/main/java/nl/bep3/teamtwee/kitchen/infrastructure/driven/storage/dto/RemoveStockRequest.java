package nl.bep3.teamtwee.kitchen.infrastructure.driven.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class RemoveStockRequest {
    private Map<UUID, Integer> ingredientAmountMap;
}