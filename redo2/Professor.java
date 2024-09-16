package redo2;
// package redo1;
import java.util.HashMap;
import java.util.ArrayList;
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
    // protected HashMap<> current_course_student_grade_assigning=new HashMap<>()
    
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
    public static ArrayList<Professor> department_based_list_of_profs(String department){
        ArrayList<Professor> profs = new ArrayList<Professor>();
        for(Professor prof : professor_db.values()){
            if(prof.get_department().equals(department)){
                profs.add(prof);
            }
        }
        return profs;
    }
    public void set_professor(){
        System.out.println("Enter the following details: ");
        System.out.print("Enter Professor Name: ");
        this.name = sc.nextLine();
        System.out.print("Enter Professor Department: ");
        this.department = sc.nextLine();
        System.out.print("Enter Professor ID: ");
        this.professor_id = sc.nextLine();
        System.out.print("Enter Office Timings: ");
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
    
    // need to implement a professor to pass a student.


    // Required functionalities
    // public void manage_course(){
    //     System.out.println("Current course assigned to you: ");
    //     this.assigned_course.show_details();
    //     while (true) {
    //         System.out.println("Enter -1 to return to the previous menu.");
    //         System.out.println("1. View enrolled students. (press 1)");
    //         System.out.println("2. Update Course Details. (press 2)");
    //         System.out.println("3. Update your details. (press 3)");
    //         System.out.print("Enter your choice: ");
    //         Integer choice = sc.nextInt();
    //         if (choice == -1){
    //             return;
    //         } else if (choice == 1){
    //             this.view_enrolled_students();
    //         } else if (choice == 2){
    //             Course.update_course(this.assigned_course.get_course_id());
    //         } else if (choice == 3){
    //             this.update_professor();
    //         } else {
    //             System.out.println("Invalid choice.");
    //         }
    //     }
    // }
    public void manage_course(){
        if (this.assigned_course == null) {
            System.out.println("No course has been assigned to you.");
            return;
        }
    
        System.out.println("Current course assigned to you: ");
        this.assigned_course.show_details();
        
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");
            System.out.println("1. View enrolled students. (press 1)");
            System.out.println("2. Update Course Details. (press 2)");
            System.out.println("3. Update your details. (press 3)");
            System.out.print("Enter your choice: ");
            
            Integer choice = sc.nextInt();
            sc.nextLine();  // To avoid input conflicts
            
            switch (choice) {
                case -1:
                    return;
                case 1:
                    this.view_enrolled_students();
                    break;
                case 2:
                    Course.update_course(this.assigned_course.get_course_id());
                    break;
                case 3:
                    this.update_professor();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    } // modified
    

    // public void view_enrolled_students(){
    //     this.assigned_course.show_enrolled_students();
    //     while (true) {
    //         System.out.println("Enter -1 to return to the previous menu.");
    //         System.out.print("Do you want to see details for any particular student? (Y/N)");
    //         String choice = sc.next();
    //         if (choice == "-1"){
    //             return;
    //         } else if (choice.equals("Y") || choice.equals("y")) {
    //             System.out.print("Enter the student's roll number: ");
    //             Integer roll_number = sc.nextInt();
    //             if (Student.student_db.containsKey(roll_number)) {
    //                 Student student = Student.student_db.get(roll_number);
    //                 if (student.current_courses.containsKey(this.assigned_course.get_course_id())) {
    //                     student.show_details();
    //                 } else {
    //                     System.out.println("Student not enrolled in this course.");                
    //                 }
    //             } else {
    //                 System.out.println("Student not found.");
    //             }
    //         } else{
    //             break;
    //         }
    //     }
    // }

    public void view_enrolled_students() {
        if (this.assigned_course != null) {
            this.assigned_course.show_enrolled_students();
        } else {
            System.out.println("No course assigned yet.");
            return;
        }
    
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");
            System.out.print("Do you want to see details for any particular student? (Y/N): ");
            String choice = sc.next();
            
            if (choice.equals("-1")) {
                return;
            } else if (choice.equalsIgnoreCase("Y")) {
                System.out.print("Enter the student's roll number: ");
                Integer roll_number = sc.nextInt();
                
                if (Student.student_db.containsKey(roll_number)) {
                    Student student = Student.student_db.get(roll_number);
                    
                    if (student.current_courses.containsKey(this.assigned_course.get_course_id())) {
                        student.show_details();
                    } else {
                        System.out.println("Student is not enrolled in this course.");
                    }
                } else {
                    System.out.println("Student not found.");
                }
            } else {
                break;
            }
        }
    } // modified
    
    

}
