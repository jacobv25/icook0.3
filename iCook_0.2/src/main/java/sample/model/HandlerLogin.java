package sample.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class : HandleLogin
 * Description: This class handles the login in LoginMenuController.java
 *
 */
public class HandlerLogin {

    private String username;
    private String password;
    private String message;
    private static boolean isUserLoggedIn;

    private static HandlerLogin handler = null;

    //Check if there is a user logged in
    public static boolean getIsUserLoggedIn() {
        return isUserLoggedIn;
    }
    //Set isUserLoggedIn
    public static void setIsUserLoggedIn(boolean logged_in_flag) {
        HandlerLogin.isUserLoggedIn = logged_in_flag;
    }

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
            handler = new
                    HandlerLogin(u,p);
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
        ResultSet resultSet = MySQLConnector.requestLogin(u, p);
        //Otherwise, login was successful,
        //set logged in flag to UP (true)
        isUserLoggedIn = true;
        //Set Current User data
        //1. Get user data
        String username = resultSet.getString("username");
        int id = resultSet.getInt("userID");

        User.username = username;
        User.id = id;

        //2. Get saved data
        //Get saved ingredient IDs
        List<String> ingredientNames = MySQLConnector.getSavedUserIngredientNamess(id);
        List<Ingredient> savedIngredients = MySQLConnector.getSavedUserIngredient(ingredientNames);
        //currentUser.saveIngredients(savedIngredients);
        User.savedIngredients = new ArrayList<>(savedIngredients);

        return true;
    }


}
