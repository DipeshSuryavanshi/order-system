package com.order.system.dto;

import lombok.Data;

import java.util.List;
@Data
public class
OrderItemDTO {
    private String msg=("List of the order associated with Item");
    private Long itemId;
    private List<Long> orderId;


}
