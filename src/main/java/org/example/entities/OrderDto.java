package org.example.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;


@Builder
@Data
public class OrderDto implements Serializable {
    private String productName;
    private Integer quantity;
    private String customer;
}
