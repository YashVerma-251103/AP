package Assignment2;

import java.util.Scanner;

import Assignment2.Exceptions.InvalidLogin;

public class ERP {
    public static Scanner main_sc = new Scanner(System.in);
    public static void main(String[] args) {
        DataSeeder.seedData();  // Load predefined data

        while (true) {
            System.out.println("Welcome to the IIITD Course Registration System");
            System.out.println("1. Enter the Application");
            System.out.println("2. Exit the Application");
            System.out.print("Enter your choice: ");
            int mainChoice = main_sc.nextInt();

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
                int roleChoice = main_sc.nextInt();

                if (roleChoice == 1) {
                    studentLogin(main_sc);
                } else if (roleChoice == 2) {
                    professorLogin(main_sc);
                } else if (roleChoice == 3) {
                    adminLogin(main_sc);
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
        main_sc.close();
    }
    
    // public static void studentLogin(Scanner main_sc) {
    //     System.out.print("Enter Roll Number: ");
    //     int rollNumber = main_sc.nextInt();
    //     try {
    //         Student student = Student.student_db.get(rollNumber);
    //         if (student != null) {
    //             System.out.println("Welcome, " + student.get_name() + "!");
    //             System.out.print("Enter Student Password: ");
    //             try {
    //                 if (!student.get_password().equals(main_sc.next())) {
    //                     throw new InvalidLogin("Student ("+student.get_name()+" | "+student.get_student_roll_number()+") Password");
    //                 }
    //             } catch (Exception e) {
    //                 System.out.println(e.getMessage());
    //                 return;
    //             }
    //             studentInterface(student, main_sc);
    //         } else {
    //             throw new InvalidLogin("student");
    //         }
    //     } catch (InvalidLogin e) {
    //         System.out.println(e.getMessage());
    //     } 
    // }
    public static void studentLogin(Scanner main_sc) {
        System.out.print("Enter Roll Number: ");
        int rollNumber = main_sc.nextInt();
        try {
            TAs ta = TAs.ta_db.get(rollNumber);
            if (ta!=null) {              
                System.out.println("Welcome, " + ta.get_name() + "!");
                System.out.print("Enter Student Password: ");
                try {
                    if (!ta.get_password().equals(main_sc.next())) {
                        throw new InvalidLogin("Student ("+ta.get_name()+" | "+ta.get_student_roll_number()+") Password");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
                taInterface(ta, main_sc);                
            } else {
                Student student = Student.student_db.get(rollNumber);
                if (student != null) {
                    System.out.println("Welcome, " + student.get_name() + "!");
                    System.out.print("Enter Student Password: ");
                    try {
                        if (!student.get_password().equals(main_sc.next())) {
                            throw new InvalidLogin("Student ("+student.get_name()+" | "+student.get_student_roll_number()+") Password");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                    studentInterface(student, main_sc);
                } else {
                    throw new InvalidLogin("student");
                }
            }
        } catch (InvalidLogin e) {
            System.out.println(e.getMessage());
        } 
    }

    public static void professorLogin(Scanner main_sc) {
        System.out.print("Enter Professor ID: ");
        String profId = main_sc.next();
        try {
            Professor professor = Professor.professor_db.get(profId);
            if (professor != null) {
                System.out.println("Welcome, " + professor.get_name() + "!");
                System.out.print("Enter Professor Password: ");
                try {
                    if (!professor.get_password().equals(main_sc.next())) {
                        throw new InvalidLogin("Professor ("+professor.get_name()+" | "+professor.get_professor_id()+") Password");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
                professorInterface(professor, main_sc);
            } else {
                throw new InvalidLogin("Professor");
            }
        } catch (InvalidLogin e) {
            System.out.println(e.getMessage());
        } 
    }

    public static void adminLogin(Scanner main_sc) {
        System.out.print("Enter Admin ID: ");
        String adminId = main_sc.next();
        try {
            Admin admin = Admin.admin_db.get(adminId);
            if (admin != null) {
                System.out.println("Welcome, " + admin.get_name() + "!");
                System.out.print("Enter Admin Password: ");
                try {
                    if (!admin.get_password().equals(main_sc.next())) {
                        throw new InvalidLogin("Admin ("+admin.get_name()+" | "+admin.get_admin_id()+") Password");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return;
                }
                adminInterface(admin, main_sc);
            } else {
                throw new InvalidLogin("Admin");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void studentInterface(Student student, Scanner main_sc) {
        while (true) {
            int i=1;
            System.out.println((i++) + ". Logout"); 
            System.out.println((i++) + ". Show my details");
            System.out.println((i++) + ". View Schedule");
            System.out.println((i++) + ". View Courses Offered this Semester");
            System.out.println((i++) + ". View Completed Courses");
            System.out.println((i++) + ". View Registered Course");
            System.out.println((i++) + ". View Dropped Courses");
            System.out.println((i++) + ". Register for Course");
            System.out.println((i++) + ". Drop Course");
            System.out.println((i++) + ". Submit Complaint");
            System.out.println((i++) + ". View My Complaints");
            System.out.println((i++) + ". View Particular Complaint");
            System.out.print("Enter your choice: ");
            int choice = main_sc.nextInt();
            if (choice == 1) {
                System.out.println("Logging out...");
                break;
            } else if (choice == 2) {
                student.show_details();
            } else if (choice == 3) {
                student.view_schedule();
            } else if (choice == 4) {
                student.view_available_courses();
            } else if (choice == 5) {
                student.show_completed_courses();
            } else if (choice == 6) {
                student.view_registered_courses();
            } else if (choice == 7) {
                student.view_dropped_courses();
            } else if (choice == 8) {
                student.register_course();
            } else if (choice == 9) {
                student.drop_course();
            } else if (choice == 10) {
                student.submit_complaint();
            } else if (choice == 11) {
                student.see_complaint();
            } else if (choice == 12) {
                student.see_status_of_particular_complaint();
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void taInterface(TAs student, Scanner main_sc) {
        while (true) {
            int i=1;
            System.out.println((i++) + ". Logout"); 
            System.out.println((i++) + ". Show my details");
            System.out.println((i++) + ". View Schedule");
            System.out.println((i++) + ". View Courses Offered this Semester");
            System.out.println((i++) + ". View Completed Courses");
            System.out.println((i++) + ". View Registered Course");
            System.out.println((i++) + ". View Dropped Courses");
            System.out.println((i++) + ". Register for Course");
            System.out.println((i++) + ". Drop Course");
            System.out.println((i++) + ". Submit Complaint");
            System.out.println((i++) + ". View My Complaints");
            System.out.println((i++) + ". View Particular Complaint");
            System.out.println((i++) + ". View Teaching Course Detaits");
            System.out.println((i++) + ". Manage Students in Teaching Course");
            // System.out.println((i++) + ". ");
            System.out.print("Enter your choice: ");
            int choice = main_sc.nextInt();
            if (choice == 1) {
                System.out.println("Logging out...");
                break;
            } else if (choice == 2) {
                student.show_details();
            } else if (choice == 3) {
                student.view_schedule();
            } else if (choice == 4) {
                student.view_available_courses();
            } else if (choice == 5) {
                student.show_completed_courses();
            } else if (choice == 6) {
                student.view_registered_courses();
            } else if (choice == 7) {
                student.view_dropped_courses();
            } else if (choice == 8) {
                student.register_course();
            } else if (choice == 9) {
                student.drop_course();
            } else if (choice == 10) {
                student.submit_complaint();
            } else if (choice == 11) {
                student.see_complaint();
            } else if (choice == 12) {
                student.see_status_of_particular_complaint();
            } else if (choice == 13) {
                student.view_course_details();
            } else if (choice == 14) {
                student.manage_student_grades();
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }






    public static void professorInterface(Professor professor, Scanner main_sc) {
        while (true) {
            int i = 1;
            System.out.println((i++) + ". Logout");
            System.out.println((i++) + ". View My Details");
            System.out.println((i++) + ". View My Course");
            System.out.println((i++) + ". Manage My Course");
            System.out.println((i++) + ". View Students in My Course");
            System.out.println((i++) + ". Assign Grade to Student");
            System.out.println((i++) + ". Pass Semester for Assigned Course");
            System.out.println((i++) + ". Update My Details");
            System.out.print("Enter your choice: ");
            int choice = main_sc.nextInt();
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
            } else if (choice == 8) {
                professor.update_professor();
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public static void adminInterface(Admin admin, Scanner main_sc) {
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
            int choice = main_sc.nextInt();
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
