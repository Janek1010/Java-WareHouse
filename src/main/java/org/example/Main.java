package org.example;

import org.example.entities.Customer;
import org.example.entities.Order;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private final static List<String> products = List.of("Piwo IPA","Piwo APA", "Pomidor", "Mango","Młotek");
    private final static Map<String,String > map = new HashMap<>();
    private final static List<String> names = List.of("Jan Kowalski","Zbigniew Noga", "Jacek Jackowski", "Marek Markowski","Jurek Jurkowski");
    public static void main(String[] args) {

        Random rand = new Random();
        Set<Customer> customers  = new HashSet<>();

        for (String n: names) {
            customers.add(Customer.builder().name(n).age(rand.nextInt(80) + 20).build());
        }

        for (Customer c: customers) {
            for (int i = 0; i < rand.nextInt(5) + 3; i++) {
                c.addOrder(Order.builder().customer(c).productName("Piwo IPA").quantity(rand.nextInt(500) + 10).build());
            }
        }
        // 2
        //customers.forEach(System.out::println);

        // 3
        List<Order> orders = customers.stream()
                .map(Customer::getOrders) // Otrzymujemy List<List<Order>>
                .flatMap(Collection::stream) // Spłaszczamy Listy Orderów do pojedynczego strumienia
                .toList();

        orders.forEach(System.out::println);
    }
}