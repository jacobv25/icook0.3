package sample.model;

import java.sql.SQLException;

public class HandlerLogin {

    private String username;
    private String password;

    private static HandlerLogin handler = null;

    private HandlerLogin(){
        username = null;
        password = null;
    }

    private HandlerLogin(String s, String p){
        username = s;
        password = p;
    }

    public static HandlerLogin getHandler(){
        if(handler == null) {
            handler = new HandlerLogin();
        }
        System.out.println("New Instance created\n");
        return handler;
    }

    /**********************************************
     *
     * @param u
     * @param p
     * @return
     * @throws SQLException
     * @throws LoginFailedException
     *********************************************/
    public boolean requestLogin(String u, String p) throws SQLException, LoginFailedException {

        //If login failed, LoginFailed Exception will  be thrown
        MySQLConnector.requestLogin(u, p);
        //Otherwise, login was successful, so return true.
        return true;
    }


}
