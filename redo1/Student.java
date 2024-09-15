package redo1;

import java.util.HashMap;
public class Student extends ProfStudComman{

    
    // Personal Information
    private String name;
    private Integer student_roll_number;

    // Attributes
    private static Integer credit_limit=20;
    private Integer credits_registered=0;
    private Integer current_semester=1;
    private Float cgpa=0.0f;

    // Storing and Sharing DataBase
    protected static HashMap<Integer, Student> student_db = new HashMap<Integer, Student>();
    protected HashMap<String,Course> current_courses = new HashMap<String,Course>();
    protected HashMap<String,Course> dropped_courses = new HashMap<String,Course>();
    protected HashMap<String,Pair<Course,Integer>> completed_courses = new HashMap<String,Pair<Course,Integer>>();
    private Float sgpas[]=new Float[8];

    // Getters
    public String get_name() {
        return name;
    }
    public Integer get_student_roll_number() {
        return student_roll_number;
    }
    public Integer get_current_semester() {
        return current_semester;
    }
    public Float get_cgpa() {
        return cgpa;
    }
    public Integer get_credits_registered() {
        return credits_registered;
    }
    public Integer get_credit_limit() {
        return credit_limit;
    }

    // Setters
    public void set_name(String name) {
        this.name = name;
    }
    public void set_student_roll_number(Integer student_roll_number) {
        this.student_roll_number = student_roll_number;
    }
    public void set_current_semester(Integer current_semester) {
        this.current_semester = current_semester;
    }
    public void set_cgpa(Float cgpa) {
        this.cgpa = cgpa;
    }
    public void set_sgpa(Integer sem, Float sgpa) {
        this.sgpas[sem-1] = sgpa;
    }
    public void set_credits_registered(Integer credits_registered) {
        this.credits_registered = credits_registered;
    }

    // methods for easy implementations
    public void calculate_cgpa(){
        if (this.current_semester > 1) {
            Float cgpa = 0.0f;
            Integer sems = 0;
            for (int i = 0; i < (this.current_semester) - 1; i++) {
                if (this.sgpas[i] != null) {
                    cgpa += this.sgpas[i];
                    sems++;
                }
            }
            this.set_cgpa((Float) (cgpa / sems));
        } else {
            this.set_cgpa(0.0f);
        }
    }
    public void calculate_sgpa(int sem) {
        if (this.current_semester > 1) {
            Float sgpa = 0.0f;
            Integer courses = 0;
            for (Pair<Course,Integer> course_grade_pair : this.completed_courses.values()) {
                if (course_grade_pair.getFirst().get_offered_semester()==sem){
                    sgpa += course_grade_pair.getSecond();
                    courses++;
                }
            }
            sgpa = (Float) (sgpa / courses);
            this.set_sgpa(sem, sgpa);
        } else {
            this.set_sgpa(sem, 0.0f);
        }
    }
    public void set_student(){
        System.out.println("Enter the following details: ");
        System.out.print("Enter the name of the student:");
        this.set_name(sc.nextLine());
        System.out.print("Enter the roll number of the student:");
        this.set_student_roll_number(Integer.parseInt(sc.nextLine()));

        // Login Info Setting
        System.out.print("Enter Student's email:");
        this.set_email(sc.nextLine());
        System.out.print("Enter Student's password:");
        this.set_password(sc.nextLine());

    }
    public void view_registered_courses(){
        System.out.println("Registered Courses: ");
        for (Course course : this.current_courses.values()) {
            System.out.println(course.get_course_id() + " " + course.get_course_name());
        }
    }
    
