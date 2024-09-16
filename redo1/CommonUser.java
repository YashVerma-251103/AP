package redo1;

import java.util.Scanner;

public class CommonUser implements User {
    public static Scanner sc = new Scanner(System.in);
    private String email;
    private String password;
    
    void set_user(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void set_email(String email) {
        this.email = email;
    }
    public void set_password(String password) {
        this.password = password;
    }
    public String get_email() {
        return email;
    }
    public String get_password() {
        return password;
    }

}