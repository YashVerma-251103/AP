package com.assignment_4.junit;

import static org.junit.jupiter.api.Assertions.*;

import com.Start.Classes.canteen;
import com.Start.Classes.customer;
import com.Start.Classes.order;
import com.Start.Planning.data_seeder;

import org.junit.jupiter.api.Test;

import com.Start.Classes.admin;


public class junit_test {

    public static void main(String[] args) {
        data_seeder.seed_data();
        junit_test test = new junit_test();
        System.out.println("\n\n\n\n");
        System.out.println("Running tests ...");
        test.test_login_fail();
        test.test_login_success();
        test.item_out_of_stock_test();
        test.item_in_stock_test();
    }


    @Test
    public void test_login_fail() {
        String test_admin_name = "test_admin";
        String test_admin_password = "test_admin_password";
        boolean admin_test = admin.test_login(test_admin_name, test_admin_password);
        assertFalse(admin_test, "Admin login test failed !");
    }

    @Test
    public void test_login_success() {
        String test_admin_name = "admin";
        String test_admin_password = "a123";
        boolean admin_test = admin.test_login(test_admin_name, test_admin_password);
        assertTrue(admin_test, "Admin login test passed !");
    }

    @Test
    public void item_out_of_stock_test(){
        customer test_customer = customer.customer_db.get(6);
        order o = order.create_order(test_customer);
        boolean test = test_customer.add_item_test(o, canteen.get_instance().get_menu().get(1), 200);
        assertFalse(test, "Item out of stock test failed !");

    }

    @Test
    public void item_in_stock_test(){
        customer test_customer = customer.customer_db.get(7);
        order o = order.create_order(test_customer);
        boolean test = test_customer.add_item_test(o, canteen.get_instance().get_menu().get(2), 1);
        assertTrue(test, "Item out of stock test passed !");

    }
}

