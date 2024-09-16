package redo2;
// package redo1;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends CommonUser { // left.
    // Personal information
    private String name;

    // Login credentials for admin
    private String admin_id;

    // Storing and Sharing DataBase
    protected static HashMap<String, Admin> admin_db = new HashMap<String, Admin>();

    // Setters
    public void set_user(String email, String password) {
        super.set_user(email, password);
    }
    public void set_email(String email) {
        super.set_email(email);
    }
    public void set_password(String password) {
        super.set_password(password);
    }
    public void set_admin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    // Getters
    public String get_admin_id() {
        return admin_id;
    }
    public String get_email() {
        return super.get_email();
    }
    public String get_password() {
        return super.get_password();
    }
    public String get_name() {
        return name;
    }

    // methods for easy implementations
    public static Admin create_admin() {
        Admin admin = new Admin();
        System.out.print("Enter the Admin ID: ");
        String admin_id = sc.next();
        admin.set_admin_id(admin_id);
        System.out.print("Enter the Admin Name: ");
        String name = sc.next();
        admin.name = name;
        System.out.print("Enter the email: ");
        String email = sc.next();
        admin.set_email(email);
        System.out.print("Enter the password: ");
        String password = sc.next();
        admin.set_password(password);
        if (admin.admin_db.containsKey(admin_id)) {
            System.out.println("Admin already exists.");
            return null;
        } else {
            admin.admin_db.put(admin_id, admin);
            System.out.println("Admin added successfully.");
            return admin;
        }
    }
    // need to implement the function to allow admin to pass the semester of a course /  or may be this implementation better handled for each student -- i do not know right now.
    public static void pass_semester() {
        
    }



    // Required functionalities
    // void manage_course_catalog() {
    //     while (true) {
    //         System.out.println("Enter -1 to return to previous menu");
    //         System.out.println("1. View Courses (press 1)");
    //         System.out.println("2. Add a new Course (press 2)");
    //         System.out.println("3. Delete an existing Course (press 3)");
    //         System.out.println("4. Update the details for existing course (press 4)");
    //         System.out.print("Enter your choice: ");
    //         Integer choice = sc.nextInt();
    //         if (choice == -1) {
    //             return;
    //         } else if (choice == 1) {
    //             while (true) {
    //                 System.out.println("Enter -1 to reutnr to previous menu.");
    //                 System.out.println("Choose your viewing format: ");
    //                 System.out.println("1. To see all courses (press 1)");
    //                 System.out.println("2. To see courses by semester (press 2)");
    //                 System.out.print("Enter your choice: ");
    //                 Integer format_choice = sc.nextInt();
    //                 if (format_choice == -1) {
    //                     break;
    //                 } else if (format_choice == 1) {
    //                     Course.display_all_courses();
    //                     break;
    //                 } else if (format_choice == 2) {
    //                     System.out.print("Enter the Semester: ");
    //                     Integer sem = sc.nextInt();
    //                     if (sem == -1) {
    //                         break;
    //                     } else if (Course.semester_course_db.containsKey(sem)) {
    //                         Course.display_courses_by_semester(sem);
    //                     } else {
    //                         System.out.println("No Course exist for this semester.");
    //                     }
    //                     break;
    //                 } else {
    //                     System.out.println("Invalid Choice!");
    //                 }
    //                 System.out.print("Wish to see all details of any particular course? (y/n)");
    //                 String course_choice = sc.next();
    //                 if (course_choice.equals("y") || course_choice.equals("Y")) {
    //                     System.out.print("Enter the course id: ");
    //                     String course_id = sc.next();
    //                     Course.display_course_details(course_id);
    //                 }
    //             }
    //         } else if (choice == 2) {
    //             Professor prof_to_be_assigned = assign_professor_to_course();
    //             if (prof_to_be_assigned != null) {
    //                 Course.create_course(prof_to_be_assigned);
    //             }
    //         } else if (choice == 3) {
    //             System.out.print("Enter the course id to be deleted: ");
    //             String course_id_to_delete = sc.next();
    //             if (Course.course_db.containsKey(course_id_to_delete)) {
    //                 Course.delete_course(course_id_to_delete);
    //             } else {
    //                 System.out.println("Course does not exist.");
    //             }
    //         } else if (choice == 4) {
    //             System.out.print("Enter the course id to updating course: ");
    //             String course_id_to_update = sc.next();
    //             if (Course.course_db.containsKey(course_id_to_update)) {
    //                 Course.update_course(course_id_to_update);
    //             } else {
    //                 System.out.println("Course does not exist.");
    //             }
    //         } else {
    //             System.out.println("Invalid choice. Please try again.");
    //         }
    //     }
    // }
    void manage_course_catalog() {
        while (true) {
            System.out.println("Enter -1 to return to previous menu");
            System.out.println("1. View Courses (press 1)");
            System.out.println("2. Add a new Course (press 2)");
            System.out.println("3. Delete an existing Course (press 3)");
            System.out.println("4. Update the details for an existing course (press 4)");
            System.out.print("Enter your choice: ");
            Integer choice = sc.nextInt();
            sc.nextLine();  // To handle nextInt()
    
            switch (choice) {
                case -1:
                    return;
                case 1:
                    view_courses();
                    break;
                case 2:
                    add_course();
                    break;
                case 3:
                    delete_course();
                    break;
                case 4:
                    update_course();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    } // modified
    
    private void view_courses() { // created for manage_course_catalog
        while (true) {
            System.out.println("Enter -1 to return to previous menu.");
            System.out.println("Choose your viewing format: ");
            System.out.println("1. To see all courses (press 1)");
            System.out.println("2. To see courses by semester (press 2)");
            Integer format_choice = sc.nextInt();
            sc.nextLine();  // Handle newline
            if (format_choice == -1) break;
    
            switch (format_choice) {
                case 1:
                    Course.display_all_courses();
                    break;
                case 2:
                    System.out.print("Enter the Semester: ");
                    Integer sem = sc.nextInt();
                    sc.nextLine();
                    if (Course.semester_course_db.containsKey(sem)) {
                        Course.display_courses_by_semester(sem);
                    } else {
                        System.out.println("No courses exist for this semester.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            
            System.out.print("Wish to see details of any particular course? (y/n): ");
            String course_choice = sc.nextLine();
            if (course_choice.equalsIgnoreCase("y")) {
                System.out.print("Enter the course ID: ");
                String course_id = sc.nextLine();
                Course.display_course_details(course_id);
            }
        }
    }
    private void add_course() { // created for manage_course_catalog
        Professor prof_to_be_assigned = assign_professor_to_course();
        if (prof_to_be_assigned != null) {
            Course.create_course(prof_to_be_assigned);
        }
    }
    private void delete_course() {// created for manage_course_catalog
        System.out.print("Enter the course ID to delete: ");
        String course_id_to_delete = sc.nextLine();
        if (Course.course_db.containsKey(course_id_to_delete)) {
            Course.delete_course(course_id_to_delete);
        } else {
            System.out.println("Course does not exist.");
        }
    }
    private void update_course() {// created for manage_course_catalog
        System.out.print("Enter the course ID to update: ");
        String course_id_to_update = sc.nextLine();
        if (Course.course_db.containsKey(course_id_to_update)) {
            Course.update_course(course_id_to_update);
        } else {
            System.out.println("Course does not exist.");
        }
    }
    
    static Professor assign_professor_to_course() {
        System.out.print("Enter Professor expertise (Mention Department): ");
        String department = sc.next();
        department = department.toLowerCase();
        ArrayList<Professor> profs = Professor.department_based_list_of_profs(department);
        if (profs.size() == 0) {
            System.out.println("No Professor found with the given expertise");
        } else {
            System.out.println("Professors with the given expertise: ");
            for (Professor prof : profs) {
                System.out.println(
                        "Professor ID : " + prof.get_professor_id() + " | Professor Name : " + prof.get_name());
            }
        }
        while (true) {
            System.out.println("Enter -1 to return to the previous menu");
            System.out.print("Enter the Professor ID for to assign the new course to: ");
            String prof_id = sc.next();
            if (prof_id.equals("-1")) {
                return null;
            }
            Professor prof = Professor.professor_db.get(prof_id);
            if (prof == null) {
                System.out.println("No Professor found with the given ID");
            } else {
                return prof;
            }
        }
    }
    void manage_students() {
        Student.show_all_students();
        while (true) {
            System.out.println("Enter -1 to return to previous menu");
            System.out.print("Student Roll Number to view the details of that student: ");
            Integer student_roll=sc.nextInt();
            if (student_roll.equals(-1)) {
                return;
            } else {
                if (Student.student_db.containsKey(student_roll)) {
                    Student student=Student.student_db.get(student_roll);
                    student.show_details();
                    System.out.print("Do you want to update the student details? (Y/N)");
                    String choice = sc.next();
                    if (choice.equals("Y") || choice.equals("y")) {
                        student.update_detials();
                    }    
                } else{
                    System.out.println("No student found with the given roll number!");
                }
            }
        }
    }
    // void handle_complaints() {
    //     while (true) {
    //         System.out.println("Enter -1 to return to previous menu");
    //         System.out.println("1. View all complaints (press 1)");
    //         System.out.println("2. View resolved complaints (press 2)");
    //         System.out.println("3. View pending complaints (press 3)");
    //         System.out.println("4. View complaints by a student (press 4)");
    //         System.out.println("5. View complaints resolved by me (press 5)");
    //         System.out.println("6. View complaint status (press 6)");
    //         System.out.println("7. Resolve a complaint (press 7)");
    //         // System.out.println("8. ");
    //         System.out.print("Enter your choice: ");
    //         Integer choice = sc.nextInt();
    //         if (choice == -1) {
    //             return;
    //         } else if (choice == 1) {
    //             Complaint.view_all_complaints();
    //         } else if (choice == 2) {
    //             Complaint.view_all_complaints(true);
    //         } else if (choice == 3) {
    //             Complaint.view_all_complaints(false);
    //         } else if (choice == 4) {
    //             System.out.print("Enter the student roll number: ");
    //             Integer student_roll = sc.nextInt();
    //             if (Student.student_db.containsKey(student_roll)) {
    //                 Student student = Student.student_db.get(student_roll);
    //                 Complaint.view_all_complaints(student);
    //             } else {
    //                 System.out.println("No student found with the given roll number.");
    //             }
    //         } else if (choice == 5) {
    //             Complaint.view_all_complaints(this);
    //         } else if (choice == 6){
    //             System.out.print("Enter the complaint id: ");
    //             Integer complaint_id = sc.nextInt();
    //             if (Complaint.complaint_db.containsKey(complaint_id)){
    //                 Complaint complaint = Complaint.complaint_db.get(complaint_id);
    //                 complaint.show_complaint_status();
    //             } else {
    //                 System.out.println("No complaint found with the given complaint id.");
    //             }
    //         } else if (choice == 7){
    //             System.out.print("Enter the complaint id: ");
    //             Integer complaint_id = sc.nextInt();
    //             if (Complaint.complaint_db.containsKey(complaint_id)){
    //                 Complaint complaint = Complaint.complaint_db.get(complaint_id);
    //                 System.out.print("Enter the response: ");
    //                 String response = sc.next();
    //                 complaint.resolve_complaint(response);
    //             } else {
    //                 System.out.println("No complaint found with the given complaint id.");
    //             }
    //         // } else if (choice == 8){
    //         // } else if (){
    //         }else {
    //             System.out.println("Invalid choice. Please try again.");
    //         }
    //     }
    // } 
    void handle_complaints() {
        while (true) {
            System.out.println("Enter -1 to return to previous menu");
            System.out.println("1. View all complaints (press 1)");
            System.out.println("2. View resolved complaints (press 2)");
            System.out.println("3. View pending complaints (press 3)");
            System.out.println("4. View complaints by a student (press 4)");
            System.out.println("5. View complaints resolved by me (press 5)");
            System.out.println("6. View complaint status (press 6)");
            System.out.println("7. Resolve a complaint (press 7)");
            System.out.print("Enter your choice: ");
            Integer choice = sc.nextInt();
            sc.nextLine();  // Handle newline
            
            switch (choice) {
                case -1:
                    return;
                case 1:
                    Complaint.view_all_complaints();
                    break;
                case 2:
                    Complaint.view_all_complaints(true);
                    break;
                case 3:
                    Complaint.view_all_complaints(false);
                    break;
                case 4:
                    view_complaints_by_student();
                    break;
                case 5:
                    Complaint.view_all_complaints(this);
                    break;
                case 6:
                    view_complaint_status();
                    break;
                case 7:
                    resolve_complaint();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    
    private void view_complaints_by_student() {
        System.out.print("Enter the student roll number: ");
        Integer student_roll = sc.nextInt();
        if (Student.student_db.containsKey(student_roll)) {
            Student student = Student.student_db.get(student_roll);
            Complaint.view_all_complaints(student);
        } else {
            System.out.println("No student found with the given roll number.");
        }
    }
    
    private void view_complaint_status() {
        System.out.print("Enter the complaint ID: ");
        Integer complaint_id = sc.nextInt();
        if (Complaint.complaint_db.containsKey(complaint_id)) {
            Complaint complaint = Complaint.complaint_db.get(complaint_id);
            complaint.show_complaint_status();
        } else {
            System.out.println("No complaint found with the given ID.");
        }
    }
    
    private void resolve_complaint() {
        System.out.print("Enter the complaint ID: ");
        Integer complaint_id = sc.nextInt();
        sc.nextLine();  // Handle newline
        if (Complaint.complaint_db.containsKey(complaint_id)) {
            Complaint complaint = Complaint.complaint_db.get(complaint_id);
            System.out.print("Enter the response: ");
            String response = sc.nextLine();
            complaint.resolve_complaint(response,this);
        } else {
            System.out.println("No complaint found with the given ID.");
        }
    } // modified
    
}
