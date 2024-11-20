package com.Start.Structures;

import java.util.TreeMap;

public class review_data_base {
    private static TreeMap<Integer, TreeMap<Integer, review>> review_db = new TreeMap<Integer, TreeMap<Integer, review>>();
    // TreeMap<item_id, HashMap<customer_id, review>>

    public static TreeMap<Integer, TreeMap<Integer, review>> get_review_db() {
        return review_db;
    }
    public static TreeMap<Integer, review> get_reviews_by_item_id(int item_id) {
        return review_db.get(item_id);
    }
}
