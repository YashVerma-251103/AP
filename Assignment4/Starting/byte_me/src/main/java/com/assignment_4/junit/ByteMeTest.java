package com.assignment_4.junit;

import static org.junit.jupiter.api.Assertions.*;

import com.Start.Classes.admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.Start.Classes.customer;
import com.Start.Classes.menu_item;

public class ByteMeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testOrderOutOfStockItem() {

//        print("out of ");

        // Assuming there's a method to set item stock and place an order
//        menu_item item = new menu_item(1, "Test Item", 10.0, "Test Description", 1, 0); // 0 stock
//        customer cust = new customer();
//        cust.add_to_cart(item, 1); // Try to add out-of-stock item to cart
//        cust.checkout(); // Attempt to checkout

//        String expectedOutput = "Item Test Item is out of stock.";
//        assertTrue(outContent.toString().contains(expectedOutput));
    }

    @Test
    public void testInvalidLoginAttempts() {
//        admin adminInstance = new admin();
//        boolean loginResult = adminInstance.login("wrongUsername", "wrongPassword");

//        assertFalse(loginResult);
//        String expectedOutput = "Invalid username or password.";
//        assertTrue(outContent.toString().contains(expectedOutput));
    }
}