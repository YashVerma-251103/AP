/* Objectives: 
 * As a part of this assignment, you have to implement a CLI-based food ordering system called ”Byte Me!”, designed specifically for our college canteen. Your task is to develop a complete command-line interface system that will:
    • Help students browse the canteen menu, place orders, and track their delivery without leaving their comfy hostel rooms.
    • Enable canteen staff to manage menu items and process orders efficiently.
    • Maintain order histories (so you can remember what you ate during that 3 AM coding session).
    • Use collections to organise and sort data to make this process efficient!
 */
/* Datas:
    • Menu of Food Items
    • List of current orders to be processed
    • Order History for each individual customer
 */
/* Features:
    • Admin:
        - Add a new items
        - Update an items
        - Remove an items
        - Order management
        - Report Generation
    • Customer:
        - Types : VIP Customers, Regular Customers
        - Browse the menu
        - Cart Operations
        - Order Tracking
        - Item Reviews
 */
/* Userflow: 
 * Admin:
        • Add a new items : 
            Details => price, category, and availability (basic may be more)
        • Update an items : 
            Details => price, category, and availability (basic may be more)
        • Remove an items : 
            Key Point => When an item is removed, the status of all pending orders containing that item will be updated to 'denied'.
        • Order management :
            - view all/pending/delivered/denied orders : 
                Key Point => Orders should be handled in the order they were received, ensuring fair processing.
            - update order status : 
                Details => Admin can mark orders as completed or update them at different stages (e.g., preparing, out for delivery).
            - process refunds :
                Details => Admin can process refunds for denied/canceled orders or orders with some issues.
            - handle special requests :
                Details => Admin can handle special requests from customers (e.g., extra cheese, no onions).
            - Key Point : Order Priority :
                Details => Orders placed by VIP customers should be given priority over regular orders, ensuring VIP orders are processed first when multiple orders are pending.
        • Report Generation :
            -Daily Sales Report :
                Details => Admin can generate a report of all sales and orders processed during the day, which includes details like total sales, most popular items, and total orders.
 * Customer:
        • Types : (Key Point)
            - VIP Customers
                - Details => Orders should be given priority in the order processing flow. You can become a VIP by paying a specified amount.
            - Regular Customers
                - Details => orders will be processed in the order they are received but will only be handled after all VIP orders have been processed.
            
        • Browse the menu :
            - View all items :
                Details => Customers can view the complete menu of food items along with their prices and availability.
            - Search functionality :
                Details => Customers can search for specific items using keywords or name.
            - Filter by category :
                Details => Customers can filter items by category (e.g., snacks, beverages, desserts).
            - Sort by price :
                Details => Customers can sort items by price in ascending or descending order.
        • Cart Operations :
            - Add an item :
                Details => Customers can add multiple items to their cart, specifying the quantity of each item.
            - Special Requests :
                Details => Customers can add special requests to their order (e.g., extra cheese, no onions).
            - Modify item quantity :
                Details => Customers can modify the quantity of items in their cart before checking out.
            - Remove an item :
                Details => Customers can remove items from their cart before checking out.
            - View total :
                Details => Customers can view the total amount (price) of their order, including taxes and delivery charges.
            - Checkout :
                Details => Customers can complete their order by providing payment and delivery details.
        • Order Tracking :
            - View order status :
                Details => Customers can track the status of their orders in real time, from 'order received' to 'delivered', or 'denied'.
            - Cancel order :
                Details => Customers can cancel their orders before they are prepared or processed.
            - Order history :
                Details => Customers can view their order history, including all previous orders placed, items ordered, and the total amount spent.
        • Item Reviews
            - Provide reviews :
                Details => Customers can provide feedback and ratings for items they have ordered.
            - View reviews :
                Details => Customers can view reviews and ratings provided by other customers for each item.
 */