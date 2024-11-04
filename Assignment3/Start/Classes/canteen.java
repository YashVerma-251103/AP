package Start.Classes;

import java.util.Scanner;
import java.util.TreeMap;

import Start.Structures.Pair;

public class canteen {

    private static Scanner canteen_sc = new Scanner(System.in);
    
    // Only one instance of canteen is allowed.
    private static canteen instance = null;
    private static order current_order_being_processed = null;
    private static Integer day = 1;

    public static canteen get_instance() {
        if (instance == null) {
            instance = new canteen();
        }
        return instance;
    }

    // Data Sets
    private static TreeMap<Integer, menu_item> menu = new TreeMap<>();
    private static TreeMap<Integer, order> current_orders = new TreeMap<>();
    private static TreeMap<Integer, order> priority_orders = new TreeMap<>();
    private static TreeMap<Integer, order> order_history = new TreeMap<>();
    private static TreeMap<Integer, TreeMap<Integer, order>> order_history_ledger = new TreeMap<>();

    public TreeMap<Integer, order> get_current_orders() {
        return current_orders;
    }
    public TreeMap<Integer, order> get_priority_orders() {
        return priority_orders;
    }
    public TreeMap<Integer, order> get_order_history() {
        return order_history;
    }
    public void update_order_history(order o) {
        order_history.put(o.get_order_id(), o);
    }



    // Financials
    private static Double earnings = 0.0;
    private static Double total_refund = 0.0;
    private static void update_refund(Double amount) {
        total_refund += amount;
    }
    private void remove_item_refunds(menu_item item, order o){
        Double refund_amount = (item.get_price()) * (o.get_ordered_items().get(item).getFirst());
        o.get_canteen_messages()
                .add("Item (" + item.get_name() + ") is no longer available.\nTotal quantity of " + item.get_name()
                        + " in your order is now 0.\nRefund Amount: Rs. (" + (refund_amount)
                        + ") will be refunded to your wallet.");
        o.get_ordered_items().remove(item);
        o.update_total_price();
        // update_refund(refund_amount);
        process_refund(o, refund_amount);
    }
    public void update_earnings(Double amount) {
        earnings += amount;
    }
    public static void process_refund(order o, Double amount) {
        // process the refund
        o.get_customer().add_to_wallet(amount);
        String message = "Refund Amount: Rs. (" + amount + ") has been refunded to your wallet.";
        o.get_canteen_messages().add(message);
        update_transaction_history(o, amount, "Refund");
    }    
    public static void process_refund(order o) {
        Double curent_refund = 0.0;

        if (o.get_order_status().equals(4) || o.get_order_status().equals(5)) {
            curent_refund = o.get_total_price() + total_refund;
        }
        else if (o.get_order_status().equals(3)){
            curent_refund = total_refund;
        }

        if (curent_refund == 0.0) {
            return;
        }

        o.get_customer().add_to_wallet(curent_refund);
        String message = "Refund Amount: Rs. (" + curent_refund + ") has been refunded to your wallet.";
        o.get_canteen_messages().add(message);
        update_transaction_history(o, curent_refund, "Refund");
        update_refund(0.0);
    }
    private static void update_transaction_history(order o, Double amount, String message) {
        // update the transaction history
        o.get_customer().update_transaction_history(amount, message);

    }


    // Utility Functions
    public TreeMap<Integer, menu_item> get_menu() {
        return menu;
    }



