package Assignment1;

import java.util.*;

public class Administrator extends User {
    /*
     * Required Functionalities
     * manage course catalog
     * manage student records
     * assign professors to courses
     * handle complaints
     */
    
    // Personal Information


    // Attributes
    private Integer admin_id;
    // private String name; // may need to add it later

    // Storing and Shared Datas -- may need to change it to protected
    private static HashMap<Integer, Administrator> Administrators = new HashMap<Integer, Administrator>();


    // Functionalities that i may require to implement everything easily
    
    

    // Functionalities i need to ensure the smooth running of the system
    


    // Required Functionalities
    void manage_course_catalog() {
        // view, add, and delete courses
    }
    void manage_student_records() {
        // view and update student records, grades, and personal information
    }
    void assign_professors_to_courses() {
        // assign professors to courses
    }
    void handle_complaints() { // Will do it later
        // view and respond to complaints
        // status of complaints should be updated as well
            // Pending 
            // Resolved
        // Add filter to view the complaints based on the status or date
        // provide resolution for the complaints


        // when assign grades move the course from current_courses to completed_courses
        // when making a new course and student keep in mind the null condition.
    }
}
