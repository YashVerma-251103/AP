// package redo1;
// public class MenuDriver { // left
//     public static void main(String[] args) {
//         Admin ad = Admin.create_admin();
//         Student st = Student.create_student();
//         Professor pr = Professor.create_professor();
//         Course cr = Course.create_course(pr);
//         Complaint cm = Complaint.create_complaint(st,"complaint");
//     }
// }




package redo1;

import java.util.Scanner;

public class UniversitySystemDriver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DataSeeder.seedData();  // Load predefined data

        while (true) {
            System.out.println("Welcome to the University Course Registration System");
            System.out.println("1. Enter the Application");
            System.out.println("2. Exit the Application");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();

            if (mainChoice == 2) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            } else if (mainChoice == 1) {
                System.out.println("Login as:");
                System.out.println("1. Student");
                System.out.println("2. Professor");
                System.out.println("3. Administrator");
                System.err.println("4. Exit");
                System.out.print("Enter your choice: ");
                int roleChoice = sc.nextInt();

                if (roleChoice == 1) {
                    studentLogin(sc);
                } else if (roleChoice == 2) {
                    professorLogin(sc);
                } else if (roleChoice == 3) {
                    adminLogin(sc);
                } else if (roleChoice == 4) {
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice! Please try again.");
                }
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        sc.close();
    }

    public static void studentLogin(Scanner sc) {
        System.out.print("Enter Roll Number: ");
        int rollNumber = sc.nextInt();
        Student student = Student.student_db.get(rollNumber);
        if (student != null) {
            System.out.println("Welcome, " + student.get_name() + "!");
            System.out.print("Enter Student Password: ");
            if (!student.get_password().equals(sc.next())) {
                System.out.println("Incorrect password! Please try again.");
                return;
            }
            studentInterface(student, sc);
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void professorLogin(Scanner sc) {
        System.out.print("Enter Professor ID: ");
        String profId = sc.next();
        Professor professor = Professor.professor_db.get(profId);
        if (professor != null) {
            System.out.println("Welcome, " + professor.get_name() + "!");
            System.out.print("Enter Professor Password: ");
            if (!professor.get_password().equals(sc.next())) {
                System.out.println("Incorrect password! Please try again.");
                return;
            }
            professorInterface(professor, sc);
        } else {
            System.out.println("Professor not found.");
        }
    }

    public static void adminLogin(Scanner sc) {
        System.out.print("Enter Admin ID: ");
        String adminId = sc.next();
        Admin admin = Admin.admin_db.get(adminId);
        if (admin != null) {
            System.out.println("Welcome, " + admin.get_name() + "!");
            System.out.print("Enter Admin Password: ");
            if (!admin.get_password().equals(sc.next())) {
                System.out.println("Incorrect password! Please try again.");
                return;
                
            }
            adminInterface(admin, sc);
        } else {
            System.out.println("Admin not found.");
        }
    }

    public static void studentInterface(Student student, Scanner sc) {
        while (true) {
            int i=1;
            System.out.println((i++) + ". Logout");
            System.out.println((i++) + ". Show my details");
            System.out.println((i++) + ". View Schedule");
            System.out.println((i++) + ". View Courses Offered this Semester");
            System.out.println((i++) + ". View Completed Courses");
            System.out.println((i++) + ". View Registered Course");
            System.out.println((i++) + ". Register for Course");
            System.out.println((i++) + ". Drop Course");
            System.out.println((i++) + ". Submit Complaint");
            System.out.println((i++) + ". View My Complaints");
            System.out.println((i++) + ". View Particular Complaint");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Logging out...");
                break;
            } else if (choice == 2) {
                student.view_schedule();
            } else if (choice == 3) {
                student.view_available_courses();
            } else if (choice == 4) {
                student.show_completed_courses();
            } else if (choice == 5) {
                student.view_registered_courses();
            } else if (choice == 6) {
                student.register_course();
            } else if (choice == 7) {
                student.drop_course();
            } else if (choice == 8) {
                student.submit_complaint();
            } else if (choice == 9) {
                student.see_complaint();
            } else if (choice == 10) {
                student.see_status_of_particular_complaint();
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void professorInterface(Professor professor, Scanner sc) {
        while (true) {
            int i = 1;
            System.out.println((i++) + ". Logout");
            System.out.println((i++) + ". View My Details");
            System.out.println((i++) + ". View My Course");
            System.out.println((i++) + ". Manage My Course");
            System.out.println((i++) + ". View Students in My Course");
            System.out.println((i++) + ". Assign Grade to Student");
            System.out.println((i++) + ". Pass Semester for Assigned Course");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Logging out...");
                break;
            } else if (choice == 2) {
                professor.view_my_details();
            } else if (choice == 3) {
                professor.view_my_course();
            } else if (choice == 4) {
                professor.manage_course();
            } else if (choice == 5) {
                professor.view_enrolled_students();
            } else if (choice == 6) {
                professor.assign_grades_to_student();
            } else if (choice == 7) {
                professor.pass_semester();
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void adminInterface(Admin admin, Scanner sc) {
        while (true) {
            int i = 1;
            System.out.println((i++) + ". Logout");
            System.out.println((i++) + ". Manage Courses");
            System.out.println((i++) + ". Manage Students");
            System.out.println((i++) + ". Handle Complaints");
            System.out.println((i++) + ". Create New Admin");
            System.out.println((i++) + ". Create New Professor");
            System.out.println((i++) + ". Create New Student");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("Logging out...");
                break;
            } else if (choice == 2) {
                admin.manage_course_catalog();
            } else if (choice == 3) {
                admin.manage_students();
            } else if (choice == 4) {
                admin.handle_complaints();
            } else if (choice == 5) {
                Admin.create_admin();
            } else if (choice == 6) {
                Professor.create_professor();
            } else if (choice == 7) {
                Student.create_student();
            }else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
