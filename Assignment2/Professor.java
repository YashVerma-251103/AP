package Assignment2;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;
public class Professor extends CommonUser { // made -- testing left
    public static Scanner prof_sc = new Scanner(System.in);
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
    protected HashMap<Integer, Pair<Student,Integer>> assigning_grades_to_student = new HashMap<Integer, Pair<Student,Integer>>();

    // Getters 
    public String get_name() {
        return this.name;
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
        this.name = prof_sc.nextLine();
        System.out.print("Enter Professor Department: ");
        this.department = prof_sc.nextLine();
        System.out.print("Enter Professor ID: ");
        this.professor_id = prof_sc.nextLine();
        System.out.print("Enter Office Timings: ");
        this.office_timings = prof_sc.nextLine();
        
        // Login Info Setting
        System.out.print("Enter Professor's email: ");
        this.set_email(prof_sc.nextLine());
        System.out.print("Enter Professor's password: ");
        this.set_password(prof_sc.nextLine());
    }
    public void update_professor(){
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");   
            System.out.println("What do you want to update?");
            System.out.println("1. Department (press 1)");
            System.out.println("2. Name (press 2)");
            System.out.println("3. Office Timings (press 3)");
            System.out.println("4. Email (press 4)");
            System.out.println("5. Password (press 5)");
            System.out.print("Enter your choice: ");
            Integer choice = prof_sc.nextInt();
            if (choice == -1){
                return;
            }
            if (choice == 1){
                System.out.print("Enter new department: ");
                String temp = prof_sc.nextLine();
                this.department = temp;
                System.out.println("Department updated successfully.");
            } else if (choice == 2){
                System.out.print("Enter new name: ");
                String temp = prof_sc.nextLine();
                this.name = temp;
                System.out.println("Name updated successfully.");
            } else if (choice == 3){
                System.out.print("Enter new office timings: ");
                String temp = prof_sc.nextLine();
                this.office_timings = temp;
                System.out.println("Office Timings updated successfully.");
            } else if (choice == 4){
                System.out.print("Enter new email: ");
                String temp = prof_sc.nextLine();
                this.set_email(temp);
                System.out.println("Email updated successfully.");
            } else if (choice == 5){
                System.out.print("Enter new password: ");
                String temp = prof_sc.nextLine();
                this.set_password(temp);
                System.out.println("Password updated successfully.");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
    public void view_my_details(){
        System.out.println("Professor Details: ");
        System.out.println("Name: " + this.name);
        System.out.println("Department: " + this.department);
        System.out.println("Professor ID: " + this.professor_id);
        System.out.println("Office Timings: " + this.office_timings);
        System.out.println("Email: " + this.get_email());
    }
    public void view_my_course(){
        if (this.assigned_course == null){
            System.out.println("No course assigned to this professor.");
        } else {
            System.out.println("Course assigned to this professor: ");
            this.assigned_course.show_details();
        }
    }
    
    // Required functionalities
    public void manage_course(){
        System.out.println("Current course assigned to you: ");
        if (this.assigned_course == null){
            System.out.println("No course assigned to you.");
            return;
        } 
        this.assigned_course.show_details();
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");
            System.out.println("1. View enrolled students. (press 1)");
            System.out.println("2. Update Course Details. (press 2)");
            System.out.println("3. Update your details. (press 3)");
            System.out.print("Enter your choice: ");
            Integer choice = prof_sc.nextInt();
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
        if (this.assigned_course == null){
            System.out.println("No course assigned to this professor.");
            return;
        } else if (this.assigned_course.get_enrolled_students().isEmpty()){
            System.out.println("No students enrolled in the assigned course.");
            return;
        }
        this.assigned_course.show_enrolled_students();
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");
            System.out.print("Do you want to see details for any particular student? (Y/N)");
            String choice = prof_sc.next();
            if (choice == "-1"){
                return;
            } else if (choice.equals("Y") || choice.equals("y")) {
                System.out.print("Enter the student's roll number: ");
                Integer roll_number = prof_sc.nextInt();
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
    

    // need to implement a professor to pass a student.
    public void show_students_with_grades_assigned(){
        if (assigning_grades_to_student.isEmpty()){
            System.out.println("No students with grades assigned.");
            return;
        }
        System.out.println("Students with grades assigned: ");
        for (Integer roll_number : assigning_grades_to_student.keySet()){
            Pair<Student,Integer> pair = assigning_grades_to_student.get(roll_number);
            System.out.println("Roll Number: " + roll_number + " Grade: " + pair.getSecond());
        }
    }
    public void assign_grades_to_student(){
        if (this.assigned_course == null) {
            System.out.println("No course assigned to this professor.");
            return;
        } else if (this.assigned_course.get_enrolled_students().isEmpty()) {
            System.out.println("No students enrolled in the assigned course.");
            return;
        }
        System.out.println("Assigning grades to students for the course: " + this.assigned_course.get_course_id());
        this.assigned_course.show_enrolled_students();
        while (true) {
            System.out.println("Enter -1 to return to the previous menu.");
            System.out.print("Do you want to assign grades to any student? (Y/N)");
            String choice = prof_sc.next();
            if (choice == "-1"){
                return;
            } else if (choice.equals("Y") || choice.equals("y")) {
                this.show_students_with_grades_assigned();
                System.out.print("Enter the student's roll number: ");
                Integer roll_number = prof_sc.nextInt();
                if (Student.student_db.containsKey(roll_number)) {
                    Student student = Student.student_db.get(roll_number);
                    if (student.current_courses.containsKey(this.assigned_course.get_course_id())) {
                        System.out.print("Enter the grade: ");
                        Integer grade = prof_sc.nextInt();
                        Pair<Student,Integer> pair = new Pair<Student,Integer>(student, grade);
                        assigning_grades_to_student.put(roll_number, pair);
                        System.out.println("Grade assigned successfully.");
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
    public void pass_semester() {
        if (this.assigned_course == null) {
            System.out.println("No course assigned to this professor.");
            return;
        }else if (this.assigned_course.get_enrolled_students().isEmpty()) {
            System.out.println("No students enrolled in the assigned course.");
            return;
        } else if (assigning_grades_to_student.size() != this.assigned_course.get_enrolled_students().size()) {
            System.out.println("Grades not assigned to all students.");
            if (assigning_grades_to_student.isEmpty()) {
                System.out.println("No grades assigned to any student.");
            } else {
                System.out.println("Grades assigned to some students.");
                this.show_students_with_grades_assigned();
            }
            System.out.println("Please assign grades to all students before passing the semester.");
            return;
        }
        this.assigned_course.advance_to_next_semester(this.assigning_grades_to_student);
        // need to assign grades to students before this.
        System.out.println("Semester passed successfully for course: " + this.assigned_course.get_course_id());
    }


}
