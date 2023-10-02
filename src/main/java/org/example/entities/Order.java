package org.example.entities;

import lombok.*;

import java.util.Comparator;
import java.util.UUID;

@Data
@Builder
public class Order implements Comparable<Order>{
    private String productName;
    private Integer quantity;
    private Customer customer;
    @Override
    public int compareTo(Order otherOrder) {
        return Comparator.comparing(Order::getProductName)
                .thenComparing(Order::getQuantity)
                .compare(this,otherOrder);
    }
    @Override
    public String toString() {
        return "Order{" +
                "productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", customer='" + customer.getName() + '\'' +
                '}';
    }
}
