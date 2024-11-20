package com.Start;

import com.Start.Planning.*;
// import Start.Structures.*;

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
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice ! Please try again !");
            }
        }
    }
}