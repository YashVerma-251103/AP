package com.Start.Classes;

import java.util.ArrayList;
import java.util.TreeMap;

import com.Start.Structures.Pair;


public class order {


    private static Integer order_id_counter = 1;
    private static TreeMap<Integer, String> Status_map = new TreeMap<>();

    // Current order statuses: Pending, Preparing, Out for delivery, Delivered,
    // Denied
    static {
        Status_map.put(0, "Pending");
        Status_map.put(1, "Preparing");
        Status_map.put(2, "Out for delivery");
        Status_map.put(3, "Delivered");
        Status_map.put(4, "Denied");
        Status_map.put(5, "Cancelled");
    }

    // Key: menu_item, Value: quantity
    private TreeMap<menu_item, Pair<Integer,String>> ordered_items;
    private ArrayList<String> canteen_messages = new ArrayList<String>();

    private Integer order_id;
    private Integer order_status;
    private Double total_price;
    private customer customer;


    // Constructor
    public static order create_order(customer ordering_customer) {
        order new_order = new order();
        new_order.order_id = order_id_counter++;
        // order_id_counter++;
        new_order.ordered_items = new TreeMap<>();
        new_order.customer = ordering_customer;
        new_order.order_status = 0;

        return new_order;
    }

    // Getters
    public Integer get_order_id() {
        return order_id;
    }

    public TreeMap<menu_item, Pair<Integer,String>> get_ordered_items() {
        return ordered_items;
    }

    public customer get_customer() {
        return customer;
    }

    public Integer get_order_status() {
        return order_status;
    }

    public String get_order_status_string() {
        return Status_map.get(order_status);
    }

    public ArrayList<String> get_canteen_messages() {
        return canteen_messages;
    }

    public Double get_total_price() {
        return total_price;
    }
    
    // Setters
    public void set_customer(customer customer) {
        this.customer = customer;
    }
    
    public void set_order_status(Integer order_status) {
        this.order_status = order_status;
    }

    public void update_total_price() {
        Double total = 0.0;
        for (menu_item item : ordered_items.keySet()) {
            total += item.get_price() * ordered_items.get(item).getFirst();
        }
        total_price = total;
    }

    


    // Functionalities

    // used by customer and admin
    public void add_item(menu_item item) {
        Pair<Integer,String> p = new Pair<>(1,"NULL");
        if (ordered_items.containsKey(item)) {
            ordered_items.put(item, p);
            System.out.println("Quantity of " + item.get_name() + " increased to " + ordered_items.get(item).getFirst());
        } else {
            ordered_items.put(item, p);
            System.out.println(item.get_name() + " added to order");
        }
        // this.add_item(item, 1);
    }

    public void add_item(menu_item item, Integer quantity) {
        Integer new_quantity = quantity;
        if (ordered_items.containsKey(item)) {
            new_quantity += ordered_items.get(item).getFirst();
            ordered_items.get(item).setFirst(new_quantity);
            System.out.println("Quantity of " + item.get_name() + " increased to " + ordered_items.get(item).getFirst());
        } else {
            Pair<Integer,String> p = new Pair<>(new_quantity,"NULL");
            ordered_items.put(item, p);            
            System.out.println(item.get_name() + " with quantity (" + quantity + ") added to order");
        }
    }

    public void add_item(menu_item item, Integer quantity, String special_request) {
        Integer new_quantity = quantity;
        if (ordered_items.containsKey(item)) {
            new_quantity += ordered_items.get(item).getFirst();
            ordered_items.get(item).setFirst(new_quantity);
            ordered_items.get(item).setSecond(special_request);
            System.out.println("Quantity of " + item.get_name() + " increased to " + ordered_items.get(item).getFirst());
        } else {
            Pair<Integer,String> p = new Pair<>(new_quantity,special_request);
            ordered_items.put(item, p);
            System.out.println(item.get_name() + " with quantity (" + quantity + ") added to order");
        }
    }

    public void remove_item(menu_item item) {
        if (ordered_items.containsKey(item)) {
            ordered_items.remove(item);
            System.out.println(item.get_name() + " removed from order");
            return;
        }
        System.out.println("Item not found in order");
    }

