package redo2;
// // package redo1;
// import java.util.Scanner;
// public class CommonUser implements User {
//     public static Scanner sc = new Scanner(System.in);
//     private String email;
//     private String password;    
//     void set_user(String email, String password) {
//         this.email = email;
//         this.password = password;
//     }
//     public void set_email(String email) {
//         this.email = email;
//     }
//     public void set_password(String password) {
//         this.password = password;
//     }
//     public String get_email() {
//         return email;
//     }
//     public String get_password() {
//         return password;
//     }
// }

import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class CommonUser implements User {
    public static Scanner sc = new Scanner(System.in);
    private String email;
    private String password;  // Store hashed password
    
    public void set_user(String email, String password) {
        this.email = email;
        this.password = hashPassword(password);  // Store hashed password
    }

    public void set_email(String email) {
        this.email = email;
    }
    
    public void set_password(String password) {
        this.password = hashPassword(password);  // Store hashed password
    }
    
    public String get_email() {
        return email;
    }

    public String get_password() {
        return password;  // Return the hashed password
    }

    // Utility function to hash the password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