    // functionalities
    // Menu Management
    public void view_full_menu() {
        // view the full menu
        if (menu.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        System.out.println("Menu:\nItem Id : Item : Price (per unit)");
        for (menu_item item : menu.values()) {
            System.out.println(item.get_id() + " : " + item.get_name() + " : Rs. " + item.get_price());
        }
    }
    public void view_menu() {
        // view the menu
        if (menu.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        while (true) { 
            System.out.println("how would you like to view the menu?");
            System.out.println("0. Go Back");
            System.out.println("1. View Full Menu");
            System.out.println("2. View Menu by Price");
            System.out.println("3. View Menu by Category");
            System.out.print("Enter your choice: ");
            Integer choice = canteen_sc.nextInt();
            canteen_sc.nextLine();
            if (choice == 0) {
                return;
            } else if (choice == 1) {
                view_full_menu();
            } else if (choice == 2) {
                while (true) {
                    System.out.println("How would you like to view the menu by price?");
                    System.out.println("0. Go Back");
                    System.out.println("1. Ascending Order");
                    System.out.println("2. Descending Order");
                    System.out.print("Enter your choice: ");
                    Integer price_choice = canteen_sc.nextInt();
                    canteen_sc.nextLine();
                    if (price_choice == 0) {
                        break;
                    } else if (price_choice == 1) {
                        view_menu_by_price(true);
                    } else if (price_choice == 2) {
                        view_menu_by_price(false);
                    } else {
                        System.out.println("Invalid Choice.");
                        continue;
                    }
                    break;
                }
            } else if (choice == 3) {
                Integer category_choice = category_selection();
                if (category_choice == 0) {
                    break;
                } else {
                    view_menu_by_category(category_choice - 1);
                }
            } else {
                System.out.println("Invalid Choice.");
            }
        }
    }
    private Integer category_selection(){
        while (true) {
            
            System.out.println("Which category would you like to view?");
            System.out.println("0. Go Back");
            for (Integer i = 0; i < menu_item.get_category_menu().size(); i++) {
                System.out.println((i + 1) + ". " + menu_item.get_category_menu().get(i).firstEntry().getValue().get_category_string());
            }
            System.out.print("Enter your choice: ");
            Integer category_choice = canteen_sc.nextInt();
            canteen_sc.nextLine();
            if (category_choice > menu_item.get_category_counter()) {
                System.out.println("Invalid Choice.");
                continue;
            }
            return category_choice;
        }
    }
    public void view_menu_by_price(Boolean ascending) {
        // view the menu by price
        if (menu.isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        TreeMap<Double, menu_item> price_menu = new TreeMap<>();
        for (menu_item item : menu.values()) {
            price_menu.put(item.get_price(), item);
        }
        if (ascending) {
            System.out.println("Menu:\nItem Id : Item : Price (per unit)");
            for (menu_item item : price_menu.values()) {
                System.out.println(item.get_id() + " : " + item.get_name() + " : Rs. " + item.get_price());
            }
        } else {
            System.out.println("Menu:\nItem Id : Item : Price (per unit)");
            for (menu_item item : price_menu.descendingMap().values()) {
                System.out.println(item.get_id() + " : " + item.get_name() + " : Rs. " + item.get_price());
            }
        }
    }
    private void view_menu_by_category(Integer category) {
        // view the menu by category
        // if (menu.isEmpty()) {
        //     System.out.println("No items found.");
        //     return;
        // }
        // if (menu_item.get_category_menu().get(category).isEmpty()) {
        //     System.out.println("No items found in this category.");
        //     return;
        // }
        // System.out.println("Menu:\nItem Id : Item : Price (per unit)");
        // for (menu_item item : menu_item.get_category_menu().get(category).values()) {
        //     System.out.println(item.get_id() + " : " + item.get_name() + " : Rs. " + item.get_price());
        // }

        TreeMap<Integer,menu_item> menu = menu_item.get_menu_by_category(category);
        if (menu.isEmpty()) {
            System.out.println("No items found in this category.");
            return;
        }
        menu.forEach((k,v) -> {
            System.out.println(v.get_id() + " : " + v.get_name() + " : Rs. " + v.get_price());
        });

    }

    public void add_item(menu_item item) {
        menu.put(item.get_id(), item);
    }
    public void remove_item(menu_item item) {
        menu.remove(item.get_id());
        for (order o : current_orders.values()) {
            if (o.get_ordered_items().containsKey(item)) {
                remove_item_refunds(item, o);
            }
        }
    }
    
    
    
    // Order Management
    public void view_order_history(Integer status) {
        // view order history
        if (order_history.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        if (status != -1) {
            for (order o : order_history.values()) {
                if (o.get_order_status() == status) {
                    o.view_order_summary();
                }
            }
            return;
        }
        for (order o : priority_orders.values()) {
            o.view_order_summary();
        }
    }
    public void view_all_orders_EOD() {
        // view all orders
        if (order_history.isEmpty()) {
            System.out.println("No orders were received today.");
            return;
        }
        for (order o : order_history.values()) {
            o.view_order_summary();
        }
    }
    public void view_order(Integer order_id) {
        // view a particular order
        if (order_history.containsKey(order_id)) {
            order_history.get(order_id).view_order_summary();
        } else {
            System.out.println("Order not found.");
        }
    }
    public void view_delivered_orders() {
        // view delivered orders
        view_order_history(3);
    }
    public void view_denied_orders() {
        // view denied orders
        view_order_history(4);
    }
    public void view_cancelled_orders() {
        // view cancelled orders
        view_order_history(5);
    }
    public void view_pending_orders() {
        // view pending orders
        if (priority_orders.isEmpty() && current_orders.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }
        if (!(priority_orders.isEmpty())) {
            System.out.println("Priority Orders:\n");
            for (order o : priority_orders.values()) {
                if (o.get_order_status() == 0) {
                    o.view_order_summary();
                }
            }
            System.out.println();
        }
        if (!(current_orders.isEmpty())) {
            System.out.println("Current Orders:\n");
            for (order o : current_orders.values()) {
                if (o.get_order_status() == 0) {
                    o.view_order_summary();
                }
            }
            System.out.println();
        }
    }

    public void add_order(order o) {
        if (o.get_customer().is_vip()) {
            priority_orders.put(o.get_order_id(), o);
            return;
        }
        current_orders.put(o.get_order_id(), o);
    }
    public void update_order(Integer order_id, order o) {
        if (o.get_customer().is_vip()) {
            priority_orders.put(order_id, o);
            current_orders.remove(order_id);
            return;
        }
        priority_orders.remove(order_id);
        current_orders.put(order_id, o);
    }    
    public void remove_order(Integer order_id, order o) {
        update_order_history(o);
        priority_orders.remove(order_id);
        current_orders.remove(order_id);
    }
    public static void handle_special_request(menu_item item, order o) {
        String request = o.get_ordered_items().get(item).getSecond();
        if (request.equals("NULL")) {
            return;
        }
        System.out.println("Special Request for " + item.get_name() + " : " + request);
        System.out.println("Do you want to add this special request to the order? (Y/N)");
        String admin_choice = canteen_sc.nextLine();
        String canteen_message = "Special Request for " + item.get_name() + " : " + request + " has been DENIED.";
        if (admin_choice.equals("Y") || admin_choice.equals("y")) {
            System.out.println("Adding Special Request to the order.");
            canteen_message = "Special Request for " + item.get_name() + " : " + request + " has been added.";
        }
        o.get_canteen_messages().add(canteen_message);
        return;
    }    
    private void remove_order_from_queue(order o) {
        if (o.get_customer().is_vip()) {
            priority_orders.remove(o.get_order_id());
        } else {
            current_orders.remove(o.get_order_id());
        }
    }
    private static void process_order_item_of_stock(menu_item item, order o) {
        Double refund_amount = (item.get_price()) * (o.get_ordered_items().get(item).getFirst());
        o.get_canteen_messages()
                .add("Item (" + item.get_name() + ") is out of stock.\nTotal quantity of " + item.get_name()
                        + " in your order is now 0.\nRefund Amount: Rs. (" + (refund_amount)
                        + ") will be refunded to your wallet.");
        o.get_ordered_items().remove(item);
        o.update_total_price();
        update_refund(refund_amount);
    }
    private static void process_order_item_of_stock(menu_item item, order o, boolean vip) {
        Double refund_amount = (item.get_price())
                * (o.get_ordered_items().get(item).getFirst() - item.get_quantity_in_stock());
        o.get_canteen_messages().add("Item (" + item.get_name() + ") is only available in limited quantity. (Only "
                + item.get_quantity_in_stock()
                + " left).\nSince you are a VIP customer, we have added the remaining to your order.\nTotal quantity of "
                + item.get_name() + " in your order is now " + item.get_quantity_in_stock()
                + ".\nRemaining Balance: Rs. (" + (refund_amount) + ") will be refunded to your wallet.");
        o.get_ordered_items().get(item).setFirst(item.get_quantity_in_stock());
        item.set_quantity(0);
        o.update_total_price();
        update_refund(refund_amount);
    }
    private void order_finished(order o) {
        // update the earnings
        process_refund(o);
        update_earnings(o.get_total_price());
        // update the order history
        update_order_history(o);
        // remove the order from the queue
        remove_order_from_queue(o);
    }
    private void order_denied(order o) {
        // update the order history
        update_order_history(o);
        // remove the order from the queue
        remove_order_from_queue(o);
        // process the refund
        process_refund(o);
    }
    private void order_cancelled(order o) {
        // update the order history
        update_order_history(o);
        // remove the order from the queue
        remove_order_from_queue(o);
        // process the refund
        process_refund(o);
    }
    public void update_order_status(order o) {
        // update the order status
        if (o.get_order_status() == 3) {
            order_finished(o);
        } else if (o.get_order_status() == 4) {
            order_denied(o);
        } else if (o.get_order_status() == 5) {
            order_cancelled(o);
        } else{
            o.set_order_status(o.get_order_status() + 1);
        }
        System.out.println("Order Status Updated: " + o.get_order_status());
    }
    public void process_order() {
        order o = get_next_order();
        if (o == null) {
            // next_step_trigger=false;
            System.out.println("No orders to process.");
            return;
        }
        System.out.println("Processing Order Status: " + o.get_order_status());
        if (o.get_order_status() == 2 || o.get_order_status() == 1) {
            update_order_status(o);
            return;
        }

        if (o.get_order_status() != 4 && o.get_order_status() != 5 && o.get_order_status() != 3) {
            System.out.println("Processing Order: " + o.get_order_id());
            // process the order
            process_order(o);
        }
        
        // update the order status
        update_order_status(o);
        if (o.get_order_status() == 4 || o.get_order_status() == 5 || o.get_order_status() == 3) {
            current_order_being_processed=null;
        }

        // next_step_trigger=true;    
    }
    public static void process_order(order o) {
        // process the order
        for (menu_item item : o.get_ordered_items().keySet()) {
            // Double refund_amount;
            if (item.get_quantity_in_stock() == 0) {
                if (item.get_available()) {
                    item.set_available(false);
                }
                process_order_item_of_stock(item, o);
                continue;
            }
            Integer new_quantity = item.get_quantity_in_stock() - o.get_ordered_items().get(item).getFirst();
            if (new_quantity < 0) {
                process_order_item_of_stock(item, o, true);
                item_not_available_special_request_handler(item,o);
                // handle_special_request(item, o);
                item.set_available(false);
                continue;
            }
            // in stock
            item.set_quantity(new_quantity);
            handle_special_request(item, o);
        }
    }
    private static void item_not_available_special_request_handler(menu_item item, order o) {
        System.out.println("item_not_available_special_request_handler");
        String request = o.get_ordered_items().get(item).getSecond();
        if (request.equals("NULL")) {
            return;
        }
        String canteen_message = "Special Request for " + item.get_name() + " : " + request + " has been DENIED due to insufficient stock.";
        o.get_canteen_messages().add(canteen_message);
        return;
    }
    private order get_next_order() {
        order o = null;
        if (current_order_being_processed != null) {
            return current_order_being_processed;
        }
        if (!priority_orders.isEmpty()) {
            o = priority_orders.pollFirstEntry().getValue();
        } else if (!current_orders.isEmpty()) {
            o = current_orders.pollFirstEntry().getValue();
        }
        current_order_being_processed = o;
        return o;
    }

    // EOD Functions
    public void end_of_day() {
        // end of the day
        // view all orders
        view_all_orders_EOD();
        // end the shift
        end_shift();
    }
    private void end_shift() {
        // end the shift
        // generate the report
        generate_report();
        // update order history ledger
        update_order_history_ledger();
        // reset the earnings, current_orders, priority_orders
        data_reset();

    }
    public void generate_report() {
        // generate the report
        System.out.println("Earnings: Rs. " + earnings);
        System.out.println("Total Orders Received: " + order_history.size());
        System.out.println("Items out of Stock:");
        for (menu_item item : menu.values()) {
            if (!item.get_available()) {
                System.out.println(item.get_name());
            }
        }
    }
    private void update_order_history_ledger() {
        // update the order history ledger
        order_history_ledger.put(day, order_history);
        day++;
    }
    private void data_reset() {
        // reset the earnings, current_orders, priority_orders
        // earnings = 0.0;
        // current_orders.clear();
        // priority_orders.clear();
        order_history.clear();
    }


}