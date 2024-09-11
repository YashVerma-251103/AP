package Assignment1;
import java.util.*;


public class Driver {
    /*
     * Required Work Flow
     * start application
     * role-based interface
     * exit application
     */
    static Scanner sc = new Scanner(System.in);

    // <T> void sign_up(T user, String email, String password) {
    // user.set_email(email);
    // user.set_password(password);
    // }

    <T> Boolean login(T user, String username, String password) {
        Boolean success = false;


        return success;
    }

    public static void main(String[] args) {
        Integer role;
        while (true) { // Role deciding loop.
            System.out.print(
                    "Who are you?\n1. Administrator (Press 1)\n2. Professor (Press 2)\n3. Student(Press 3)\n4. Exit (Press 4)\n");
            role = sc.nextInt();
            if (role >= 1 && role <= 4) {
                break;
            } else {
                System.out.println("Invalid Input\n\n");
            }
        }

        // making
        if (role == 4) {
            // Exit
            System.out.println("Exiting...");
            System.exit(1);
        } else {
            Boolean login = false;
            if (role == 1) {
                // Administrator
                System.out.println("Welcome Administrator");
                Administrator user = new Administrator();
                break;
            } else if (role == 2) {
                // Professor
                System.out.println("Welcome Professor");
                Professor user = new Professor();
                break;
            } else {
                // Student
                System.out.println("Welcome Student");
                Student user = new Student();
                break;
            }
            login = login(user, "admin", "admin");


        }
    }
}
