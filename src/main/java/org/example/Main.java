package org.example;

import org.example.entities.Customer;
import org.example.entities.Order;
import org.example.entities.OrderDto;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {

    private final static List<String> products = List.of("Piwo IPA", "Piwo APA", "Pomidor", "Mango", "Młotek");
    private final static Map<String, String> map = new HashMap<>();
    private final static List<String> names = List.of("Jan Kowalski", "Zbigniew Noga", "Jacek Jackowski", "Marek Markowski", "Jurek Jurkowski");

    public static void main(String[] args) throws FileNotFoundException {

        Random rand = new Random();
        Set<Customer> customers = new HashSet<>();

        for (String n : names) {
            customers.add(Customer.builder().name(n).age(rand.nextInt(80) + 20).build());
        }

        for (Customer c : customers) {
            for (int i = 0; i < rand.nextInt(5) + 3; i++) {
                c.addOrder(Order.builder().customer(c).productName(products.get(rand.nextInt(5))).quantity(rand.nextInt(500) + 10).build());
            }
        }
        // 2
        //customers.forEach(System.out::println);

        // 3
        Set<Order> orders = customers.stream()
                .map(Customer::getOrders) // Otrzymujemy List<List<Order>>
                .flatMap(Collection::stream) // Spłaszczamy Listy Orderów do pojedynczego strumienia
                .collect(Collectors.toSet());

        //orders.forEach(System.out::println);

        // 4
        orders = orders.stream().filter(el -> el.getQuantity() >= 80).filter(el -> el.getQuantity() < 250).sorted().collect(Collectors.toSet());
        //orders.forEach(System.out::println);

        // 5
        List<OrderDto> dtos = orders.stream().map(el -> OrderDto.builder().customer(el.getCustomer().getName()).productName(el.getProductName()).quantity(el.getQuantity()).build()).toList();
        //dtos.forEach(System.out::println);

        File file = new File("dataDTO.bin");
        // 6
        //save
        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(dtos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        List<OrderDto> readDTO = new ArrayList<>();

        //read
        FileInputStream fstream = new FileInputStream(file);
        try {
            ObjectInputStream ostream = new ObjectInputStream(fstream);

            Object obj;
            obj = ostream.readObject();
            readDTO = (List<OrderDto>) obj;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        readDTO.forEach(System.out::println);

        // 7

        ForkJoinPool customThreadPool = new ForkJoinPool(3);

    }
}