    public static void changes_in_completed_courses(String old_course_id,String new_course_id){
        for (Student student : student_db.values()) {
            if (student.completed_courses.containsKey(old_course_id)) {
                Pair<Course,Integer> course_grade_pair = student.completed_courses.get(old_course_id);
                student.completed_courses.remove(old_course_id);
                student.completed_courses.put(new_course_id, course_grade_pair);
            } else if (student.dropped_courses.containsKey(old_course_id)) {
                Course course = student.dropped_courses.get(old_course_id);
                student.dropped_courses.remove(old_course_id);
                student.dropped_courses.put(new_course_id, course);
            }
        }
    }
    public static Student create_student(){
        Student student = new Student();
        student.set_student();
        if (student_db.containsKey(student.student_roll_number)) {
            System.out.println("Student already exists.");
            return null;
        } else {
            student_db.put(student.student_roll_number, student);
            System.out.println("Student added successfully.");
        }
        return student;
    }
    public void show_details(){
        System.out.println("Name: " + this.name);
        System.out.println("Roll Number: " + this.student_roll_number);
        System.out.println("Email: " + this.get_email());
        System.out.println("Current Semester: " + this.current_semester);
        System.out.println("CGPA: " + this.cgpa);
        System.out.println("Credits Registered for this semester: " + this.credits_registered);
    }

    // Required functionalities
    public void view_available_courses(){
        System.out.println("Available Courses: ");
        Course.display_courses_by_semester(this.current_semester);
        System.out.println("Would you like to see details of any course? (y/n)");
        String choice = sc.next();        
        if (choice.equals("y") || choice.equals("Y")) {
            System.out.println("Enter the course id: ");
            String course_id = sc.next();
            Course.display_course_details(course_id);
        }
        return;
    }
    public void register_course(){
        if (this.credits_registered < credit_limit) {
            Course.display_courses_by_semester(this.current_semester);
            System.out.println("Enter the course id: ");
            String course_id = sc.next();
            if (Course.course_db.containsKey(course_id)) {
                Course course = Course.course_db.get(course_id);
                if (course.get_offered_semester() == this.current_semester) {
                    course.enroll_student(this);
                } else {
                    System.out.println("Course not offered in this semester.");
                }
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Credit limit reached.");
        }
    }
    public void drop_course(){
        if (this.current_courses.size() > 0) {
            System.out.println("Registered Courses: ");
            for (Course course : this.current_courses.values()) {
                System.out.println("Course Id : " + course.get_course_id() + " | Course Name : " + course.get_course_name());
            }
            System.out.print("Enter the course id to delete: ");
            String course_id = sc.next();
            if (this.current_courses.containsKey(course_id)) {
                Course course = this.current_courses.get(course_id);
                course.drop_student(this);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("No courses registered.");
        }
    }
    public void track_academic_progress(){
        System.out.println("Academic Progress: ");
        System.out.println("CGPA: " + this.cgpa);
        System.out.println("Your current semester is: " + this.current_semester);
        System.out.println("Currently Enrolled Courses: ");
        for (Course course : this.current_courses.values()) {
            System.out.println("Course ID : " + course.get_course_id() + " | Course Name : " + course.get_course_name());
        }
        for (int i = 0; i < this.current_semester; i++) {
            System.out.println("Semester " + (i + 1) + " SGPA: " + this.sgpas[i]);
            System.out.println("Completed Courses: ");
            for (Pair<Course,Integer> course_grade_pair : this.completed_courses.values()) {
                if (course_grade_pair.getFirst().get_offered_semester() == i + 1) {
                    System.out.println("Course ID : " + course_grade_pair.getFirst().get_course_id() + " | Course Name : " + course_grade_pair.getFirst().get_course_name() + " | Grade: " + course_grade_pair.getSecond());
                }
            }
        }
    }
    public void submit_complaint(){
        System.out.println("Enter the complaint: ");
        String complaint = sc.nextLine();
        //Structure == HashMap<student_roll_number,HashMap<Pair<Status,Complaint_id>,Pair<Complaint,Response>>> 
        // Adding complaint to the database
        Complaint.create_complaint(this, complaint);
        // Complaint my_complaint = Complaint.create_complaint(this, complaint);

        System.out.println("Complaint submitted successfully.");
    }
    public void see_complaint(){
        Complaint.view_all_complaints(this);
    }
    public void see_status_of_particular_complaint(){
        System.out.println("Enter the complaint id: ");
        Integer complaint_id = sc.nextInt();
        if (Complaint.complaint_db.containsKey(complaint_id)) {
            Complaint.complaint_db.get(complaint_id).view_complaint();
        } else {
            System.out.println("Complaint not found.");
        }
    }
    public void view_schedule(){} // To be implemented


}
