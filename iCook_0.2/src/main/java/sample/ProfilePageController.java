package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.model.Recipe;

import java.util.List;

public class ProfilePageController {

    @FXML ListView ingredientListView;
    @FXML ListView recipeListView;
    @FXML Label welcomeLabel;

    public void populateProfilePage(List<String> ingredientList, List<Recipe> recipeList, String username){

        ingredientListView.getItems().addAll(ingredientList);
        recipeListView.getItems().addAll(recipeList);
        setWelcome(username);
    }

    private void setWelcome(String msg){

        welcomeLabel.setText("Welcome " + msg + "!");
    }
}
