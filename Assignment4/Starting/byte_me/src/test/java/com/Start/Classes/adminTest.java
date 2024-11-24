package com.Start.Classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class adminTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void success_login() {
        admin a = admin.get_instance();
        boolean test = a.test_login("admin", "a123");
        assertTrue(test, "Admin login test passed !");
    }

    @Test
    void failed_login() {
        admin a = admin.get_instance();
        boolean test = a.test_login("test_username", "test_password");
        assertFalse(test, "Admin login test failed !");
    }

//    @Test
//    void test_login() {
//    }
}