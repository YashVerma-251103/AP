package com.Start.Structures;


import com.Start.Classes.menu_item;

public class review {
    private Integer rating;
    private String review_s;
    private Integer item_id;
    private menu_item item;
    
    public  menu_item get_item() {
        return item;
    }

    public void set_item(menu_item item) {
        this.item = item;
    }

    public Integer get_item_id() {
        return item_id;
    }

    public review give_review(String review_s) {

        review new_review = new review();
        new_review.review_s = review_s;
        return new_review;
    }
    public review give_review(Integer rating) {
        review new_review = new review();
        new_review.rating = rating;
        return new_review;
    }

    public review give_review(Integer rating, String review_s) {
        review new_review = new review();
        new_review.rating = rating;
        new_review.review_s = review_s;
        return new_review;
    }

    public void update_review(Integer rating, String review_s) {
        this.rating = rating;
        this.review_s = review_s;
    }
    public void update_review(Integer rating) {
        this.rating = rating;
    }
    public void update_review(String review_s) {
        this.review_s = review_s;
    }

    public Integer get_rating() {
        return rating;
    }
    public String get_review() {
        return review_s;
    }

}
