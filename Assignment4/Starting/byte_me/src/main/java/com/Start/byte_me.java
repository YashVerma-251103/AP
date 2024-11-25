package com.Start;

import com.Start.Planning.*;
import com.Start.Structures.review;
import com.Start.Structures.review_data_base;
import com.Start.save_files.JsonUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

import com.Start.Classes.*;

public class byte_me {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Seeding data...");
        data_seeder.seed_data();
        System.out.println("Data seeded !\n\n\n\n\n");

        while (true) {
            System.out.println("Welcome to Byte Me !");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                admin.login();
                // admin a = admin.get_instance();
                // a.admin_interface();

            } else if (choice == 2) {
                customer.customer_login();
            } else if (choice == 3) {
                System.out.println("Thank you for using Canteen Management System !");
                save_data();
                System.out.println("Data saved successfully !");
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice ! Please try again !");
            }
        }
    }

    public static void load_data() {
        String baseDir = "Starting/byte_me/src/main/java/com/Start/save_files/";
    
        // Load menu items
        String menuFile = baseDir + "menu_items.json";
        menu_item.get_category_menu().clear();
        menu_item.get_category_menu().putAll(
            JsonUtil.loadFromJson(menuFile, TreeMap.class) // Use Jackson's direct class type handling
        );
    
        // Load customers
        String customerFile = baseDir + "customers.json";
        customer.customer_db.clear();
        customer.customer_db.putAll(
            JsonUtil.loadFromJson(customerFile, TreeMap.class) // Use Jackson's direct class type handling
        );
    
        // Load current orders
        String currentOrdersFile = baseDir + "current_orders.json";
        canteen.get_instance().get_current_orders().clear();
        canteen.get_instance().get_current_orders().putAll(
            JsonUtil.loadFromJson(currentOrdersFile, TreeMap.class) // Use Jackson's direct class type handling
        );
    
        // Load priority orders
        String priorityOrdersFile = baseDir + "priority_orders.json";
        canteen.get_instance().get_priority_orders().clear();
        canteen.get_instance().get_priority_orders().putAll(
            JsonUtil.loadFromJson(priorityOrdersFile, TreeMap.class) // Use Jackson's direct class type handling
        );
    
        // Load order history
        String orderHistoryFile = baseDir + "order_history.json";
        canteen.get_instance().get_order_history().clear();
        canteen.get_instance().get_order_history().putAll(
            JsonUtil.loadFromJson(orderHistoryFile, TreeMap.class) // Use Jackson's direct class type handling
        );
    
        // Load reviews
        String reviewsFile = baseDir + "reviews.json";
        review_data_base.get_review_db().clear();
        review_data_base.get_review_db().putAll(
            JsonUtil.loadFromJson(reviewsFile, TreeMap.class) // Use Jackson's direct class type handling
        );
    
        System.out.println("All data loaded successfully!");
    }
    
    

    private static void save_data() {
        String baseDir = "Starting/byte_me/src/main/java/com/Start/save_files/";
        File saveFilesDir = new File(baseDir);

        if (!saveFilesDir.exists()) {
            System.out.println("Creating directory: " + saveFilesDir.getAbsolutePath());
            saveFilesDir.mkdirs();
        }

        // Save menu items
        String menuFile = baseDir + "menu_items.json";
        JsonUtil.saveToJson(menuFile, menu_item.get_category_menu());

        // Save customers
        String customerFile = baseDir + "customers.json";
        JsonUtil.saveToJson(customerFile, customer.customer_db);

        // Save current orders
        String currentOrdersFile = baseDir + "current_orders.json";
        JsonUtil.saveToJson(currentOrdersFile, canteen.get_instance().get_current_orders());

        // Save priority orders
        String priorityOrdersFile = baseDir + "priority_orders.json";
        JsonUtil.saveToJson(priorityOrdersFile, canteen.get_instance().get_priority_orders());

        // Save order history
        String orderHistoryFile = baseDir + "order_history.json";
        JsonUtil.saveToJson(orderHistoryFile, canteen.get_instance().get_order_history());

        // Save reviews
        String reviewsFile = baseDir + "reviews.json";
        JsonUtil.saveToJson(reviewsFile, review_data_base.get_review_db());

        System.out.println("All data saved successfully!");
    }

}

// private static void save_data() {
// // Print the current working directory
// System.out.println("Current working directory: " + new
// File(".").getAbsolutePath());
//
// // Ensure directories exist
// File savedFilesDir = new
// File("Starting/byte_me/src/main/java/com/Start/save_files/");
// File saveFilesDir = new
// File("Starting/byte_me/src/main/java/com/Start/save_files/");
//
// if (!savedFilesDir.exists()) {
// System.out.println("Creating directory: " + savedFilesDir.getAbsolutePath());
// savedFilesDir.mkdirs();
// } else {
// System.out.println("Directory exists: " + savedFilesDir.getAbsolutePath());
// }
//
// if (!saveFilesDir.exists()) {
// System.out.println("Creating directory: " + saveFilesDir.getAbsolutePath());
// saveFilesDir.mkdirs();
// } else {
// System.out.println("Directory exists: " + saveFilesDir.getAbsolutePath());
// }
//
// // Retrieve and print menu items
// TreeMap<Integer, TreeMap<Integer, menu_item>> categoryMenu =
// menu_item.get_category_menu();
// System.out.println("Menu Items: " + categoryMenu);
//
// // Save menu items
// String menuItemsFilePath = new
// File("Starting/byte_me/src/main/java/com/Start/save_files/menu_items.json").getAbsolutePath();
// System.out.println("Saving menu items to: " + menuItemsFilePath);
// json_util.save_to_json(menuItemsFilePath, categoryMenu);
//
// // Verify menu items file content
// try (FileReader reader = new FileReader(menuItemsFilePath)) {
// int ch;
// System.out.print("Menu Items File Content: ");
// while ((ch = reader.read()) != -1) {
// System.out.print((char) ch);
// }
// System.out.println();
// } catch (IOException e) {
// e.printStackTrace();
// }
//
// // Retrieve and print reviews
// TreeMap<Integer, TreeMap<Integer, review>> reviewDb =
// review_data_base.get_review_db();
// System.out.println("Reviews: " + reviewDb);
//
// // Save reviews
// String reviewsFilePath = new
// File("Starting/byte_me/src/main/java/com/Start/save_files/reviews.json").getAbsolutePath();
// System.out.println("Saving reviews to: " + reviewsFilePath);
// json_util.save_to_json(reviewsFilePath, reviewDb);
//
// // Verify reviews file content
// try (FileReader reader = new FileReader(reviewsFilePath)) {
// int ch;
// System.out.print("Reviews File Content: ");
// while ((ch = reader.read()) != -1) {
// System.out.print((char) ch);
// }
// System.out.println();
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
// }
//
//
