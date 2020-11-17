package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//import main.java.sample.model.Recipe;
//import main.java.sample.model.Ingredient;
import javafx.stage.Stage;
import sample.model.*;

import static sample.model.Ingredient.FoodCategory.*;


public class IngredientMenuController implements Initializable {


    @FXML GridPane grid;
    @FXML ListView proteinListView;
    @FXML ListView carbListView;
    @FXML ListView veggieListView;
    @FXML ListView sauceListView;


    ArrayList<Recipe> allRecipes;
    ArrayList<Ingredient> allIngredients;
    ArrayList<Ingredient> proteinIngredients;
    ArrayList<Ingredient> carbIngredients;
    ArrayList<Ingredient> veggieIngredients;
    ArrayList<Ingredient> sauceIngredients;

    @FXML FlowPane proteinPane;
    @FXML FlowPane carbPane;
    @FXML FlowPane veggiePane;
    @FXML FlowPane saucePane;


    @FXML CheckBox checkBox;


    /*********************************************************************
     * Get the chosen ingredient list back from ChooseRecipeMenuController
     * and update the check boxes and ListViews
     * Function assumes all CheckBoxes and ListViews are empty
     *********************************************************************/
    public void handleCheckBoxes(List<Ingredient> ingredientList){
        //handle check boxes
        HandlerCheckBox.repopulateCheckBoxes(proteinPane, ingredientList);
        HandlerCheckBox.repopulateCheckBoxes(carbPane, ingredientList);
        HandlerCheckBox.repopulateCheckBoxes(veggiePane, ingredientList);
        HandlerCheckBox.repopulateCheckBoxes(saucePane, ingredientList);
        //update the List views
    }

    public void repopulateTextViews(List<Ingredient> list){
        for (int i=0; i < list.size(); i++) {
            if (list.get(i).getCategory() == PROTEIN) {
//                   System.out.println("Ingredient is a protein !!");
                proteinListView.getItems().add(list.get(i).getName());
            }
            else if (list.get(i).getCategory() == CARB) {
//                   System.out.println("Ingredient is a carb !!");
                carbListView.getItems().add(list.get(i).getName());
            }
            else if (list.get(i).getCategory() == VEGGIE) {
//                   System.out.println("Ingredient is a veggie !!");
                veggieListView.getItems().add(list.get(i).getName());
            }
            else if (list.get(i).getCategory() == SAUCE) {
//                   System.out.println("Ingredient is a sauce !!");
                sauceListView.getItems().add(list.get(i).getName());
            }
        }
    }


    /********************************************************
     * When this button is pressed it will change the scene
     * F
     ****************************************************/
    public void generateRecipesButtonPushed(ActionEvent event) throws IOException {

        //Parent chooseRecipeParent = FXMLLoader.load(getClass().getClassLoader().getResource("choose_recipe_menu.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("choose_recipe_menu.fxml"));
        Parent chooseRecipeParent = loader.load();
        Scene chooseRecipeScene = new Scene(chooseRecipeParent);

        //access Controller and add a method NOT NEEDED ANYMORE
        //ChooseRecipeMenuController controller = loader.getController();
        //controller.initData(getIngredientsFromListViews());

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(chooseRecipeScene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        proteinIngredients = new ArrayList<>();
        carbIngredients = new ArrayList<>();
        veggieIngredients = new ArrayList<>();
        sauceIngredients = new ArrayList<>();

        //==== getting data using mySQL ===========
        try {
            allIngredients = new ArrayList<>(MySQLConnector.getAllIngredients());
            allRecipes = new ArrayList<>(MySQLConnector.getAllRecipes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //====== Move ingredients from allIngredient to separate ArrayLists ============
        //Iterate through all ingredients, and one by one place each ingredient into its appropriate
        //categorical ArrayList
        for(int i=0; i < allIngredients.size(); i++){
            switch (allIngredients.get(i).getCategory()){
                case PROTEIN:
                    proteinIngredients.add(allIngredients.get(i));
                    break;
                case CARB:
                    carbIngredients.add(allIngredients.get(i));
                    break;
                case VEGGIE:
                    veggieIngredients.add(allIngredients.get(i));
                    break;
                case SAUCE:
                    sauceIngredients.add(allIngredients.get(i));
                    break;
            }
        }
        //========= adding ingredients to correct Tile Pane =============
        checkBox = null;
        for (Ingredient item : proteinIngredients) {
            checkBox = new CheckBox(item.getName());
            //Set each check box's On Action Method
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //handleProteinCheckBoxes(event);
                    System.out.println("event!");
                    CheckBox test = (CheckBox) event.getSource();
                    System.out.println("checked?=" + test.isSelected());
                    HandlerCheckBox.handleCheckBoxes(event, proteinPane, proteinListView);
                }
            });
            System.out.println("Added?=" + proteinPane.getChildren().add(checkBox));
        }
        for (Ingredient item : carbIngredients) {
            checkBox = new CheckBox(item.getName());
            //Set each check box's On Action Method
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    HandlerCheckBox.handleCheckBoxes(event, carbPane, carbListView);
                }
            });
            System.out.println("Added?=" + carbPane.getChildren().add(checkBox));
        }
        for (Ingredient item : veggieIngredients) {
            checkBox = new CheckBox(item.getName());
            //Set each check box's On Action Method
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    HandlerCheckBox.handleCheckBoxes(event, veggiePane, veggieListView);
                }
            });
            System.out.println("Added?=" + veggiePane.getChildren().add(checkBox));
        }
        for (Ingredient item : sauceIngredients) {
            checkBox = new CheckBox(item.getName());
            //Set each check box's On Action Method
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    HandlerCheckBox.handleCheckBoxes(event, saucePane, sauceListView);
                }
            });
            System.out.println("Added?=" + saucePane.getChildren().add(checkBox));
        }
    }


}
