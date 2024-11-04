package Start.Classes;
// Customer remake with better structure, more functionalities, checks and sorted code

import java.util.Scanner;
import java.util.TreeMap;

// import Start.Classes.canteen;
// import Start.Classes.menu_item;
// import Start.Classes.order;
import Start.Structures.Pair;
import Start.Structures.review;
import Start.Structures.review_data_base;

public class customer {
    private static final Scanner customer_sc = new Scanner(System.in);
    private static final canteen ct = canteen.get_instance();
    private static Double vip_cost = 100.0;
    // private static Double vip_discount = 0.1;
    private static Integer customer_id_counter = 1;
    public static TreeMap<Integer, customer> customer_db = new TreeMap<Integer, customer>();

    // Personal Details
    private Integer customer_id;
    private Boolean vip;
    private String customer_name;
    private String customer_address;

    private TreeMap<Integer, order> cart = new TreeMap<Integer, order>();
    private TreeMap<Integer, order> order_history = new TreeMap<Integer, order>();
    private TreeMap<Integer, order> current_orders = new TreeMap<Integer, order>();

    // financials
    private Double wallet = 0.0;
    private Integer transaction_id_counter = 0;
    private TreeMap<Integer, Pair<Double, String>> transaction_history = new TreeMap<Integer, Pair<Double, String>>();

    public void update_transaction_history(Double amount, String description) {
        transaction_history.put(transaction_id_counter++, new Pair<Double, String>(amount, description));
    }

    private Boolean purchase(Double amount, String description) {
        if (wallet < amount) {
            System.out.println("Insufficient funds. Please add money to your wallet.");
            return false;
        }
        wallet -= amount;
        update_transaction_history(amount, description);
        return true;
    }

    private Boolean purchase(Double amount) {
        return purchase(amount, "Placed an order");
    }

    // Constructor
    public static customer create_customer(String name, String address, Double wallet, Boolean vip) {
        customer new_customer = new customer();
        new_customer.customer_id = customer_id_counter++;
        new_customer.vip = vip;
        new_customer.customer_name = name;
        new_customer.wallet = wallet;
        customer_db.put(new_customer.get_customer_id(), new_customer);
        return new_customer;
    }

    // Utility Functions
    public Boolean is_vip() {
        return this.vip;
    }

    public String get_customer_name() {
        return this.customer_name;
    }

    public void set_customer_name(String name) {
        this.customer_name = name;
    }

    public String get_customer_address() {
        return this.customer_address;
    }

    public void set_customer_address(String address) {
        this.customer_address = address;
    }

    public Integer get_customer_id() {
        return this.customer_id;
    }

    public Double get_wallet() {
        return this.wallet;
    }

    public void add_to_wallet(Double amount) {
        this.wallet += amount;
    }

    public order get_past_order(Integer order_id) {
        return order_history.get(order_id);
    }



    // for admin
    public static void end_of_day() {
        // WIP
        System.out.println("End of day for all customers.\nAll pending current orders will be delievered on the next working day.");
    }

    public static void customer_login() {
        // WIP
        
        while (true) {
            System.out.print("Enter your customer_id: ");
            Integer id = customer_sc.nextInt();
            customer_sc.nextLine();
            if (customer_db.containsKey(id)) {
                customer c = customer_db.get(id);
                if (c.get_customer_id() == id) {
                    System.out.println("Login successful!");
                    c.customer_interface();
                    break;
                }
            }
            System.out.println("Invalid customer_id. Please try again.");
        }
        
    }


    // Customer Interface
    public void customer_interface() {
        // Customer functionalities
        // become_vip
        // browse_menu
        // cart_operations
        // order_tracking
        // item_reviews
        // check_financials

        print_customer_greetings();
        while (true) {
            print_customer_menu((is_vip()));
            Integer choice = get_customer_choice();
            Boolean continuation = perform_customer_action(choice, is_vip());
            if (!continuation) {
                break;
            }
        }
    }

    private void print_customer_greetings() {
        System.out.println("Welcome " + this.get_customer_name() + " !");
    }

