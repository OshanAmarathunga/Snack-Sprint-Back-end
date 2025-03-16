package com.oshan.snack.sprint.request;

import com.oshan.snack.sprint.models.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Long restaurantId;
    private Address deliveryAddress;

}
