package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.model.Ingredient;
import sample.model.Recipe;
import sample.model.User;

import java.io.IOException;
import java.util.List;

public class ProfilePageController {

    @FXML private ListView ingredientListView;
    @FXML private ListView recipeListView;
    @FXML private Label welcomeLabel;

    public void addIngredients(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ingredients_menu.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        //access Controller
        IngredientMenuController controller = loader.getController();
        controller.handleCheckBoxes(User.savedIngredients);
        controller.repopulateTextViews(User.savedIngredients);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void populateProfilePage(){

        //User user = User.getUser();
        //ingredientListView.getItems().addAll(user.getSavedIngredients());
        for(Ingredient e: User.savedIngredients) {
            ingredientListView.getItems().add(e.getName());
            //recipeListView.getItems().addAll(user.getSavedRecipes());
            setWelcome(User.username);
        }
    }

    private void setWelcome(String msg){

        welcomeLabel.setText("Welcome " + msg + "!");
    }
}
