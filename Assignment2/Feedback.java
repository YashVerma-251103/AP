package Assignment2;

public class Feedback<T>{
    
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