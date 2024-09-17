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
    private Float CGPA;
    private Integer credits_registered;
    private Integer credit_limit = 20;
    private Integer grade;

    // Storage and Shared Datas
    protected static HashMap<Integer, Student> Students = new HashMap<Integer, Student>();
    protected static HashMap<Integer, HashMap<Boolean, String>> Complaints = new HashMap<Integer, HashMap<Boolean, String>>();
    private HashMap<String, Course> current_courses = new HashMap<String, Course>();
    // private HashMap<String, Course> completed_courses = new HashMap<String, Course>();
    private HashMap<String, <Course,Integer>> completed_courses = new HashMap<String, HashMap<Course,Integer>>();
    private HashMap<String, Course> dropped_courses = new HashMap<String, Course>();
    private Float SGPAs[] = new Float[8];

    // functionalites that i may require to implement everything easily
    Boolean check_prerequisites(Course course_to_be_checked) { // need to check if this is working properly
        for (Course course : course_to_be_checked.prerequisites) {
            if (!this.completed_courses.containsKey(course.get_code())) {
                return false;
            }
        }
        return true;
    }
    static void course_cleanup(String code) {
        for (Student student : Students.values()) {
            if(student.current_courses.containsKey(code)){
                student.current_courses.remove(code);
            }
            if(student.completed_courses.containsKey(code)){
                student.completed_courses.remove(code);
            }
            if(student.dropped_courses.containsKey(code)){
                student.dropped_courses.remove(code);
            }
            for (int i = 0; i < student.current_semester-1; i++) { // removing vague values from the SGPAs
                student.calculate_sgpa(i);
            }
            student.calculate_cgpa();
        }
    }
    static void show_all_students(){
        for (Student student : Students.values()) {
            System.out.println("Name: "+student.name+" | Roll Number: "+student.roll_number);
        }
    }
    static Integer show_student_details(Integer roll_number){
        Student student = Students.get(roll_number);
        if(student == null){
            System.out.println("No Student found with the given Roll Number!");
            return 0;
        }
        System.out.println("Student Roll Number : " + student.get_roll_number());
        System.out.println("Student Name : " + student.get_name());
        System.out.println("Student Email : " + student.get_email());
        System.out.println("Student Semester : " + student.current_semester);
        System.out.println("Student CGPA : " + student.get_cgpa());
        System.out.println("\nStudent Current Courses : ");
        student.current_courses.forEach((code, course) -> {
            System.out.println("Course Code : " + course.get_code() + " | Course Title : " + course.get_title());
        });
        System.out.println("\nStudent Grades : ");
        student.completed_courses.forEach((code, course) -> {
            System.out.println("Course Code : " + course.get_code() + " | Course Title : " + course.get_title() + " | Grade : " + course.get_grade());
        });
        return 1;
    }
    
    // Getters
    Float get_sgpa(int sem) {
        return SGPAs[sem - 1];
    }
    Float get_cgpa() {
        return CGPA;
    }
    String get_name() {
        return name;
    }
    Integer get_roll_number() {
        return roll_number;
    }
    Integer get_current_semester() {
        return current_semester;
    }
    Integer get_grade() {
        return grade;
    }
    
    // Setters
    void set_sgpa(int sem, Float sgpa) {
        SGPAs[sem - 1] = sgpa;
    }
    void set_cgpa(Float cgpa) {
        CGPA = cgpa;
    }
    void set_name(String name) {
        this.name = name;
    }
    void set_roll_number(Integer roll_number) {
        this.roll_number = roll_number;
    }
    void set_current_semester(Integer semester) {
        this.current_semester = semester;
    }
    void set_grade(Integer grade) {
        this.grade = grade;
    }


    // GPA Calculations
    void calculate_sgpa(int sem) {
        if (this.current_semester > 1) {
            Float sgpa = 0.0f;
            Integer courses = 0;
            for (String code : this.completed_courses.keySet()) {
                if (this.completed_courses.get(code).get_semester() == sem) {
                    sgpa += this.completed_courses.get(code).get_grade();
                    courses++;
                }
            }
            sgpa = (Float) (sgpa / courses);
            this.set_sgpa(sem, sgpa);
        } else {
            this.set_sgpa(sem, 0.0f);
        }
    }
    void calculate_cgpa() {
        if (this.current_semester > 1) {
            Float cgpa = 0.0f;
            Integer sems = 0;
            for (int i = 0; i < (this.current_semester) - 1; i++) {
                if (this.SGPAs[i] != null) {
                    cgpa += this.SGPAs[i];
                    sems++;
                }
            }
            this.set_cgpa((Float) (cgpa / sems));
        } else {
            this.set_cgpa(0.0f);
        }
    }

    // Creating Student
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
        // Personal Information
        System.out.println("Enter Student Name: ");
        this.name = sc.nextLine();
        System.out.println("Enter Student Roll Number: ");
        this.roll_number = sc.nextInt();
        this.current_semester = 1; // by default
        // Login Information
        System.out.println("Enter Student Email: ");
        this.set_email(sc.nextLine());
        System.out.println("Enter Student Password: ");
        this.set_password(sc.nextLine());
    }

    // Required Functionalities
    void view_available_courses() { // made - test left
        Course.show_course_list(this.current_semester);
        // menu to see the details of the course.
        String code_of_course_to_show;
        while (true) {
            System.out.println(
                    "Enter (-1) to return to previous Menu!\nEnter the course code to view the details of the course: ");
            code_of_course_to_show = sc.next();
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
            code_of_course_to_register = sc.next();
            if (code_of_course_to_register.equals("-1")) {
                return;
            } else if (this.credits_registered >= this.credit_limit) {
                System.out.println(
                        "You have reached the credit limit! You can't register for more courses. If you still want to register please drop some courses.");
            } else if (this.current_courses.containsKey(code_of_course_to_register)) {
                System.out.println("You are already registered in this course!");
            } else if (Course.course_bank.containsKey(code_of_course_to_register)) {
                if (this.check_prerequisites(Course.course_bank.get(code_of_course_to_register))) {
                    if ((Course.course_bank.get(code_of_course_to_register)
                            .get_enrollment_count()) < (Course.course_bank.get(code_of_course_to_register)
                                    .get_enrollment_limit())) {
                        Course.course_bank.get(code_of_course_to_register).enroll_student(this);
                        // Course.course_bank.get(code_of_course_to_register).increment_enrollment_count();
                        // // added in enroll_student
                        this.current_courses.put(code_of_course_to_register,
                                Course.course_bank.get(code_of_course_to_register));
                                this.credits_registered += Course.course_bank.get(code_of_course_to_register).get_credits();
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
    void track_academic_progress() { // made - test left
        // view the courses that are completed
        // view the grades of the completed courses
        // SGPA adn CGPA -- GPA only computed for the completed courses.

        System.out.println("Your Completed Courses are: ");
        for (String code : this.completed_courses.keySet()) {
            System.out.println(code + " : " + this.completed_courses.get(code).get_title() + " : "
                    + this.completed_courses.get(code).get_grade());
        }
        System.out.println("Current CGPA: " + this.CGPA);
        for (int i = 0; i < 8; i++) {
            System.out.println("SGPA in Semester " + (i + 1) + ": " + this.SGPAs[i]);
        }
        System.out.println();

    }
    void drop_course() { // made - test left
        // drop the course from the current courses
        // add the course to the dropped courses
        // decrement the enrollment count of the course
        // check if the course is in the current courses
        // check if the course is in the completed courses
        // check if the course is in the dropped courses

        System.out.println("Your current courses are: ");
        for (String code : this.current_courses.keySet()) {
            System.out.println(code + " : " + this.current_courses.get(code).get_title());
        }
        String code_of_course_to_drop;
        while (true) {
            if(this.credits_registered == 0){
                System.out.println("You don't have any courses to drop!");
                return;
            }
            System.out.println("Enter (-1) to return to previous Menu!\nEnter the course code to drop the course: ");
            code_of_course_to_drop = sc.next();
            if (code_of_course_to_drop.equals("-1")) {
                return;
            } else if (this.current_courses.containsKey(code_of_course_to_drop)) {
                this.current_courses.get(code_of_course_to_drop).denroll_student(this);
                // this.current_courses.get(code_of_course_to_drop).decrement_enrollment_count();
                // // added in denroll_student
                this.dropped_courses.put(code_of_course_to_drop, this.current_courses.get(code_of_course_to_drop));
                this.current_courses.remove(code_of_course_to_drop);
                System.out.println("Course Dropped Successfully!");
                this.credits_registered -= this.dropped_courses.get(code_of_course_to_drop).get_credits();
            } else {
                System.out.println("You don't have this Course! Please try again and Enter all things in Capitals.");
            }
        }
    }

    // Left
    void submit_complaint() { // will do it later
        // submit a complaint
        // view the status of the complaint
        // view the resolution of the complaint
        // filter the complaints based on the status or date
        // provide resolution for the complaints

    }
    void view_schedule() { // will make later.
        // able to view weekly course schedule
        // class timings
        // location
        // professor name
    }

}
