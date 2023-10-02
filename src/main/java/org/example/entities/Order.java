package org.example.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.UUID;

@Data
@Builder
public class Order implements Comparable<Order>{
    String productName;
    Double quantity;
    Customer customer;
    @Override
    public int compareTo(Order otherOrder) {
        return Comparator.comparing(Order::getProductName)
                .thenComparing(Order::getQuantity)
                .compare(this,otherOrder);
    }
}
