package Assignment1;

import java.util.*;

public class Professor extends User {
    /* Required Functionalities
     * manage courses
     * view enrolled students
     */

    // Personal Information
    private String name;

    // Attributes
    private Integer professor_id;

    // Storing and Shared Datas -- may need to change it to protected
    private HashMap<Integer,Professor> Professors = new HashMap<Integer,Professor>();
    
    // functions that will help me to implement everything easily
    String get_name(){
        return name;
    }

    // Required Functionalities
    void manage_courses(){
        // Only read and update course
        // addition and deletion of course is handled by admin
    }
    void view_enrolled_students(){}
}