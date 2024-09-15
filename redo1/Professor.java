package redo1;
import java.util.HashMap;
public class Professor extends ProfStudComman { // made -- testing left
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
    public void update_professor(){
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");   
            System.out.println("What do you want to update?");
            System.out.println("1. Name (press 1)");
            System.out.println("2. Department (press 2)");
            System.out.println("3. Office Timings (press 3)");
            System.out.println("4. Email (press 4)");
            System.out.println("5. Password (press 5)");
            System.out.print("Enter your choice: ");
            Integer choice = sc.nextInt();
            if (choice == -1){
                return;
            } else if (choice == 1){
                System.out.print("Enter new name: ");
                this.name = sc.nextLine();
                System.out.println("Name updated successfully.");
            } else if (choice == 2){
                System.out.print("Enter new department: ");
                this.department = sc.nextLine();
                System.out.println("Department updated successfully.");
            } else if (choice == 3){
                System.out.print("Enter new office timings: ");
                this.office_timings = sc.nextLine();
                System.out.println("Office Timings updated successfully.");
            } else if (choice == 4){
                System.out.print("Enter new email: ");
                this.set_email(sc.nextLine());
                System.out.println("Email updated successfully.");
            } else if (choice == 5){
                System.out.print("Enter new password: ");
                this.set_password(sc.nextLine());
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    
    // Required functionalities
    public void manage_course(){
        System.out.println("Current course assigned to you: ");
        this.assigned_course.show_details();
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");
            System.out.println("1. View enrolled students. (press 1)");
            System.out.println("2. Update Course Details. (press 2)");
            System.out.println("3. Update your details. (press 3)");
            System.out.print("Enter your choice: ");
            Integer choice = sc.nextInt();
            if (choice == -1){
                return;
            } else if (choice == 1){
                this.view_enrolled_students();
            } else if (choice == 2){
                Course.update_course(this.assigned_course.get_course_id());
            } else if (choice == 3){
                this.update_professor();
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    public void view_enrolled_students(){
        this.assigned_course.show_enrolled_students();
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");
            System.out.println("Do you want to see details for any particular student? (Y/N)");
            String choice = sc.next();
            if (choice == "-1"){
                return;
            } else if (choice.equals("Y") || choice.equals("y")) {
                System.out.println("Enter the student's roll number: ");
                Integer roll_number = sc.nextInt();
                if (Student.student_db.containsKey(roll_number)) {
                    Student student = Student.student_db.get(roll_number);
                    if (student.current_courses.containsKey(this.assigned_course.get_course_id())) {
                        student.show_details();
                    } else {
                        System.out.println("Student not enrolled in this course.");
                        
                    }
                } else {
                    System.out.println("Student not found.");
                }
            } else{
                break;
            }
        }
    }
    

}
