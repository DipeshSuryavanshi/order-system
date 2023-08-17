package com.order.system.dto;

import lombok.Getter;

@Getter

public enum Status {
    PENDING,
    PROCESSED,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
