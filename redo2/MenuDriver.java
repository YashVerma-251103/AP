package redo2;
// package redo1;

import java.util.Scanner;

public class MenuDriver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Initialize data before starting the application
        DataInitialization.initializeData();

        System.out.println("Welcome to the University Management System");

        while (!exit) {
            System.out.println("\nPlease select your role:");
            System.out.println("1. Login as Student");
            System.out.println("2. Login as Professor");
            System.out.println("3. Login as Administrator");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    studentLogin(scanner);
                    break;
                case 2:
                    professorLogin(scanner);
                    break;
                case 3:
                    adminLogin(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void studentLogin(Scanner scanner) {
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Student student = Student.student_db.values().stream()
                .filter(s -> s.get_email().equals(email) && s.get_password().equals(password))
                .findFirst()
                .orElse(null);

        if (student != null) {
            System.out.println("Welcome, " + student.get_name());
            studentMenu(student, scanner);
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    private static void professorLogin(Scanner scanner) {
        System.out.print("Enter professor email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Professor professor = Professor.professor_db.values().stream()
                .filter(p -> p.get_email().equals(email) && p.get_password().equals(password))
                .findFirst()
                .orElse(null);

        if (professor != null) {
            System.out.println("Welcome, Professor " + professor.get_name());
            professorMenu(professor, scanner);
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    private static void adminLogin(Scanner scanner) {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        Admin admin = Admin.admin_db.values().stream()
                .filter(a -> a.get_password().equals(password))
                .findFirst()
                .orElse(null);

        if (admin != null) {
            System.out.println("Welcome, Admin " + admin.get_name());
            adminMenu(admin, scanner);
        } else {
            System.out.println("Invalid admin password.");
        }
    }

    private static void studentMenu(Student student, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Academic Progress");
            System.out.println("5. Submit Complaint");
            System.out.println("6. View Complaint Status");
            System.out.println("7. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    student.view_available_courses();
                    break;
                case 2:
                    student.register_course();
                    break;
                case 3:
                    student.drop_course();
                    break;
                case 4:
                    student.track_academic_progress();
                    break;
                case 5:
                    student.submit_complaint();
                    break;
                case 6:
                    student.see_status_of_particular_complaint();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void professorMenu(Professor professor, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nProfessor Menu:");
            System.out.println("1. Manage Course");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Update Personal Details");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    professor.manage_course();
                    break;
                case 2:
                    professor.view_enrolled_students();
                    break;
                case 3:
                    professor.update_professor();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void adminMenu(Admin admin, Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Manage Student Records");
            System.out.println("3. Handle Complaints");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    admin.manage_course_catalog();
                    break;
                case 2:
                    admin.manage_students();
                    break;
                case 3:
                    admin.handle_complaints();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
