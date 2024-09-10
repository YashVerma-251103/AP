package Assignment1;

import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

public class main {
    /*
     * Required Work Flow
     * start application
     * role-based interface
     * exit application
     */
    static Scanner sc = new Scanner(System.in);

    <T> void sign_up(T user, String email, String password) {
        user.set_email(email);
        user.set_password(password);
    }

    <T> Boolean login(T user, String username, String password) {
        return true;
    }

    public static void main(String[] args) {
        Boolean exit = false;
        while (true) {
            System.out.print("Who are you?\n1. Administrator (Press 1)\n2. Professor (Press 2)\n3. Student(Press 3)\n4. Exit (Press 4)\n");
            Integer role = sc.nextInt();
            if (role == 1) {
                // Administrator
                System.out.println("Welcome Administrator");
                Administrator admin = new Administrator();
                break;
            } else if (role == 2) {
                // Professor
                System.out.println("Welcome Professor");
                Professor prof = new Professor();
                break;
            } else if (role == 3) {
                // Student
                System.out.println("Welcome Student");
                Student student = new Student();
                break;
            } else if (role == 4) {
                // Exit
                System.out.println("Exiting...");
                exit = true;
                break;
            } else {
                System.out.println("Invalid Input\n\n");
            }
        }
        if(exit) {
            System.exit(1);
        }
        
    }
}
