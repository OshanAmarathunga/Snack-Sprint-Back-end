package com.oshan.snack.sprint.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long cartItemId;
    private Integer quantity;
}