    private void print_customer_menu(Boolean is_vip) {
        System.out.println("0. Exit");
        int i = 1;
        if (!is_vip) {
            System.out.println(i++ + ". Become VIP");
        }
        System.out.println(i++ + ". Browse Menu");
        System.out.println(i++ + ". Cart Operations");
        System.out.println(i++ + ". Order Tracking");
        System.out.println(i++ + ". Item Reviews");
        System.out.println(i++ + ". Check Financials");
    }

    private Integer get_customer_choice() {
        System.out.print("Enter your choice: ");
        Integer choice = customer_sc.nextInt();
        customer_sc.nextLine();
        return choice;
    }

    private Boolean perform_customer_action(Integer choice, Boolean is_vip) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (!(is_vip)) {
            if (choice == i++) {
                // Become VIP
                become_vip();
            }
        }  if (choice == i++) {
            // Browse Menu
            browse_menu();
        } else if (choice == i++) {
            // Cart Operations
            cart_operations();
        } else if (choice == i++) {
            // Order Tracking
            order_tracking();
        } else if (choice == i++) {
            // Item Reviews
            item_reviews();
        } else if (choice == i++) {
            // Check Financials
            check_financials();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    // Become VIP
    private void become_vip() {
        if (this.vip) {
            System.out.println("You are already a VIP customer.");
            return;
        }
        System.out.println("You are about to become a VIP customer!");
        System.out.println(
                "This will cost you (" + vip_cost + ") and your orders will get priority over regular customers.");
        System.out.print("Do you want to continue? (y/n): ");
        String choice = customer_sc.nextLine();
        if (choice.equalsIgnoreCase("y")) {
            if (purchase(vip_cost, "Became a VIP customer")) {
                this.vip = true;
                System.out.println("You are now a VIP customer!\nCongratulations!");
                update_current_orders();
            }
        }
    }
    private void update_current_orders() {
        for (order o : current_orders.values()) {
            ct.update_order(o.get_order_id(), o);
        }
    }


    // Browse Menu
    private void browse_menu() {
        // functionalities
        // view_all_items
        // search_functionality
        // filter_by_category
        // sort_by_price

        while (true) {
            print_browse_menu();
            Integer choice = get_customer_choice();
            Boolean continuation = perform_browse_menu_action(choice);
            if (!continuation) {
                break;
            }
        }
    }

    private void print_browse_menu() {
        System.out.println("0. Go back");
        System.out.println("1. View all items");
        System.out.println("2. Search for an item");
        System.out.println("3. Filter by Category");
        System.out.println("4. Sort by Price");
    }

    private Boolean perform_browse_menu_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // View all items
            view_all_items();
        } else if (choice == i++) {
            // Search for an item
            search_functionality();
        } else if (choice == i++) {
            // Filter by Category
            filter_by_category();
        } else if (choice == i++) {
            // Sort by Price
            sort_by_price();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void view_all_items() {
        TreeMap<Integer, menu_item> menu = ct.get_menu();
        if (menu.isEmpty()) {
            System.out.println("No items in the menu.");
            return;
        }
        System.out.println("Item ID | Name | Price | Description | Availability | Quantity in Stock");
        for (menu_item item : (menu.values())) {
            System.out.println(item.get_id() + " | " + item.get_name() + " | " + item.get_price() + " | "
                    + item.get_description() + " | " + item.get_available() + " | " + item.get_quantity_in_stock());
        }
    }

    private void search_functionality() {
        while (true) {
            System.out.println("0. Go back");
            System.out.println("Enter the item_id of the item you want to search for: ");
            Integer id = get_customer_choice();
            Boolean continuation = perform_search_action(id);
            if (!continuation) {
                return;
            }
        }
    }

    private Boolean perform_search_action(Integer id) {
        int i = 0;
        if (id == i++) {
            return false;
        } else if (ct.get_menu().containsKey(id)) {
            menu_item item = ct.get_menu().get(id);
            System.out.println("Name: " + item.get_name());
            System.out.println("Price: " + item.get_price());
            System.out.println("Description: " + item.get_description());
            System.out.println("Availability: " + item.get_available());
            System.out.println("Quantity in Stock: " + item.get_quantity_in_stock());
        } else {
            System.out.println("Invalid item_id. Please try again.");
        }
        return true;
    }

    private void filter_by_category() {
        while (true) {
            print_current_categories();
            Integer choice = get_customer_choice();
            Boolean continuation = perform_filter_action(choice);
            if (!continuation) {
                return;
            }
        }
    }

    private void print_current_categories() {
        System.out.println("0. Go back");
        System.out.println("Current Categories:");
        for (Integer i = 0; i < menu_item.get_category_counter(); i++) {
            System.out.println((i + 1) + ". " + menu_item.get_categories()[i]);
        }
    }

    private Boolean perform_filter_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice > 0 && choice <= menu_item.get_category_counter()) {
            // Filter by category
            show_menu_by_category(choice);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void show_menu_by_category(Integer choice) {
        TreeMap<Integer, menu_item> category_menu = menu_item.get_menu_by_category(choice - 1);
        if (category_menu.isEmpty()) {
            System.out.println("No items in this category.");
            return;
        }

        System.out.println("Item ID | Name | Price | Description | Availability | Quantity in Stock");
        for (menu_item item : (category_menu.values())) {
            System.out.println(item.get_id() + " | " + item.get_name() + " | " + item.get_price() + " | "
                    + item.get_description() + " | " + item.get_available() + " | " + item.get_quantity_in_stock());
        }
    }

    private void sort_by_price() {
        while (true) {
            print_sort_menu();
            Integer choice = get_customer_choice();
            Boolean continuation = perform_sort_action(choice);
            if (!continuation) {
                return;
            }
        }
    }

    private void print_sort_menu() {
        System.out.println("0. Go back");
        System.out.println("1. Sort by price in ascending order");
        System.out.println("2. Sort by price in descending order");
    }

    private Boolean perform_sort_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // Sort by price in ascending order
            sort_menu_by_price(true);
        } else if (choice == i++) {
            // Sort by price in descending order
            sort_menu_by_price(false);
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void sort_menu_by_price(Boolean ascending) {
        // TreeMap<Integer, menu_item> menu = ct.get_menu();
        // if (menu.isEmpty()) {
        //     System.out.println("No items in the menu.");
        //     return;
        // }

        // TreeMap<Double, menu_item> sorted_menu = new TreeMap<Double, menu_item>();

        // for (menu_item item : menu.values()) {
        //     sorted_menu.put(item.get_price(), item);
        // }

        // if (!ascending) {
        //     sorted_menu = (TreeMap<Double, menu_item>) sorted_menu.descendingMap();
        // }

        // System.out.println("Item ID | Name | Price | Description | Availability | Quantity in Stock");
        // for (menu_item item : (sorted_menu.values())) {
        //     System.out.println(item.get_id() + " | " + item.get_name() + " | " + item.get_price() + " | "
        //             + item.get_description() + " | " + item.get_available() + " | " + item.get_quantity_in_stock());
        // }
        ct.view_menu_by_price(ascending);
    }

    // Cart Operations
    private void cart_operations() {
        // functionalities
        // add item
        // special requests
        // modify item quantity
        // checkout

        while (true) {
            print_cart_menu();
            Integer choice = get_customer_choice();
            Boolean continuation = perform_cart_action(choice);
            if (!continuation) {
                break;
            }
        }
    }

    private void print_cart_menu() {
        System.out.println("0. Go back");
        System.out.println("1. View cart");
        System.out.println("2. Create a new order");
        System.out.println("3. Modify order");
        System.out.println("4. Remove order from cart");
        System.out.println("5. Checkout");
    }

    private Boolean perform_cart_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // View cart
            view_cart();
        } else if (choice == i++) {
            // Create a new order
            create_new_order();
        } else if (choice == i++) {
            // Modify order
            modify_order();
        } else if (choice == i++) {
            // Remove order from cart
            remove_order_from_cart();
        } else if (choice == i++) {
            // Checkout
            checkout();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void view_cart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        for (order o : cart.values()) {
            o.view_order();
        }

    }

