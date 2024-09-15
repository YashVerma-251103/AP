package redo1;
import java.util.HashMap;
public class Professor extends ProfStudComman {
    // Personal Information
    private String name;

    // Attributes
    private String department; // Department is the expertise of the professor.
    private String professor_id;
    private String office_timings;
    private Course assigned_course;

    // Storing and Sharing DataBase
    protected static HashMap<String, Professor> professor_db = new HashMap<String, Professor>();
    
    // Getters 
    public String get_name() {
        return name;
    }
    public String get_department() {
        return department;
    }
    public String get_professor_id() {
        return professor_id;
    }
    public String get_office_timings() {
        return office_timings;
    }
    public Course get_assigned_course() {
        return assigned_course;
    }

    // Setters
    public void set_name(String name) {
        this.name = name;
    }
    public void set_department(String department) {
        this.department = department;
    }
    public void set_professor_id(String professor_id) {
        this.professor_id = professor_id;
    }
    public void set_office_timings(String office_timings) {
        this.office_timings = office_timings;
    }
    public void set_assigned_course(Course assigned_course) {
        this.assigned_course = assigned_course;
    }

    // methods for easy implementations
    public static Professor create_professor(){
        Professor professor = new Professor();
        professor.set_professor();
        if (professor_db.containsKey(professor.professor_id)) {
            System.out.println("Professor already exists.");
            return null;
        } else {
            professor_db.put(professor.professor_id, professor);
            System.out.println("Professor added successfully.");
        }
        return professor;
    }
    public void set_professor(){
        System.out.println("Enter the following details: ");
        System.out.print("Name: ");
        this.name = sc.nextLine();
        System.out.print("Department: ");
        this.department = sc.nextLine();
        System.out.print("Professor ID: ");
        this.professor_id = sc.nextLine();
        System.out.print("Office Timings: ");
        this.office_timings = sc.nextLine();
        
        // Login Info Setting
        System.out.print("Enter Professor's email: ");
        this.set_email(sc.nextLine());
        System.out.print("Enter Professor's password: ");
        this.set_password(sc.nextLine());
    }

    // Required functionalities
    public void manage_course(){}
    public void view_enrolled_students(){}
    

}
