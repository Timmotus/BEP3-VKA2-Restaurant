package nl.bep3.teamtwee.kitchen.infrastructure.driver.web.request;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UploadOrderRequest {
    @NotEmpty public List<Map<UUID, Integer>> orderItems;
}