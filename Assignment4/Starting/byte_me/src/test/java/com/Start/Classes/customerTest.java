package com.Start.Classes;

import com.Start.Planning.data_seeder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class customerTest {

    @BeforeEach
    void setUp() {
//        customer.create_customer("test_customer", "test_customer_password", 800.0, true);
//        customer.create_customer("test_customer2", "test_customer_password2", 800.0, false);

        data_seeder.seed_data();
    }

    @AfterEach
    void tearDown() {
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