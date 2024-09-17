package Assignment1;

public abstract class User {
    private String email;
    private String password;

    String get_email(){
        return this.email;
    }
    String get_password(){
        return this.password;
    }
    void set_email(String email) {
        this.email = email;
    }
    void set_password(String password) {
        this.password = password;
    }
    void sign_up(String email, String password) {
        this.set_email(email);
        this.set_password(password);
    }

}
