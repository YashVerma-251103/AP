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
    private Scanner sc = new Scanner(System.in);
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
    Boolean check_prerequisites(Course course_to_be_checked) { // need to check if this is working properly
        for (Course course : course_to_be_checked.prerequisites) {
            if (!this.courses.containsKey(course.get_code())) {
                return false;
            }
        }
        return true;
    }

    // Required Functionalities
    void view_available_courses(){
        Course.show_course_list(this.current_semester);
        
    }
    // void view_available_courses() { // made - test left.
    //     Integer semester = this.current_semester;
    //     for (int i = 1; i <= semester; i += 2) {
    //         System.out.println();
    //         if (Course.sem_course_bank.containsKey(i)) {
    //             Course.show_course_list(i);
    //         }
    //     }
    //     System.out.println();
    //     // menu to see the details of the course.
    //     String code_of_course_to_show;
    //     while (true){
    //         System.out.println("Enter (-1) to return to previous Menu!\nEnter the course code to view the details of the course: ");
    //         code_of_course_to_show = sc.nextLine();
    //         if(code_of_course_to_show.equals("-1")){
    //             return;
    //         }
    //         else if(Course.course_bank.containsKey(code_of_course_to_show)){
    //             Course.view_course(Course.course_bank.get(code_of_course_to_show));
    //         }
    //         else{
    //             System.out.println("Invalid Course Code! Please try again and Enter all things in Capitals.");
    //         }
    //     }
    // }

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