    public void remove_item(menu_item item, Integer quantity) {
        if (ordered_items.containsKey(item)) {
            if (ordered_items.get(item).getFirst() > quantity) {
                Integer new_quantity = ordered_items.get(item).getFirst() - quantity;
                ordered_items.get(item).setFirst(new_quantity);
                System.out.println("Quantity of " + item.get_name() + " decreased to " + ordered_items.get(item).getFirst());
            } else {
                ordered_items.remove(item);
                System.out.println(item.get_name() + " removed from order");
            }
        }
        System.out.println("Item not found in order (remove_item)");
    }

    public void update_item_quantity(menu_item item, Integer quantity) {
        if (ordered_items.containsKey(item)) {
            Integer new_quantity = quantity;
            ordered_items.get(item).setFirst(new_quantity);
            System.out.println("Quantity of " + item.get_name() + " updated to " + ordered_items.get(item).getFirst());
            return;
        }
        System.out.println("Item not found in order (update_item_quantity)");
    }

    public Integer get_item_quantity(menu_item item) {
        if (!(ordered_items.containsKey(item))) {
            System.out.println("Item not found in order (get_item_quantity)");
            return -1;
        }
        return ordered_items.get(item).getFirst();
    }


    public void add_special_request(menu_item item, String request) {
        if (ordered_items.containsKey(item)) {
            ordered_items.get(item).setSecond(request);
            System.out.println("Special request added to " + item.get_name());
        }
        System.out.println("Item not found in order");
    }

    public void remove_special_request(menu_item item) {
        if (ordered_items.containsKey(item)) {
            ordered_items.get(item).setSecond("NULL");
            System.out.println("Special request removed from " + item.get_name());
        }
        System.out.println("Item not found in order");
    }

    public void update_special_request(menu_item item,String new_request) {
        if (ordered_items.containsKey(item)) {
            ordered_items.get(item).setSecond(new_request);
            System.out.println("Special request updated for " + item.get_name());
        }
        System.out.println("Item not found in order");
    }

    public void view_order() {
        System.out.println("Order ID: " + order_id);
        System.out.println("Customer: " + customer.get_customer_name());
        System.out.println("Status: " + Status_map.get(order_status));
        if (ordered_items.isEmpty()) {
            System.out.println("No items in order");
            return;
        }
        System.out.println("Items:");
        System.out.println("Item name | Quantity | Special request (if any)");
        for (menu_item item : ordered_items.keySet()) {
            System.out.print(item.get_name() + " | " + ordered_items.get(item).getFirst());
            if (!(ordered_items.get(item).getSecond().equals("NULL"))) {
                System.out.println(" | Special request: " + ordered_items.get(item).getSecond());
            } else {
                System.out.println();
            }
        }
        update_total_price();
    }

    public void view_order_summary() {
        this.view_order();
        System.out.println("Total price: " + total_price);
    }

    public void cancel_order() {
        order_status = 5;
        System.out.println("Order cancelled");
    }

    public void place_order() {
        order_status = 0;
        System.out.println("Order placed");
    }

    public order reorder(Integer order_id) {
        order new_order = create_order(customer);
        new_order = customer.get_past_order(order_id);
        System.out.println("Order updated");
        return new_order;
    }

    // used by admin and canteen staff
    public void update_order_status(Integer status) {
        if (Status_map.containsKey(status)) {
            order_status = status;
            System.out.println("Order status updated to " + Status_map.get(status));
        }
        System.out.println("Invalid status");
    }

    public void deny_order() {
        order_status = 4;
        System.out.println("Order denied");
    }

    public StringBuilder order_string() {
        
        StringBuilder order_items = new StringBuilder();

        order_items.append("Order ID: " + order_id + " | Customer: " + customer.get_customer_name() + ((customer.is_vip()) ? "( VIP )" : "")  + " | Status: "
                + Status_map.get(order_status) + " | Total price: " + total_price + "\nItems: \n");

        for (menu_item item : ordered_items.keySet()) {

            if (!(ordered_items.get(item).getSecond().equals("NULL"))) {
                order_items.append(item.get_name() + " | " + ordered_items.get(item).getFirst() + " | Special request: " + ordered_items.get(item).getSecond() + "\n");
            } else {
                order_items.append(item.get_name() + " | " + ordered_items.get(item).getFirst() + "\n");
            }
        }        

        return order_items;
    }

}
