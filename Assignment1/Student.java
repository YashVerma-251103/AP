package Assignment1;

import java.util.*;

public class Student extends User {
    /*
     * Required Functionalites
     * view available courses
     * register for courses
     * view schedule
     * track academic progress
     * drop courses
     * submit complaints
     */

    // Personal Information
    private String name;
    private Integer roll_number;

    // Attributes
    private Integer current_semester;

    // Storing and Shared Datas -- may need to change it to protected
    // private ArrayList<Course> courses;
    private HashMap<String, Course> courses = new HashMap<String, Course>();
    private static HashMap<Integer, Student> Students = new HashMap<Integer, Student>();
    private static HashMap<Integer, HashMap<Boolean, String>> Complaints = new HashMap<Integer, HashMap<Boolean, String>>();

    // functionalites that i may require to implement everything easily
    // create a fucniton to check whether the student is already registered or not

    // Required Functionalities
    void view_available_courses() {
        // Things need to do -- get all the avialable courses in the current semester
        // with all the details.

        Integer semester = this.current_semester;
        for (int i = 1; i <= semester; i += 2) {
            if (Course.Courses.containsKey(i)) {
                Course.show_course_list(i);
            }
        }
    }

    void register_courses() {
        /*
         * registeration only for the available courses -- current the semester
         * process
         * 1. select the semester
         * 2. the courses from the list.
         * checks
         * completion of pre-requisites
         */
    }

    void view_schedule() {
        // able to view weekly course schedule
        // class timings
        // location
        // professor name
    }

    void track_academic_progress() {

    }

    void drop_course() {

    }

    void submit_complaint() {

    }

}
