package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.model.CryptographerAES;
import sample.model.HandlerLogin;
import sample.model.LoginFailedException;
import sample.model.MySQLConnector;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

/*****************************************************
 * Class: LoginMenuController
 * Description: This class is the controller for login_menu.xml
 *****************************************************/
public class LoginMenuController  {

    private final String DEFAULT_KEY = "123456789abcdefg";
    private final int MIN_PASSWORD_LENGTH = 6;

    @FXML private ImageView logoLoginImageView;

    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;

    @FXML private Button requestLoginButton;
    @FXML private Button registerButton;
    @FXML private Button createAccountButton;
    @FXML private Button cancelRegisterButton;
    @FXML private Button guestLoginButton;

    @FXML private Label errorMessageLabel;
    @FXML private Label loginMessageLabel;


    private void changeSceneToProfilePage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("profile_page.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        //access Controller
        ProfilePageController controller = loader.getController();
        controller.populateProfilePage();

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }
    /**
     * Clears thr login and password TextFields
     */
    private void clearTextFields(){
        usernameTextField.clear();
        passwordTextField.clear();
    }

    /**
     * Checks the event for which button was pressed, then calls
     * the approtiate methods that configure the login menu
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    public void buttonPushed(ActionEvent event) throws SQLException, IOException {


        if(event.getSource() == requestLoginButton){
            hideLoginMessage();
            requestLoginButtonPushed(event);
        }
        else if(event.getSource() == registerButton ||
                event.getSource() == createAccountButton){
            registerButtonPushed(event);
        }
        else if(event.getSource() == cancelRegisterButton){
            hideLoginMessage();
            hideRegisterMenuButtons();
            showLoginMenuButtons();
            clearTextFields();
        }
        else if(event.getSource() == guestLoginButton){
            //Set logged in flag to false
            HandlerLogin.setIsUserLoggedIn(false);
            //Load scene
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("ingredients_menu.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);

            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(scene);
            window.show();
        }

    }

    /**
     * When loginButton is pushed, the data from the two TextFields, usernameTextField and passwordTextField
     * is collected and passed to a Handler object and anc check to see if access is granted.
     */
    private void requestLoginButtonPushed(ActionEvent event){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        boolean loginSuccess;
        try {
            //send encrypted password and see if it matches
            CryptographerAES aes = new CryptographerAES(DEFAULT_KEY);
            loginSuccess =  HandlerLogin.getHandler().requestLogin(username, aes.encrypt(password));
            //===== The code below is how I think Dr. Fan wants is done but when I try this way, correct username and passwords get rejected =====
            //HandlerLogin handler = HandlerLogin.getHandler(username, password);
            //loginSuccess = handler.requestLogin();
            if(loginSuccess){

                showLoginMessage("Login successful", true);
                changeSceneToProfilePage(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LoginFailedException e) {
            showLoginMessage(e.getMessage(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The event is checked to see which button was pushed.
     * IF registerButton was pushed, change the menu
     * IF createAccountButton was pushed, attempt to create an account
     * @param event
     * @throws SQLException
     */
    private void registerButtonPushed(ActionEvent event) throws SQLException {

        if(event.getSource() == registerButton){
            clearTextFields();
            hideLoginMenuButtons();
            hideLoginMessage();
            showRegisterMenuButtons();
        }
        else if (event.getSource() == createAccountButton) {
            //send data to MySQLConnector
            //and see if register was successful
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            System.out.println("username=" +username);
            System.out.println("password=" +password);
            //==== I wonder if the code below should go inside HandlerLogin ====
            try {
                if (password.length() < MIN_PASSWORD_LENGTH){
                    showLoginMessage("Password too short.", false);
                }
                else if (username.equals("") || password.equals("")) {
                    showLoginMessage("Invalid entry. Try again", false);
                } else {
                    //set all lowercase so caps doesn't matter when entering username
                    username = username.toLowerCase();
                    //encrypt password
                    CryptographerAES aes = new CryptographerAES(DEFAULT_KEY);
                    MySQLConnector.registerUser(username, aes.encrypt(password));
                    //hide the register buttons and show the login buttons
                    hideRegisterMenuButtons();
                    showLoginMenuButtons();
                    clearTextFields();
                    showLoginMessage("Registration\nsuccessful!", true);
                }
            }
            catch (SQLIntegrityConstraintViolationException e){
                e.printStackTrace();
                showLoginMessage("Username already exists.", false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Displays a message informing the user the result of the action.
     * @param msg the message to be shown
     * @param success if the request was approved or not
     */
    private void showLoginMessage(String msg, boolean success){
        loginMessageLabel.setVisible(true);
        loginMessageLabel.setText(msg);
        if (success){
            loginMessageLabel.setTextFill(Color.GREEN);
        }
        else { loginMessageLabel.setTextFill(Color.RED); }
    }


    private void hideLoginMessage(){
        loginMessageLabel.setVisible(false);
    }


    private void showLoginMenuButtons(){
        requestLoginButton.setVisible(true);
        guestLoginButton.setVisible(true);
        registerButton.setVisible(true);
    }
    private void hideLoginMenuButtons(){
        requestLoginButton.setVisible(false);
        guestLoginButton.setVisible(false);
        registerButton.setVisible(false);
    }
    private void showRegisterMenuButtons(){
        createAccountButton.setVisible(true);
        cancelRegisterButton.setVisible(true);
    }
    private void hideRegisterMenuButtons(){
        createAccountButton.setVisible(false);
        cancelRegisterButton.setVisible(false);
    }

}
