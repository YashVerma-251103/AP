package Assignment1;

import java.util.HashMap;

public class Professor extends User {
    /* Required Functionalities
     * manage courses
     * view enrolled students
     */
    private HashMap<Integer,Professor> Professors = new HashMap<Integer,Professor>();
    void manage_courses(){
        // Only read and update course
        // addition and deletion of course is handled by admin
    }
    void view_enrolled_students(){}
}