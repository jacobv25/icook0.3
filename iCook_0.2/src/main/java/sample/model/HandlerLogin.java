package sample.model;

import java.sql.SQLException;

/**
 * Class : HandleLogin
 * Description: This class handles the login in LoginMenuController.java
 *
 */
public class HandlerLogin {

    private String username;
    private String password;
    private String message;

    private static HandlerLogin handler = null;

    private HandlerLogin(){
        username = null;
        password = null;
        message = null;
    }

    private HandlerLogin(String s, String p){
        username = s;
        password = p;
    }

    public static HandlerLogin getHandler(String u, String p){
        if(handler == null) {
            handler = new HandlerLogin(u,p);
        }
        return handler;
    }
    public static HandlerLogin getHandler(){
        if(handler == null) {
            handler = new HandlerLogin();
        }
        return handler;
    }

    /**********************************************
     * @return
     * @throws SQLException
     * @throws LoginFailedException
     *********************************************/
    public boolean requestLogin() throws SQLException, LoginFailedException {

        //If login failed, LoginFailed Exception will  be thrown
        MySQLConnector.requestLogin(username, password);
        //Otherwise, login was successful, so return true.
        return true;
    }

    public boolean requestLogin(String u, String p) throws SQLException, LoginFailedException {

        //If login failed, LoginFailed Exception will  be thrown
        MySQLConnector.requestLogin(u, p);
        //Otherwise, login was successful, so return true.
        return true;
    }


}
