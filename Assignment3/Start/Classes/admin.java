package Start.Classes;

import java.util.Scanner;

public class admin {
    private static final Scanner admin_sc = new Scanner(System.in);
    private static canteen ct = canteen.get_instance();

    // only one admin will the there.
    private static admin admin_instance = null;

    // Attributes
    private String name;
    private String password;


    // Constructor
    public static admin get_instance() {
        if (admin_instance == null) {
            admin_instance = new admin();
        }
        return admin_instance;
    }

    // Getters
    public String get_name() {
        return name;
    }
    public String get_password() {
        return password;
    }
    public void set_name(String name) {
        this.name = name;
    }
    public void set_password(String password) {
        this.password = password;
    }


    // login
    public static void login() {
        while (true) {
            print_login_greetings();
            String name = get_name_input();
            String password = get_password_input();
            Boolean success = login(name, password);
            if (success) {
                System.out.println("Login successful!");
                admin_instance.admin_interface();
                break;
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    }
    private static void print_login_greetings() {
        System.out.println("Welcome Admin!");
    }
    private static String get_name_input() {
        System.out.print("Enter your name: ");
        String name = admin_sc.nextLine();
        return name;
    }
    private static String get_password_input() {
        System.out.print("Enter your password: ");
        String password = admin_sc.nextLine();
        return password;
    }
    private static Boolean login(String name, String password) {
        if (name.equals(admin_instance.get_name()) && password.equals(admin_instance.get_password())) {
            return true;
        }
        return false;
    }


    // Functionalities:
    public void admin_interface() {
        // admin functionalities:+
        // take canteen to next step
        // add a new item to the menu
        // update an existing item
        // remove an item from the menu
        // order management
        // report generation

        // admin menu
        print_admin_greetings();
        while (true) {
            print_admin_menu();
            Integer choice = get_choice();
            Boolean continuation = perform_admin_action(choice);
            if (!continuation) {
                break;
            }
        }
    }
    private void print_admin_greetings() {
        System.out.println("Welcome Admin (" + get_name() + ") !");
    }
    private void print_admin_menu() {
        System.out.println("Choose an action:");
        System.out.println("-1 to take canteen to next step!");
        System.out.println("0. Exit");
        System.out.println("1. Item management");
        System.out.println("2. Order management");
        System.out.println("3. Report generation");
        System.out.println("4. End of day");
        System.out.print("Enter your choice: ");
    }
    private Integer get_choice() {
        Integer choice = admin_sc.nextInt();
        return choice;
    }
    private Boolean perform_admin_action(Integer choice) {
        switch (choice) {
            case -1:
                ct.process_order();
                break;
            case 0:
                return false;
            case 1:
                item_management();
                break;
            case 2:
                order_management();
                break;
            case 3:
                report_generation();
                break;
            case 4:
                end_of_day();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    // item management
    private void item_management() {
        // admin functionalities:
        // add a new item to the menu
        // update an existing item
        // remove an item from the menu
        while (true) {
            print_item_management_menu();
            Integer choice = get_choice();
            Boolean continuation = perform_item_management_action(choice);
            if (!continuation) {
                break;
            }
        }
    }

    private void print_item_management_menu() {
        System.out.println("Item management");
        System.out.println("Choose an action:");
        System.out.println("0. Go back");
        System.out.println("1. Add a new item");
        System.out.println("2. Update an existing item");
        System.out.println("3. Remove an item");
        System.out.println("4. View all items");
        System.out.print("Enter your choice: ");
    }
    private Boolean perform_item_management_action(Integer choice) {
        switch (choice) {
            case 0:
                return false;
            case 1:
                add_new_item();
                break;
            case 2:
                update_item();
                break;
            case 3:
                remove_item();
                break;
            case 4:
                view_all_items();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    // add item
    private void add_new_item() {
        System.out.println("Enter item details:");
        String name = get_item_name();
        Double price = get_item_price();
        String description = get_item_description();
        print_current_categories();
        Integer category = get_item_category();
        add_item_to_all_menus(name, price, description, category);
        System.out.println("Item added successfully!");
    }
    private String get_item_name() {
        System.out.print("Enter item name: ");
        String name = admin_sc.nextLine();
        if (name.length() == 0) {
            System.out.println("Invalid name. Please try again.");
            return get_item_name();
        }
        return name;
    }
    private Double get_item_price() {
        System.out.print("Enter item price: ");
        Double price = admin_sc.nextDouble();
        admin_sc.nextLine();
        if (price <= 0) {
            System.out.println("Invalid price. Please try again.");
            return get_item_price();
        }
        return price;
    }
    private String get_item_description() {
        System.out.print("Enter item description: ");
        String description = admin_sc.nextLine();
        if (description.length() == 0) {
            System.out.println("Invalid description. Please try again.");
            return get_item_description();
        }
        return description;
    }
    private Integer get_item_category() {
        System.out.print("Enter item category id : ");
        Integer category = admin_sc.nextInt();
        if (category-1 < 0 || category-1 >= menu_item.get_categories().length) {
            System.out.println("Invalid category. Please try again.");
            return get_item_category();
        }
        return (category-1);
    }
    private void print_current_categories() {
        String[] categories = menu_item.get_categories();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i+1) + ". " + categories[i]);
        }
    }
    private void add_item_to_all_menus(String item_name, Double item_price, String item_description, Integer item_category){
        menu_item new_item = menu_item.create_item(item_name, item_price, item_description, item_category);
        ct.add_item(new_item);
    }


    // update item
    private void update_item() {       
        Integer item_id = get_item_id("Enter item id to update: ");
        if (check_item_exitence_in_menu(item_id)) {
            menu_item item = get_item(item_id);
            while (true) {
                print_item_update_menu();
                Integer choice = get_choice();
                Boolean continuation = perform_item_update_action(choice, item);
                if (!continuation) {
                    break;
                }
            }
            
            System.out.println("Item updated successfully!");
        } else {
            System.out.println("Item not found. Please try again.");
        }
    }

    private void print_item_update_menu() {
        System.out.println("Choose an action:");
        System.out.println("0. Go back");
        System.out.println("1. Update name");
        System.out.println("2. Update price");
        System.out.println("3. Update description");
        System.out.println("4. Update category");
        System.out.println("5. Update availability");
        System.err.println("6. Update quantity in stock");
        // System.err.println("5. Update quantity in stock");
        System.out.print("Enter your choice: ");
    }
    private Integer get_item_id(String message) {
        System.out.print(message);
        Integer id = admin_sc.nextInt();
        admin_sc.nextLine();
        return id;
    }
    private Boolean check_item_exitence_in_menu(Integer item_id) {
        return ct.get_menu().containsKey(item_id);
    }
    private menu_item get_item(Integer item_id) {
        return ct.get_menu().get(item_id);
    }
    private Boolean perform_item_update_action(Integer choice, menu_item item) {
        switch (choice) {
            case 0:
                return false;
            case 1:
                update_item_name(item);
                break;
            case 2:
                update_item_price(item);
                break;
            case 3:
                update_item_description(item);
                break;
            case 4:
                update_item_category(item);
                break;
            case 5:
                update_item_availability(item);
                break;
            case 6:
            // case 5:
                update_item_quantity_in_stock(item);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }
    private void update_item_name(menu_item item) {
        String name = get_item_name();
        item.set_name(name);
    }
    private void update_item_price(menu_item item) {
        Double price = get_item_price();
        item.set_price(price);
    }
    private void update_item_description(menu_item item) {
        String description = get_item_description();
        item.set_description(description);
    }
    private void update_item_category(menu_item item) {
        print_current_categories();
        Integer category = get_item_category();
        item.set_category(category);
    }
    private void update_item_availability(menu_item item) {
        Boolean available = get_item_availability();
        item.set_available(available);
    }
    private Boolean get_item_availability() {
        System.out.print("Enter item availability (true/false): ");
        Boolean available = admin_sc.nextBoolean();
        admin_sc.nextLine();
        return available;
    }
    private void update_item_quantity_in_stock(menu_item item) {
        Integer quantity = get_item_quantity_in_stock();
        item.set_quantity(quantity);
    }
    private Integer get_item_quantity_in_stock() {
        System.out.print("Enter item quantity in stock: ");
        Integer quantity = admin_sc.nextInt();
        admin_sc.nextLine();
        return quantity;
    }

    // remove item
    private void remove_item() {
        Integer item_id = get_item_id("Enter item id to remove: ");
        if (check_item_exitence_in_menu(item_id)) {
            menu_item item = get_item(item_id);
            Boolean confirmation = get_item_removal_confirmation(item);
            if (confirmation) {
                remove_item(item);
                System.out.println("Item removed successfully!");
            } else {
                System.out.println("Item removal cancelled.");
                
            }
        } else {
            System.out.println("Item not found. Please try again.");
        }
    }
    private Boolean get_item_removal_confirmation(menu_item item) {
        System.out.println("Are you sure you want to remove " + item.get_name() + " from the menu ?");
        System.out.println("This action will update the status of all pending orders containing this item to 'denied'.");
        return get_confirmation();
    }
    private Boolean get_confirmation() {
        System.out.print("Enter 'true' to confirm or 'false' to cancel: ");
        Boolean confirmation = admin_sc.nextBoolean();
        admin_sc.nextLine();
        return confirmation;
    }
    private void remove_item(menu_item item) {

        remove_item_from_category_menu(item);
        deny_orders(item);
        
        remove_item_from_canteen_menu(item);
    }
    private void remove_item_from_category_menu(menu_item item) {
        Integer category = item.get_category();
        Integer id = item.get_id();
        menu_item.get_category_menu().get(category).remove(id);
        // menu_item.get_ca
    }
    private void deny_orders(menu_item item) {
        deny_priority_orders(item);
        deny_regular_orders(item);
    }
    private void deny_priority_orders(menu_item item) {
        for (order o : ct.get_priority_orders().values()) {
            if (o.get_ordered_items().containsKey(item)) {
                o.deny_order();
            }
        }   
    }
    private void deny_regular_orders(menu_item item) {
        for (order o : ct.get_current_orders().values()) {
            if (o.get_ordered_items().containsKey(item)) {
                o.deny_order();
            }
        }
    }
    private void remove_item_from_canteen_menu(menu_item item) {
        // Integer id = item.get_id();
        // ct.get_menu().remove(id);
        ct.remove_item(item);
    }

    // view all items
    private void view_all_items() {
        ct.view_full_menu();
    }



    // order management
    private void order_management() {
        // order management
        // view all/pending/delivered/denied orders
        // update order status
        // process refunds
        // handle special requests
        while (true) {
            print_order_management_menu();
            Integer choice = get_choice();
            Boolean continuation = perform_order_management_action(choice);
            if (!continuation) {
                break;
            }
        }
    }
    private void print_order_management_menu() {
        System.out.println("Order management");
        System.out.println("Choose an action:");
        System.out.println("0. Go back");
        System.out.println("1. View all orders of today");
        System.out.println("2. View pending orders");
        System.out.println("3. View delivered orders");
        System.out.println("4. View denied orders");
        System.out.println("5. Update order status");
        System.out.println("6. Process refunds");
        System.out.println("7. Handle special requests");
        System.out.print("Enter your choice: ");
    }
    private Boolean perform_order_management_action(Integer choice) {
        switch (choice) {
            case 0:
                return false;
            case 1:
                view_all_orders();
                break;
            case 2:
                view_pending_orders();
                break;
            case 3:
                view_delivered_orders();
                break;
            case 4:
                view_denied_orders();
                break;
            case 5:
                update_order_status();
                break;
            case 6:
                process_refunds();
                break;
            case 7:
                handle_special_requests();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }
    private void view_all_orders() {
        ct.view_all_orders_EOD();    
    }
    private void view_pending_orders() {
        ct.view_pending_orders();
    }
    private void view_delivered_orders() {
        ct.view_delivered_orders();
    }
    private void view_denied_orders() {
        ct.view_denied_orders();
    }
    private void update_order_status() {
        System.out.println("order status updated as we process orders. or they are denied only when the item is removed from the menu");
    }
    private void process_refunds() {
        System.out.println("Refunds are processed only when the order is denied or canceled or there is some issue with the order\neach refund is processed immediately after the order is processed.");
    }
    private void handle_special_requests() {
        System.out.println("Special requests are handled by the canteen staff while preparing the order.\n(A prompt is shown to the staff member to handle the special request.)");
    }

    // report generation
    private void report_generation() {
        // report generation
        // daily sales report
        while (true) {
            print_report_generation_menu();
            Integer choice = get_choice();
            Boolean continuation = perform_report_generation_action(choice);
            if (!continuation) {
                break;
            }
        }
    }
    private void print_report_generation_menu() {
        System.out.println("Report generation");
        System.out.println("Choose an action:");
        System.out.println("0. Go back");
        System.out.println("1. Generate daily sales report");
        System.out.print("Enter your choice: ");
    }
    private Boolean perform_report_generation_action(Integer choice) {
        switch (choice) {
            case 0:
                return false;
            case 1:
                generate_daily_sales_report();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }
    private void generate_daily_sales_report() {
        ct.generate_report();
    }    

    // end of day
    private void end_of_day() {
        ct.end_of_day();
        customer.end_of_day();
    }
}
