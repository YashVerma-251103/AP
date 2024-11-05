# Byte Me!

## Project Description
"Byte Me!" is a CLI-based food ordering system designed specifically for a college canteen. It helps students browse the canteen menu, place orders, and track their delivery without leaving their hostel rooms. It also enables canteen staff to manage menu items and process orders efficiently while maintaining order histories.

## Author Information
- **Name**: Yash Verma
- **Email**: yash23610@iiitd.ac.in
- **GitHub Repository**: [GitHub Repository Link](https://github.com/YashVerma-251103/AP/tree/7a4c666d76d7c1c8281267aeee9eb7cc9a59c51f/Assignment3)


## Assumptions and Important Points to keep in mind.
### Assumptions
1. **Single Canteen Instance**: There is only one canteen instance managing all orders and menu items.
2. **VIP Customers**: VIP customers get priority in order processing and can become VIP by paying a specified amount.
3. **Order Statuses**: Orders can have statuses like Pending, Preparing, Out for delivery, Delivered, Denied, and Cancelled.
4. **Menu Availability**: Menu items have availability status and quantity in stock.
5. **Refunds**: Refunds are processed immediately after an order is denied or canceled.
6. **Order Modification**: Customers can modify their orders before checkout.
7. **Special Requests**: Customers can add special requests to their orders, which are handled by the canteen staff.

### Refund Handling
Refunds are processed in the following scenarios:
- **Order Denied**: If an order is denied, the total price of the order is refunded to the customer's wallet.
- **Order Cancelled**: If a customer cancels an order, the total price of the order is refunded to the customer's wallet.
- **Item Out of Stock**: If an item in the order is out of stock, the price of the item is refunded to the customer's wallet.

Refunds are added to the customer's wallet, and a message is added to the order's canteen messages indicating the refund amount.

### Order Denial and Missing Items
Orders are denied in the following scenarios:
- **Item Removed**: If an item in the order is removed from the menu, the order is denied, and the total price is refunded.
- **Special Request Not Met**: If a special request cannot be met, the order may be denied, and the total price is refunded.

If items are missing from an order:
- **Out of Stock**: If an item is out of stock, the quantity is updated, and the price of the missing quantity is refunded.
- **Limited Stock for VIP**: If a VIP customer orders an item with limited stock, the available quantity is added to the order, and the remaining amount is refunded.

### Corner Cases
1. **Empty Cart**: Customers cannot checkout if their cart is empty.
2. **Invalid Order ID**: If a customer tries to cancel or track an order with an invalid order ID, an error message is displayed.
3. **Insufficient Wallet Balance**: If a customer does not have enough balance in their wallet to place an order, the order is not processed.
4. **Duplicate Items**: If a customer adds the same item multiple times, the quantities are aggregated.


### Canteen Working

The canteen system allows for efficient management of orders and inventory. One of the key functionalities provided to the admin is the ability to process orders through the `process_order()` function.

#### How Admin Can Use `process_order()` Functionality

The `process_order()` function is designed to handle the entire lifecycle of an order, from receiving the order details to updating the inventory and notifying the customer. Here is a step-by-step explanation of how it works:

1. **Receive Order Details**: The function takes in the order details, which include the items ordered, quantities, and customer information.
2. **Validate Order**: It checks if the ordered items are available in the inventory in the required quantities.
3. **Update Inventory**: If the items are available, the function updates the inventory by deducting the ordered quantities.
4. **Generate Invoice**: An invoice is generated for the order, detailing the items, quantities, prices, and total amount.
5. **Notify Customer**: The customer is notified about the order status, either confirming the order or informing them of any issues (e.g., items out of stock).
6. **Complete Order**: The order is marked as complete, and the system is updated accordingly.

By using the `process_order()` function, the admin can ensure that orders are handled efficiently and accurately, helping to maintain smooth operations in the canteen.


## Classes and Their Descriptions

### 1. `byte_me`
- **Description**: The main entry point of the application.
- **Methods**:
  - `main(String[] args)`: Initializes the application, seeds data, and provides the main menu for admin and customer login.

### 2. `admin`
- **Attributes**:
  - `admin_sc`: Scanner for admin input.
  - `ct`: Instance of `canteen`.
  - `admin_instance`: Singleton instance of `admin`.
  - `name`: Admin's name.
  - `password`: Admin's password.
- **Methods**:
  - `get_instance()`: Returns the singleton instance of `admin`.
  - `login()`: Handles admin login.
  - `admin_interface()`: Provides the admin interface with various functionalities.
  - `print_admin_greetings()`, `print_admin_menu()`, `get_choice()`, `perform_admin_action(Integer choice)`: Utility methods for admin interface.
  - `item_management()`, `order_management()`, `report_generation()`, `end_of_day()`: Main functionalities for admin.
  - `add_new_item()`, `update_item()`, `view_all_items()`, `view_all_orders()`, `view_pending_orders()`, `view_delivered_orders()`, `view_denied_orders()`, `update_order_status()`, `process_refunds()`, `handle_special_requests()`, `generate_daily_sales_report()`: Specific functionalities for item and order management.

### 3. `canteen`
- **Attributes**:
  - `earnings`, `total_refund`: Financial attributes.
  - `order_history`, `priority_orders`, `current_orders`: Order management attributes.
- **Methods**:
  - `get_instance()`: Returns the singleton instance of `canteen`.
  - `update_refund(Double amount)`, `reset_refund(Double amount)`, `remove_item_refunds(menu_item item, order o)`, `update_earnings(Double amount)`, `process_refund(order o, Double amount)`, `process_refund(order o)`, `update_transaction_history(order o, Double amount, String message)`: Financial management methods.
  - `get_menu()`, `view_full_menu()`, `view_menu()`, `view_menu_by_price(Boolean ascending)`, `view_menu_by_category(Integer category)`, `add_item(menu_item item)`: Menu management methods.
  - `view_all_orders_EOD()`, `view_order_history(Integer status)`, `view_order(Integer order_id)`, `view_denied_orders()`, `view_cancelled_orders()`, `add_order(order o)`, `order_finished(order o)`, `order_denied(order o)`, `order_cancelled(order o)`, `update_order_status(order o)`, `process_order()`, `process_order(order o)`, `get_next_order()`, `end_of_day()`, `end_shift()`, `generate_report()`, `update_order_history_ledger()`, `data_reset()`: Order management methods.

### 4. `customer`
- **Attributes**:
  - `customer_sc`: Scanner for customer input.
  - `ct`: Instance of `canteen`.
  - `vip_cost`, `customer_id_counter`, `customer_db`: Static attributes for customer management.
  - `customer_id`, `vip`, `customer_name`, `customer_address`, `wallet`, `transaction_id_counter`, `transaction_history`, `cart`, `order_history`, `current_orders`: Customer attributes.
- **Methods**:
  - `create_customer(String name, String address, Double wallet, Boolean vip)`: Creates a new customer.
  - `customer_login()`: Handles customer login.
  - `customer_interface()`: Provides the customer interface with various functionalities.
  - `print_customer_greetings()`, `print_customer_menu(Boolean is_vip)`, `get_customer_choice()`, `perform_customer_action(Integer choice, Boolean is_vip)`: Utility methods for customer interface.
  - `become_vip()`, `browse_menu()`, `cart_operations()`, `order_tracking()`, `item_reviews()`, `check_financials()`: Main functionalities for customer.
  - `view_all_items()`, `search_functionality()`, `filter_by_category()`, `sort_by_price()`, `create_order()`, `add_item_to_order(order new_order)`, `modify_order()`, `remove_item_from_order(order o)`, `checkout()`, `view_order_status()`, `cancel_order()`, `order_history()`, `provide_reviews()`, `view_reviews()`, `update_transaction_history(Double amount, String description)`: Specific functionalities for menu, cart, order, and review management.

### 5. `order`
- **Attributes**:
  - `order_id`, `order_status`, `ordered_items`, `customer`, `canteen_messages`, `total_price`: Order attributes.
- **Methods**:
  - `create_order(customer ordering_customer)`: Creates a new order.
  - `get_order_id()`, `get_order_status()`, `get_order_status_string()`, `get_canteen_messages()`, `get_total_price()`: Getter methods.
  - `set_customer(customer customer)`, `set_order_status(Integer order_status)`, `update_total_price()`: Setter methods.
  - `add_item(menu_item item)`, `add_item(menu_item item, Integer quantity)`, `add_item(menu_item item, Integer quantity, String special_request)`, `remove_item(menu_item item)`, `remove_item(menu_item item, Integer quantity)`, `update_item_quantity(menu_item item, Integer quantity)`, `add_special_request(menu_item item, String request)`, `remove_special_request(menu_item item)`: Item management methods.
  - `view_order()`, `view_order_summary()`, `cancel_order()`, `place_order()`, `reorder(Integer order_id)`, `update_order_status(Integer status)`, `deny_order()`: Order management methods.

### 6. `menu_item`
- **Attributes**:
  - `item_id`, `name`, `price`, `description`, `category`, `available`, `quantity_in_stock`: Item attributes.
- **Methods**:
  - `get_id()`, `get_name()`, `get_price()`, `get_description()`, `get_category()`, `get_available()`, `get_quantity_in_stock()`: Getter methods.
  - `set_name(String name)`, `set_price(Double price)`, `set_description(String description)`, `set_category(Integer category)`, `set_available(Boolean available)`, `set_quantity_in_stock(Integer quantity)`: Setter methods.
  - `bought(customer c, menu_item item)`: Checks if the item was bought by the customer.

### 7. `review`
- **Attributes**:
  - `rating`, `review_s`: Review attributes.
- **Methods**:
  - `give_review(String review_s)`, `give_review(Integer rating)`, `give_review(Integer rating, String review_s)`: Methods to create a review.
  - `update_review(Integer rating, String review_s)`, `update_review(Integer rating)`, `update_review(String review_s)`: Methods to update a review.
  - `get_rating()`, `get_review()`: Getter methods.

### 8. `review_data_base`
- **Attributes**:
  - `review_db`: Database of reviews.
- **Methods**:
  - `get_review_db()`, `get_reviews_by_item_id(int item_id)`: Methods to get reviews.

### 9. `data_seeder`
- **Methods**:
  - `seed_data()`, `seed_canteen()`, `seed_customers()`, `seed_admins()`, `seed_items()`, `dummy_create_order(customer c)`, `dummy_checkout_order(customer c, order o)`: Methods to seed data for testing.

### 10. `Pair`
- **Attributes**:
  - `first`, `second`: Pair attributes.
- **Methods**:
  - `getFirst()`, `getSecond()`: Getter methods.
  - `setFirst(F first)`, `setSecond(S second)`: Setter methods.

## Dataflow and Workflow
1. **Admin Workflow**:
   - Admin logs in using `admin.login()`.
   - Admin can manage items, orders, generate reports, and end the day using the admin interface.
   - Admin can add, update, and view items, manage orders, process refunds, handle special requests, and generate daily sales reports.

2. **Customer Workflow**:
   - Customer logs in using `customer.customer_login()`.
   - Customer can browse the menu, manage their cart, track orders, provide and view reviews, and check financials using the customer interface.
   - Customer can add items to the cart, place orders, modify orders, checkout, track order status, cancel orders, and view order history.

3. **Order Management**:
   - Orders are created using `order.create_order(customer)`.
   - Orders can be placed, modified, canceled, and tracked by customers.
   - Admin and canteen staff can view, update, and manage orders.

4. **Review Management**:
   - Customers can provide and view reviews for items.
   - Reviews are stored in `review_data_base`.

## OOP Relationships
- **Inheritance**: Not explicitly used in the provided code.
- **Composition**: Classes like `customer`, `order`, and `canteen` use composition to include other objects like `menu_item`, `review`, and `Pair`.
- **Singleton**: `admin` and `canteen` classes use the singleton pattern to ensure only one instance exists.
- **Encapsulation**: Attributes are private, and public getter and setter methods are used to access them.
- **Polymorphism**: Implemented using method overloading and method overriding.

