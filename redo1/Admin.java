package redo1;

import java.util.HashMap;

public class Admin extends CommonUser{ // left.
    // Personal information
    private String name;
    
    // Login credentials for admin
    private String admin_id;
    
    // Storing and Sharing DataBase
    protected HashMap<String, Admin> admin_db=new HashMap<String,Admin>();

    
    // Setters
    public void set_admin(String admin_id) {
        this.admin_id = admin_id;
    }
    public void set_user(String email, String password) {
        super.set_user(email, password);
    }
    public void set_email(String email) {
        super.set_email(email);
    }
    public void set_password(String password) {
        super.set_password(password);
    }
    public void set_admin_id(String admin_id) {
        this.admin_id = admin_id;
    }
    
    // Getters
    public String get_admin_id() {
        return admin_id;
    }
    public String get_email() {
        return super.get_email();
    }
    public String get_password() {
        return super.get_password();
    }
    public String get_name() {
        return name;
    }

    // methods for easy implementations
    
    // Required functionalities
    void manage_courses(){} // left
    static Professor assign_professor_to_course() {} //left
    void manage_students(){} // left
    void handle_complaints(){} // left
}
