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
    private Integer current_semester;
    private Course[] courses;

    private HashMap<Integer, Student> Students = new HashMap<Integer, Student>();
    // create a fucniton to check whether the student is already registered or not

    void view_available_courses() {
        // Things need to do -- get all the avialable courses in the current semester
        // with all the details.
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