    private void create_new_order() {
        while (true) {
            System.out.println("0. Go back");
            System.out.println("1. create a new order");
            Integer choice = get_customer_choice();
            Boolean continuation = perform_create_order_action(choice);
            if (!continuation) {
                return;
            }
        }
    }

    private Boolean perform_create_order_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // create a new order
            create_order();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void create_order() {
        order new_order = order.create_order(this);
        while (true) {
            System.out.println("0. Go back");
            System.out.println("1. Add item to order");
            System.out.println("2. Done adding items");
            Integer choice = get_customer_choice();
            if (choice == 0) {
                return;
            } else if (choice == 1) {
                add_item_to_order(new_order);
            } else if (choice == 2) {
                cart.put(new_order.get_order_id(), new_order);
                return;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private void add_item_to_order(order new_order) {
        Integer item_id = get_order_item_id();
        menu_item item = ct.get_menu().get(item_id);
        if (!(item.get_available())) {
            System.out.println("Item not available. Please try again later!");
            return;
        }
        Integer quantity = get_order_item_quantity(item);
        new_order.add_item(item, quantity);
    }

    private Integer get_order_item_id() {
        System.out.println("Enter the item_id of the item you want to add to your cart: ");
        Integer item_id = get_customer_choice();
        if (!ct.get_menu().containsKey(item_id)) {
            System.out.println("Invalid item_id. Please try again.");
            return get_order_item_id();
        }
        return item_id;
    }

    private Integer get_order_item_quantity(menu_item item) {
        System.out.println("Enter the quantity of the item you want to add to your cart: ");
        Integer quantity = get_customer_choice();
        if (quantity <= 0) {
            System.out.println("Invalid quantity. Please try again.");
            return get_order_item_quantity(item);
        }
        if (quantity > item.get_quantity_in_stock()) {
            System.out.println("Quantity not available. Please try again.");
            return get_order_item_quantity(item);
        }
        return quantity;
    }

    private void modify_order() {
        while (true) {
            System.out.println("0. Go back");
            System.out.println("1. View cart");
            System.out.println("2. Modify order");
            Integer choice = get_customer_choice();
            Boolean continuation = perform_modify_order_action(choice);
            if (!continuation) {
                return;
            }
        }
    }

    private Boolean perform_modify_order_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // View cart
            view_cart();
        } else if (choice == i++) {
            // Modify order
            modify_existing_order();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void modify_existing_order() {
        while (true) {
            System.out.println("Press 0 to go back");
            System.out.println("Enter the order_id of the order you want to modify: ");
            Integer order_id = get_customer_choice();
            if (order_id == 0) {
                return;
            }
            if (cart.containsKey(order_id)) {
                order order = cart.get(order_id);
                order.view_order();
                while (true) {
                    print_order_modification_menu();
                    Integer choice = get_customer_choice();
                    Boolean continuation = perform_order_modification_action(choice, order);
                    if (!continuation) {
                        break;
                    }
                }
            } else {
                System.out.println("Invalid order_id. Please try again.");
            }
        }
    }

    private void print_order_modification_menu() {
        System.out.println("0. Go back");
        System.out.println("1. Add item to order");
        System.out.println("2. Remove item from order");
        System.out.println("3. Modify item quantity");
        System.out.println("4. Add special request");
        System.out.println("5. Remove special request");
        System.out.println("6. Update special request");
        System.out.println("7. Done modifying order");
    }

    private Boolean perform_order_modification_action(Integer choice, order o) {
        if (choice == 0) {
            return false;
        } else if (choice == 1) {
            add_item_to_order(o);
        } else if (choice == 2) {
            remove_item_from_order(o);
        } else if (choice == 3) {
            modify_item_quantity(o);
        } else if (choice == 4) {
            add_special_request(o);
        } else if (choice == 5) {
            remove_special_request(o);
        } else if (choice == 6) {
            update_special_request(o);
        } else if (choice == 7) {
            return false;
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void remove_item_from_order(order o) {
        Integer item_id = get_order_item_id();
        menu_item item = ct.get_menu().get(item_id);
        if (item == null) {
            System.out.println("Invalid item_id. Please try again.");
            return;
        }
        o.remove_item(item);
    }

    private void modify_item_quantity(order o) {
        Integer item_id = get_order_item_id();
        menu_item item = ct.get_menu().get(item_id);
        if (item == null) {
            System.out.println("Invalid item_id. Please try again.");
            return;
        }
        Integer current_quantity = o.get_item_quantity(item);
        if (current_quantity == -1) {
            System.out.println("Item not found in order.");
            return;
        }
        System.out.println("Current quantity of " + item.get_name() + " is " + current_quantity);
        Integer quantity = get_order_item_quantity(item);
        o.update_item_quantity(item, quantity);
    }

    private void add_special_request(order o) {
        Integer item_id = get_order_item_id();
        menu_item item = ct.get_menu().get(item_id);
        if (item == null) {
            System.out.println("Invalid item_id. Please try again.");
            return;
        }
        System.out.println("Enter the special request for " + item.get_name() + ": ");
        String request = customer_sc.nextLine();
        o.add_special_request(item, request);
    }

    private void remove_special_request(order o) {
        Integer item_id = get_order_item_id();
        menu_item item = ct.get_menu().get(item_id);
        if (item == null) {
            System.out.println("Invalid item_id. Please try again.");
            return;
        }
        o.remove_special_request(item);
    }

    private void update_special_request(order o) {
        Integer item_id = get_order_item_id();
        menu_item item = ct.get_menu().get(item_id);
        if (item == null) {
            System.out.println("Invalid item_id. Please try again.");
            return;
        }
        System.out.println("Enter the new special request for " + item.get_name() + ": ");
        String request = customer_sc.nextLine();
        o.update_special_request(item, request);
    }

    private void remove_order_from_cart() {
        while (true) {
            System.out.println("0. Go back");
            System.out.println("Enter the order_id of the order you want to remove: ");
            Integer order_id = get_customer_choice();
            if (order_id == 0) {
                return;
            }
            if (cart.containsKey(order_id)) {
                System.out.println("Are you sure you want to remove this order? (y/n): ");
                String choice = customer_sc.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    cart.remove(order_id);
                    System.out.println("Order removed from cart.");
                    return;
                }
                System.out.println("Order not removed.");
            } else {
                System.out.println("Invalid order_id. Please try again.");
            }
        }
    }

    private void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        while (true) {
            print_checkout_menu();
            Integer choice = get_customer_choice();
            Boolean continuation = perform_checkout_action(choice);
            if (!continuation) {
                return;
            }
        }
    }

    private void print_checkout_menu() {
        System.out.println("0. Go back");
        System.out.println("1. Checkout");
    }

    private Boolean perform_checkout_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // Checkout
            System.out.println("Enter the order id of the order you want to checkout: ");
            Integer order_id = get_customer_choice();
            Boolean success = checkout_order(order_id);
            if (success) {
                System.out.println("Order placed successfully.");
                return false;
            }
            return true;
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private Boolean checkout_order(Integer order_id) {
        if (cart.containsKey(order_id)) {
            order o = cart.get(order_id);
            if (purchase(o.get_total_price())) {
                order_history.put(o.get_order_id(), o);
                current_orders.put(o.get_order_id(), o);
                cart.remove(order_id);
                ct.add_order(o);
                return true;
            }
        } else {
            System.out.println("Invalid order_id. Please try again.");
        }
        return false;
    }

    // Order Tracking
    private void order_tracking() {
        // functionalities
        // view_current_orders
        // view_order_status
        // cancel_order
        // order_history

        while (true) {
            print_order_tracking_menu();
            Integer choice = get_customer_choice();
            Boolean continuation = perform_order_tracking_action(choice);
            if (!continuation) {
                break;
            }
        }
    }

    private void print_order_tracking_menu() {
        System.out.println("0. Go back");
        System.out.println("1. View current orders");
        System.out.println("2. View order status");
        System.out.println("3. Cancel order");
        System.out.println("4. Order history");
    }

    private Boolean perform_order_tracking_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // View current orders
            view_current_orders();
        } else if (choice == i++) {
            // View order status
            view_order_status();
        } else if (choice == i++) {
            // Cancel order
            cancel_order();
        } else if (choice == i++) {
            // Order history
            order_history();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void view_current_orders() {
        if (current_orders.isEmpty()) {
            System.out.println("You have no current orders.");
            return;
        }
        for (order o : current_orders.values()) {
            o.view_order();
        }
    }

    private void view_order_status() {
        while (true) {
            System.out.println("0. Go back");
            System.out.println("Enter the order_id of the order you want to track: ");
            Integer order_id = get_customer_choice();
            if (order_id == 0) {
                return;
            }
            if (current_orders.containsKey(order_id)) {
                order o = current_orders.get(order_id);
                System.out.println("Order ID: " + o.get_order_id());
                System.out.println("Order Status: " + o.get_order_status());
                Boolean canteen_message_check = ask_canteen_message();
                if (canteen_message_check) {
                    print_canteen_messages(o);
                }
            } else {
                System.out.println("Invalid order_id. Please try again.");
            }
        }
    }

    private Boolean ask_canteen_message() {
        System.out.print("Do you want to check for any messages from the canteen? (y/n): ");
        String choice = customer_sc.nextLine();
        return choice.equalsIgnoreCase("y");
    }

    private void print_canteen_messages(order o) {
        if (o.get_canteen_messages().isEmpty()) {
            System.out.println("No messages from the canteen.");
            return;
        }
        System.out.println("Messages from the canteen:");
        int i = 1;
        for (String message : o.get_canteen_messages()) {
            System.out.println(i++ + message);
        }
    }

    private void cancel_order() {
        while (true) {
            System.out.println("0. Go back");
            System.out.println("Enter the order_id of the order you want to cancel: ");
            Integer order_id = get_customer_choice();
            if (order_id == 0) {
                return;
            }
            if (current_orders.containsKey(order_id)) {
                order o = current_orders.get(order_id);
                System.out.println("Are you sure you want to cancel this order? (y/n): ");
                String choice = customer_sc.nextLine();
                if (choice.equalsIgnoreCase("y")) 
                {
                    o.update_order_status(5);
                    order_history.put(o.get_order_id(), o);
                    current_orders.remove(order_id);
                    // ct.get_current_orders().remove(order_id);
                    // ct.get_priority_orders().remove(order_id);
                    update_transaction_history(o.get_total_price(), "Cancelled an order will be refunded by canteen later.");
                    // add_to_wallet(o.get_total_price());
                    System.out.println("Order cancelled successfully.");
                    return;
                }
                System.out.println("Order not cancelled.");
            } else {
                System.out.println("Invalid order_id. Please try again.");
            }
        }
    }

    private void order_history() {
        if (order_history.isEmpty()) {
            System.out.println("You have no order history.");
            return;
        }
        for (order o : order_history.values()) {
            o.view_order();
        }
    }

    // Item Reviews
    private void item_reviews() {
        // functionalities
        // provide_reviews for item they have bought
        // view_reviews for an item

        while (true) {
            print_item_reviews_menu();
            Integer choice = get_customer_choice();
            Boolean continuation = perform_item_reviews_action(choice);
            if (!continuation) {
                break;
            }
        }
    }

    private void print_item_reviews_menu() {
        System.out.println("0. Go back");
        System.out.println("1. Provide reviews for an item");
        System.out.println("2. View reviews for an item");
    }

    private Boolean perform_item_reviews_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // Provide reviews for an item
            provide_reviews();
        } else if (choice == i++) {
            // View reviews for an item
            view_reviews();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void provide_reviews() {
        while (true) {
            System.out.println("0. Go back");
            System.out.println("Enter the item_id of the item you want to review: ");
            Integer item_id = get_customer_choice();
            if (item_id == 0) {
                return;
            }

            menu_item item = ct.get_menu().get(item_id);

            if (item != null) {
                if (menu_item.bought(this, item)) {
                    review new_review = create_review();
                    if (new_review == null) {
                        System.out.println("Invalid review. Please try again.");
                        return;
                    }
                    TreeMap<Integer, review> reviews = review_data_base.get_reviews_by_item_id(item_id);
                    if (reviews == null) {
                        reviews = new TreeMap<Integer, review>();
                        reviews.put(this.get_customer_id(), new_review);
                        review_data_base.get_review_db().put(item_id, reviews);
                        System.out.println("Review added successfully.");
                        return;
                    }
                    reviews.put(item_id, new_review);
                    System.out.println("Review added successfully.");
                } else {
                    System.out.println("You have not bought this item. Please try again.");
                }
            } else {
                System.out.println("Invalid item_id. Please try again.");
            }
        }
    }

    private review create_review() {
        review review = new review();
        Boolean rating_check = false, review_check = false;
        
        System.out.println("Give your rating for the item (1-5) (Zero to skip rating and provide review only) ! ");
        Integer rating = get_customer_choice();
        if (rating >= 1 && rating <= 5) {
            review.update_review(rating);
            rating_check = true;
        } else if (rating != 0) {
            System.out.println("Invalid rating. Please try again.");
        } 
        
        System.out.println("Give your review for the item ! (Zero to skip review and provide rating only)");
        String review_s = customer_sc.nextLine();
        if (!(review_s.contentEquals("0") || review_s.contentEquals("") || review_s.contentEquals(" ") || review_s.equalsIgnoreCase("\n"))) {
            review.update_review(review_s);
            review_check = true;
        } else if (!(review_s.contentEquals("0"))) {
            System.out.println("Invalid review. Please try again.");
            
        }
        if (rating_check || review_check) {
            return review;
        }
        return null;
    }

    private void view_reviews() {
        while (true) {
            System.out.println("0. Go back");
            System.out.println("Enter the item_id of the item you want to view reviews for: ");
            Integer item_id = get_customer_choice();
            if (item_id == 0) {
                return;
            }
            if (item_id > 0) {
                if (ct.get_menu().containsKey(item_id)) {
                    TreeMap<Integer, review> reviews = review_data_base.get_reviews_by_item_id(item_id);
                    if (reviews == null) {
                        System.out.println("No reviews for this item.");
                        return;
                    }
                    
                    for (review r : reviews.values()) {
                        System.out.println("Rating: " + r.get_rating());
                        System.out.println("Review: " + r.get_review());
                        System.out.println();
                    }
                    return;
                }
            }
            System.out.println("Invalid item_id. Please try again.");
        }
    }



    // Check Financials
    private void check_financials() {
        while (true) {
            System.out.println("Your current wallet balance is: " + wallet);
            print_financial_menu();
            Integer choice = get_customer_choice();
            Boolean continuation = perform_financial_action(choice);
            if (!continuation) {
                return;
            }
        }
    }

    private void print_financial_menu() {
        System.out.println("0. Go back");
        System.out.println("1. Add money to wallet");
        System.out.println("2. Print transaction history");
    }

    private Boolean perform_financial_action(Integer choice) {
        int i = 0;
        if (choice == i++) {
            return false;
        } else if (choice == i++) {
            // Add money to wallet
            add_money_to_wallet();
        } else if (choice == i++) {
            // Print transaction history
            print_transaction_history();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    private void add_money_to_wallet() {
        System.out.print("Enter the amount you want to add to your wallet: ");
        Double amount = customer_sc.nextDouble();
        customer_sc.nextLine();
        if (amount <= 0) {
            System.out.println("Invalid amount. Please try again.");
            return;
        }
        add_to_wallet(amount);
        update_transaction_history(amount, "Added money to wallet");
        System.out.println("Money added to wallet successfully.");
    }

    private void print_transaction_history() {
        if (transaction_history.isEmpty()) {
            System.out.println("You have no transaction history.");
            return;
        }
        System.out.println("Your transaction history is as follows:");
        for (Integer transaction_id : transaction_history.keySet()) {
            Pair<Double, String> transaction = transaction_history.get(transaction_id);
            System.out.println("Transaction ID: " + transaction_id + " | Amount: " + transaction.getFirst()
                    + " | Description: " + transaction.getSecond());
        }
    }



}
