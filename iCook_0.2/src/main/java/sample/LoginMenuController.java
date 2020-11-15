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
import javafx.stage.Stage;
import sample.model.CryptographerAES;
import sample.model.HandlerLogin;
import sample.model.LoginFailedException;
import sample.model.MySQLConnector;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ResourceBundle;

public class LoginMenuController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            logoLoginImageView = new ImageView(getClass().getClassLoader().getResources("/images/icook_login_logo.png").toString());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    private void clearTextFields(){
        usernameTextField.clear();
        passwordTextField.clear();
    }

    public void buttonPushed(ActionEvent event) throws SQLException, IOException {


        if(event.getSource() == requestLoginButton){
            hideErrorMessage();
            requestLoginButtonPushed();
        }
        else if(event.getSource() == registerButton ||
                event.getSource() == createAccountButton){
            registerButtonPushed(event);
        }
        else if(event.getSource() == cancelRegisterButton){
            hideErrorMessage();
            hideRegisterMenuButtons();
            showLoginMenuButtons();
            clearTextFields();
        }
        else if(event.getSource() == guestLoginButton){
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

    private void requestLoginButtonPushed(){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        boolean loginSuccess;
        try {
            //send encrypted password and see if it matches
            CryptographerAES aes = new CryptographerAES(DEFAULT_KEY);
            loginSuccess =  HandlerLogin.getHandler().requestLogin(username, aes.encrypt(password));
            System.out.println("success="+loginSuccess);
            if(loginSuccess){
                System.out.println("login success!");
                loginMessageLabel.setVisible(true);
                hideErrorMessage();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LoginFailedException e) {
            displayErrorMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerButtonPushed(ActionEvent event) throws SQLException {

        if(event.getSource() == registerButton){
            clearTextFields();
            hideLoginMenuButtons();
            hideErrorMessage();
            showRegisterMenuButtons();
        }
        else if (event.getSource() == createAccountButton) {
            //send data to MySQLConnector
            //and see if register was successful
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();
            System.out.println("username=" +username);
            System.out.println("password=" +password);
            try {
                if (password.length() < MIN_PASSWORD_LENGTH){
                    displayErrorMessage("Password too short.");
                }
                else if (username.equals("") || password.equals("")) {
                    displayErrorMessage("Invalid entry. Try again");
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
                }
            }
            catch (SQLIntegrityConstraintViolationException e){
                e.printStackTrace();
                displayErrorMessage("Username already exists.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void displayErrorMessage(String msg){
        errorMessageLabel.setVisible(true);
        errorMessageLabel.setText(msg);
    }
    private void hideErrorMessage(){
        errorMessageLabel.setVisible(false);
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
