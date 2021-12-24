package nl.bep3.teamtwee.restaurant.orders.infrastructure.driven.storage.dto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResult {
    private UUID id;
    private String status;
    private List<ReservationItemResult> items;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ReservationItemResult {
        private String name;
        private Map<String, Long> ingredients;
    }
}
