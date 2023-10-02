package org.example.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Comparator;
import java.util.List;

@Data
@Builder
public class Customer implements Comparable<Customer>{
    String name;
    int age;
    List<Order> orders;

    @Override
    public int compareTo(Customer otherCustomer) {
        return Comparator.comparing(Customer::getName)
                .thenComparing(Customer::getAge)
                .compare(this,otherCustomer);
    }
}
