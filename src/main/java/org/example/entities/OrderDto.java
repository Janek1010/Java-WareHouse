package org.example.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDto {
    String productName;
    Double quantity;
    String customer;
}
