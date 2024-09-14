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
    private HashMap<String, Course> current_courses = new HashMap<String, Course>();
    private HashMap<String, Course> completed_courses = new HashMap<String, Course>();
    private static HashMap<Integer, Student> Students = new HashMap<Integer, Student>();
    private static HashMap<Integer, HashMap<Boolean, String>> Complaints = new HashMap<Integer, HashMap<Boolean, String>>();

    // functionalites that i may require to implement everything easily
    // create a fucniton to check whether the student is already registered or not
    Boolean check_prerequisites(Course course_to_be_checked) { // need to check if this is working properly
        for (Course course : course_to_be_checked.prerequisites) {
            if (!this.completed_courses.containsKey(course.get_code())) {
                return false;
            }
        }
        return true;
    }

    static Student create_course() {
        Student new_student = new Student();
        new_student.set_details();
        if (Students.containsKey(new_student.roll_number)) {
            System.out.println("Student already exists!");
            return new_student;
        } else {
            Students.put(new_student.roll_number, new_student);
            return null;
        }
    }
    void set_details() {
        System.out.println("Enter Student Name: ");
        this.name = sc.nextLine();
        System.out.println("Enter Student Roll Number: ");
        this.roll_number = sc.nextInt();
        this.current_semester = 1; // by default

    }

    // Required Functionalities
    void view_available_courses() { // made - test left
        Course.show_course_list(this.current_semester);
        // menu to see the details of the course.
        String code_of_course_to_show;
        while (true) {
            System.out.println(
                    "Enter (-1) to return to previous Menu!\nEnter the course code to view the details of the course: ");
            code_of_course_to_show = sc.nextLine();
            if (code_of_course_to_show.equals("-1")) {
                return;
            } else if (Course.course_bank.containsKey(code_of_course_to_show)) {
                Course.view_course(Course.course_bank.get(code_of_course_to_show));
            } else {
                System.out.println("Invalid Course Code! Please try again and Enter all things in Capitals.");
            }
        }

    }

    void register_courses() { // made - test left
        /*
         * registeration only for the available courses -- current the semester
         * process
         * 1. select the semester
         * 2. the courses from the list.
         * checks
         * completion of pre-requisites
         */
        System.out.println("\nYour current semester is: " + this.current_semester);
        Course.show_course_list(current_semester, this); // shown the courses that are available to the student and he
                                                         // meets the prerequisites.
        String code_of_course_to_register;
        while (true) {
            System.out
                    .println("Enter (-1) to return to previous Menu!\nEnter the course code to register the course: ");
            code_of_course_to_register = sc.nextLine();
            if (code_of_course_to_register.equals("-1")) {
                return;
            } else if (Course.course_bank.containsKey(code_of_course_to_register)) {
                if (this.check_prerequisites(Course.course_bank.get(code_of_course_to_register))) {
                    if (Course.course_bank.get(code_of_course_to_register).get_enrollment_count() < Course.course_bank
                            .get(code_of_course_to_register).get_enrollment_limit()) {
                        Course.course_bank.get(code_of_course_to_register).increment_enrollment_count();
                        this.current_courses.put(code_of_course_to_register,
                                Course.course_bank.get(code_of_course_to_register));
                        System.out.println("Course Registered Successfully!");
                    } else {
                        System.out.println("Course Enrollment Limit Reached! Pleases select another course.");
                    }
                } else {
                    System.out.println("You do not meet the prerequisites for this course!");
                }
            } else {
                System.out.println("Invalid Course Code! Please try again and Enter all things in Capitals.");
            }
        }

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
