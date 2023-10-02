package org.example.entities;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;


@Builder
@Data
public class OrderDto {
    private String productName;
    private Integer quantity;
    private String customer;
}
