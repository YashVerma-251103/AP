package Assignment1.redo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Admin extends CommonUser { // left.
    public static Scanner admin_sc = new Scanner(System.in);
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
    public void set_name(String name) {
        this.name = name;
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
        String admin_id = admin_sc.next();
        admin.set_admin_id(admin_id);
        System.out.print("Enter the Admin Name: ");
        String name = admin_sc.next();
        admin.name = name;
        System.out.print("Enter the email: ");
        String email = admin_sc.next();
        admin.set_email(email);
        System.out.print("Enter the password: ");
        String password = admin_sc.next();
        admin.set_password(password);
        if (admin_db.containsKey(admin_id)) {
            System.out.println("Admin already exists.");
            return null;
        } else {
            admin_db.put(admin_id, admin);
            System.out.println("Admin added successfully.");
            return admin;
        }
    }

    // Required functionalities
    void manage_course_catalog() {
        while (true) {
            System.out.println("Enter -1 to return to previous menu");
            System.out.println("1. View Courses (press 1)");
            System.out.println("2. View Details of a particular course (press 2)");
            System.out.println("3. Add a new Course (press 3)");
            System.out.println("4. Delete an existing Course (press 4)");
            System.out.println("5. Update the details for existing course (press 5)");
            System.out.println("6. Assign a Professor to a course (press 6)");
            System.out.print("Enter your choice: ");
            Integer choice = admin_sc.nextInt();
            if (choice == -1) {
                return;
            } else if (choice == 1) {
                while (true) {
                    System.out.println("Enter -1 to reutnr to previous menu.");
                    System.out.println("Choose your viewing format: ");
                    System.out.println("1. To see all courses (press 1)");
                    System.out.println("2. To see courses by semester (press 2)");
                    System.out.print("Enter your choice: ");
                    Integer format_choice = admin_sc.nextInt();
                    if (format_choice == -1) {
                        break;
                    } else if (format_choice == 1) {
                        Course.display_all_courses();
                        // break;
                    } else if (format_choice == 2) {
                        System.out.print("Enter the Semester: ");
                        Integer sem = admin_sc.nextInt();
                        if (sem == -1) {
                            break;
                        } else if (Course.semester_course_db.containsKey(sem)) {
                            if (Course.semester_course_db.get(sem).size() == 0) {
                                System.out.println("No Course exist for this semester.");
                                break;
                            }
                            else {
                                Course.display_courses_by_semester(sem);
                            }
                        }else {
                            System.out.println("No Course exist for this semester.");
                            break;
                        }
                    } else {
                        System.out.println("Invalid Choice!");
                    }
                    System.out.print("Wish to see all details of any particular course? (y/n)");
                    String course_choice = admin_sc.next();
                    if (course_choice.equals("y") || course_choice.equals("Y")) {
                        System.out.print("Enter the course id: ");
                        String course_id = admin_sc.next();
                        Course.display_course_details(course_id);
                    }
                }
            } else if (choice == 2){
                System.out.print("Enter the course id to see details: ");
                String temp = admin_sc.next();
                if (Course.course_db.containsKey(temp)) {
                    Course.display_course_details(temp);
                }
            } else if (choice == 3) {
                Professor prof_to_be_assigned = assign_professor_to_course();
                if (prof_to_be_assigned != null) {
                    Course.create_course(prof_to_be_assigned);
                }
            } else if (choice == 4) {
                System.out.print("Enter the course id to be deleted: ");
                String course_id_to_delete = admin_sc.next();
                if (Course.course_db.containsKey(course_id_to_delete)) {
                    Course.delete_course(course_id_to_delete);
                } else {
                    System.out.println("Course does not exist.");
                }
            } else if (choice == 5) {
                System.out.print("Enter the course id to updating course: ");
                String course_id_to_update = admin_sc.next();
                if (Course.course_db.containsKey(course_id_to_update)) {
                    Course.update_course(course_id_to_update);
                } else {
                    System.out.println("Course does not exist.");
                }

            } else if (choice == 6) {
                System.out.print("Enter the course id to assign a professor: ");
                String course_id_to_assign = admin_sc.next();
                if (Course.course_db.containsKey(course_id_to_assign)) {
                    Professor prof_to_be_assigned = assign_professor_to_course();
                    if (prof_to_be_assigned != null) {
                        Course.course_db.get(course_id_to_assign).set_course_professor(prof_to_be_assigned);
                    }
                } else {
                    System.out.println("Course does not exist.");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    static Professor assign_professor_to_course() {
        System.out.print("Enter Professor expertise (Mention Department): ");
        String department = admin_sc.next();
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
            String prof_id = admin_sc.next();
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
            System.out.println("What do you want to do?");
            System.out.println("1. View or Update the details of a student (press 1)");
            System.out.println("2. Add a new student (press 2)");
            System.out.println("3. Remove a student (press 3)");
            System.out.println("4. Pass a particular student (press 4)");
            System.out.println("5. Pass all eligible students (press 5)");
            System.out.print("Enter your choice: ");
            Integer choice = admin_sc.nextInt();
            if (choice == -1) {
                return;
            } if (choice == 2) {
                Student.create_student();
                continue;
            } else if (choice == 5) {
                Student.pass_all_eligible_students();
                continue;
            }
            System.out.print("Enter the student roll number to view the details of that student: ");
            Integer student_roll=admin_sc.nextInt();
            if (!Student.student_db.containsKey(student_roll)) {
                System.out.println("No student found with the given roll number!");
                continue;
            }
            Student student=Student.student_db.get(student_roll);
            if (choice == 1) {
                    student.show_details();
                    System.out.print("Do you want to update the student details? (Y/N)");
                    String update_choice = admin_sc.next();
                    if (update_choice.equals("Y") || update_choice.equals("y")) {
                        student.update_detials();
                    }    
            } else if (choice == 3) {
                    student.remove_student();
            } else if (choice == 4) {
                    student.get_to_next_semester(true);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
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
            // System.out.println("8. ");
            System.out.print("Enter your choice: ");
            Integer choice = admin_sc.nextInt();
            if (choice == -1) {
                return;
            } else if (choice == 1) {
                Complaint.view_all_complaints();
            } else if (choice == 2) {
                Complaint.view_all_complaints(true);
            } else if (choice == 3) {
                Complaint.view_all_complaints(false);
            } else if (choice == 4) {
                System.out.print("Enter the student roll number: ");
                Integer student_roll = admin_sc.nextInt();
                if (Student.student_db.containsKey(student_roll)) {
                    Student student = Student.student_db.get(student_roll);
                    Complaint.view_all_complaints(student);
                } else {
                    System.out.println("No student found with the given roll number.");
                }
            } else if (choice == 5) {
                Complaint.view_all_complaints(this);
            } else if (choice == 6){
                System.out.print("Enter the complaint id: ");
                Integer complaint_id = admin_sc.nextInt();
                if (Complaint.complaint_db.containsKey(complaint_id)){
                    Complaint complaint = Complaint.complaint_db.get(complaint_id);
                    complaint.show_complaint_status();
                } else {
                    System.out.println("No complaint found with the given complaint id.");
                }
            } else if (choice == 7){
                System.out.print("Enter the complaint id: ");
                Integer complaint_id = admin_sc.nextInt();
                if (Complaint.complaint_db.containsKey(complaint_id)){
                    Complaint complaint = Complaint.complaint_db.get(complaint_id);
                    System.out.print("Enter the response: ");
                    String response = admin_sc.next();
                    complaint.resolve_complaint(response);
                } else {
                    System.out.println("No complaint found with the given complaint id.");
                }
            // } else if (choice == 8){
            // } else if (){
            }else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    } 
}
