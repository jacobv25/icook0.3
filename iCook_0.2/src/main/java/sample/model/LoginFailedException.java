package sample.model;

/**
 * Exception: Login Failed
 * After thinking about it, maybe this class is kinda pointless
 */
public class LoginFailedException extends Exception {

    public LoginFailedException(){
        super("Username or password\nwas incorrect.");
    }

    public LoginFailedException(String msg){
        super(msg);
    }
}
