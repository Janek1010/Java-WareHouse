package org.example.entities;

import lombok.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
public class Customer implements Comparable<Customer>{
    private String name;
    private int age;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }
    @Override
    public int compareTo(Customer otherCustomer) {
        return Comparator.comparing(Customer::getName)
                .thenComparing(Customer::getAge)
                .compare(this,otherCustomer);
    }
}
