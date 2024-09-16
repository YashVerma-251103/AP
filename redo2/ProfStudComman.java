package redo2;
// package redo1;

// public class ProfStudComman extends CommonUser { // left
    
// }
public class ProfStudComman extends CommonUser {

    // Shared method to view personal information
    public void view_personal_info() {
        System.out.println("Email: " + get_email());
        // Password is usually not printed for security reasons
    }

    // Shared method to update email
    public void update_email() {
        System.out.print("Enter new email: ");
        String new_email = sc.nextLine();
        set_email(new_email);
        System.out.println("Email updated successfully.");
    }

    // You can add other shared functionalities here for Professor and Student
}
