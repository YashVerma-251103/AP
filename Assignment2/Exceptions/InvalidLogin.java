package Assignment2.Exceptions;

public class InvalidLogin extends Exceptions{
    public InvalidLogin(String user){
        super("Invalid Login Credentials for "+user);
    }
}
