package Assignment2;

import java.lang.Object;

public class Feedback<T>{
    // private T feedback;
    // private int rating;
    // private String comment;
    // 
    // public Feedback(){
    //     this.feedback = null;
    //     this.rating = 0;
    //     this.comment = null;
    // }
    // 
    // public Feedback(T feedback, int rating, String comment){
    //     this.feedback = feedback;
    //     this.rating = rating;
    //     this.comment = comment;
    // }
    // 
    // public T getFeedback(){
    //     return feedback;
    // }
    // 
    // public int getRating(){
    //     return rating;
    // }
    // 
    // public String getComment(){
    //     return comment;
    // }
    // 
    // public void setFeedback(T feedback){
    //     this.feedback = feedback;
    // }
    // 
    // public void setRating(int rating){
    //     this.rating = rating;
    // }
    // 
    // public void setComment(String comment){
    //     this.comment = comment;
    // }
    // 
    // @Override
    // public String toString(){
    //     return "Feedback: " + feedback + "\nRating: " + rating + "\nComment: " + comment;
    // }

    // Attributes
    private T feedback_given;

    
    // Setters
    void set_feedback(T feedback){
        this.feedback_given = feedback;
    }


    // Getters
    T get_feedback(){
        return this.feedback_given;    
    }

    @Override
    public String toString() {
        return "\""+this.feedback_given+"\"";
    }
}