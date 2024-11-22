package com.Start;

import com.Start.Planning.*;
import com.Start.save_files.json_util;

import java.io.File;
import java.util.Scanner;

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

            ;
        }
    }

    private static void save_data() {

        new File("./com/Start/save_files/canteen").mkdirs();
        new File("./com/Start/save_files/menu").mkdirs();
        new File("./com/Start/save_files/orders").mkdirs();
        new File("./com/Start/save_files/customers").mkdirs();
        
        
        json_util.save_to_json("./com/Start/save_files/canteen/canteen.json", canteen.get_instance());
        json_util.save_to_json("./com/Start/save_files/menu/menu_items.json", menu_item.get_category_menu());
        json_util.save_to_json("./com/Start/save_files/orders/orders.json", canteen.get_instance().get_current_orders());
        json_util.save_to_json("./com/Start/save_files/orders/orders.json", canteen.get_instance().get_priority_orders());
        json_util.save_to_json("./com/Start/save_files/customers/customers.json", customer.customer_db);
        
    }
}