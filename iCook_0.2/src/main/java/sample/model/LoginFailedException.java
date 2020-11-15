package sample.model;

public class LoginFailedException extends Exception {

    public LoginFailedException(){
        super("Username or password was incorrect.");
    }

    public LoginFailedException(String msg){
        super(msg);
    }
}
