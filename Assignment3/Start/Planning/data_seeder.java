package Start.Planning;

import Start.Structures.*;
import Start.Classes.*;
// import Start.byte_me;

public class data_seeder {
    public static void seed_data(){
        seed_canteen();
        seed_customers();
        seed_admins();
        seed_items();
    }
    private static void seed_canteen(){
        // canteen ct = canteen.get_instance();
        canteen.get_instance();
        System.out.println("Canteen seeded !");
    }
    private static void seed_customers(){
        for (int i = 1; i <= 5; i++) {
            // customer c = customer.create_customer(("customer" + i), ("customer" + i + "" + (i+1) + "" + (i+2)), 800.0, ((i%2==0)?true:false));
            customer.create_customer(("customer" + i), ("customer" + i + "" + (i+1) + "" + (i+2)), 800.0, ((i%2==0)?true:false));
        }
        System.out.println("Customers seeded !");
    }
    private static void seed_admins(){
        admin a = admin.get_instance();
        a.set_name("admin");
        a.set_password("a123");
        System.out.println("Admins seeded !");
    }
    private static void seed_items(){
        // category = 0 -> Snacks
        menu_item samosa = menu_item.create_item("Samosa", 10.0, "A samosa is a fried or baked pastry with a savoury filling, such as spiced potatoes, onions, peas, cheese, beef and other meats, or lentils.", 0);
        samosa.set_quantity(4);
        System.out.println("Seed samosa");
        menu_item sandwich = menu_item.create_item("Sandwich", 15.0, "A sandwich is a food typically consisting of vegetables, sliced cheese or meat, placed on or between slices of bread, or more generally any dish wherein bread serves as a container or wrapper for another food type.", 0);
        sandwich.set_quantity(3);
        System.out.println("Seed sandwich");

        // category = 1 -> Beverages
        menu_item pepsi = menu_item.create_item("Pepsi", 20.0, "Pepsi is a carbonated soft drink manufactured by PepsiCo.", 1);
        pepsi.set_quantity(5);
        System.out.println("Seed pepsi");
        menu_item coffee = menu_item.create_item("Coffee", 25.0, "Coffee is a brewed drink prepared from roasted coffee beans, the seeds of berries from certain Coffea species.", 1);
        coffee.set_quantity(2);
        System.out.println("Seed coffee");
        menu_item tea = menu_item.create_item("Tea", 20.0, "Tea is an aromatic beverage commonly prepared by pouring hot or boiling water over cured or fresh leaves of the Camellia sinensis, an evergreen shrub native to East Asia.", 1);
        tea.set_quantity(5);
        System.out.println("Seed tea");
        menu_item milkshake = menu_item.create_item("Milkshake", 30.0, "A milkshake is a sweet, cold beverage that is usually made from milk, ice cream, or iced milk, and flavorings or sweeteners such as butterscotch, caramel sauce, chocolate syrup, or fruit syrup.", 1);
        milkshake.set_quantity(3);
        System.out.println("Seed milkshake");

        // category = 2 -> Desserts
        menu_item ice_cream = menu_item.create_item("Ice Cream", 30.0, "Ice cream is a sweetened frozen food typically eaten as a snack or dessert.", 2);
        ice_cream.set_quantity(3);
        System.out.println("Seed ice_cream");
        menu_item cake = menu_item.create_item("Cake", 40.0, "Cake is a form of sweet food made from flour, sugar, and other ingredients, that is usually baked.", 2);
        cake.set_quantity(2);
        System.out.println("Seed cake");

        // category = 3 -> Main Course
        menu_item biryani = menu_item.create_item("Biryani", 50.0, "Biryani is a mixed rice dish originating among the Muslims of the Indian subcontinent.", 3);
        biryani.set_quantity(2);
        System.out.println("Seed biryani");
        biryani = menu_item.create_item("Biryani", 50.0, "Biryani is a mixed rice dish originating among the Muslims of the Indian subcontinent.", 3);
        biryani.set_quantity(2);
        System.out.println("Seed biryani");
        menu_item pizza = menu_item.create_item("Pizza", 60.0, "Pizza is a savory dish of Italian origin consisting of a usually round, flattened base of leavened wheat-based dough topped with tomatoes, cheese, and often various other ingredients.", 3);
        pizza.set_quantity(1);
        System.out.println("Seed pizza");
        menu_item burger = menu_item.create_item("Burger", 70.0, "A hamburger is a sandwich consisting of one or more cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun.", 3);
        burger.set_quantity(3);
        System.out.println("Seed burger");


        System.out.println("Items seeded !");
    }
}
