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
    protected static HashMap<Integer,HashMap<Boolean,String>> student_course_db = new HashMap<Integer,HashMap<Boolean,String>>();
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
            }
        }
    }

    // Required functionalities
    public void view_available_courses(){}
    public void register_course(){}
    public void drop_course(){}
    public void track_academic_progress(){}
    public void submit_complaint(){}
    public void view_schedule(){}


}
