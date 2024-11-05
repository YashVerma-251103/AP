package Start.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import Start.Structures.Pair;


public class menu_item implements Comparable<menu_item> {

    @Override
    public int compareTo(menu_item other) {

        // Assuming menu_item has a unique identifier or name to compare

        return this.get_id().compareTo(other.get_id());

    }


    private static canteen ct = canteen.get_instance();
    private static Integer item_id_counter = 1;

    private static String[] categories = { "Snacks", "Beverages", "Desserts", "Main Course"};
    // private static ArrayList<TreeMap<Integer, menu_item>> category_menu = new ArrayList<TreeMap<Integer, menu_item>>();
    private static Integer category_counter = categories.length;
    private static TreeMap<Integer,TreeMap<Integer, menu_item>> category_menu = new TreeMap<Integer,TreeMap<Integer, menu_item>>();

    static {
        for (int i = 0; i < categories.length; i++) {
            category_menu.put(i, new TreeMap<Integer, menu_item>());
        }
    }


    private Integer id;
    private Integer quantity_in_stock;
    private Integer category;
    private String name;
    private String description;
    private Double price;
    private Boolean available;
    
    private TreeMap<Integer, Pair<customer,Integer>> bought_customers = new TreeMap<Integer, Pair<customer,Integer>>();
    // TreeMap<customer_id, Pair<customer, times_bought>>


    // Constructor
    public static menu_item create_item(String name, Double price, String description, Integer category) {
        menu_item item = new menu_item();
        item.name = name;
        item.price = price;
        item.description = description;
        item.category = category;
        item.id = item_id_counter++;
        item.available = true;
        category_menu.get(category).put(item.id, item);
        ct.get_menu().put(item.id, item);
        return item;
    }

    // Getters
    public String get_name() {
        return name;
    }
    public Double get_price() {
        return price;
    }
    public String get_description() {
        return description;
    }
    public String get_category_string() {
        return categories[category];
    }
    public Integer get_category() {
        return category;
    }
    public Integer get_id() {
        return id;
    }
    public Boolean get_available() {
        return available;
    }
    public Integer get_quantity_in_stock() {
        return quantity_in_stock;
    }
    // public static ArrayList<TreeMap<Integer, menu_item>> get_category_menu() {
    //     return category_menu;
    // }
    public static TreeMap<Integer, TreeMap<Integer, menu_item>> get_category_menu() {
        return category_menu;
    }
    public static String[] get_categories() {
        return categories;
    }
    public static Integer get_category_counter() {
        return category_counter;
    }
    public TreeMap<Integer, Pair<customer,Integer>> get_bought_customers() {
        return bought_customers;
    }

    // Setters
    public void set_name(String name) {
        this.name = name;
    }

    public void set_price(Double price) {
        this.price = price;
    }

    public void set_description(String description) {
        this.description = description;
    }

    public void set_category(Integer category) {
        this.category = category;
    }

    public void set_quantity(Integer quantity_in_stock) {
        this.quantity_in_stock = quantity_in_stock;
    }

    // public void set_id(Integer id) {
    // this.id = id;
    // }
    public void set_available(Boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return ("Name: " + name + "\nPrice: " + price + "\nDescription: " + description + "\nCategory: " + category
                + "\nID: " + id);
    }

    // for customers.
    public Boolean is_available() {
        return available;
    }
    
    public static TreeMap<Integer, menu_item> get_menu_by_category(Integer category) {
        return category_menu.get(category);
    }
    public static Boolean bought(customer c, menu_item item) {
        if (item.bought_customers.containsKey(c.get_customer_id())) {
            return true;
        }
        return false;
    }
}
