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


public class IngredientMenuController implements Initializable {

    private static final int NUM_BUTTON_LINES = 5;
    private static final int BUTTONS_PER_LINE = 3;
    private static final int MAX_INGREDIENTS = 100;
    private static final int MAX_RECIPES = 100;

    @FXML GridPane grid;
    @FXML ListView list1 = new ListView();
    @FXML ListView proteinListView;
    @FXML ListView carbListView;
    @FXML ListView veggieListView;
    @FXML ListView sauceListView;

    @FXML TextArea textArea1;
    String ingredients = "";


    ArrayList<Recipe> allRecipes;
    ArrayList<Recipe> possibleRecipes;
    ArrayList<Ingredient> allIngredients;
    ArrayList<Ingredient> proteinIngredients;
    ArrayList<Ingredient> carbIngredients;
    ArrayList<Ingredient> veggieIngredients;
    ArrayList<Ingredient> sauceIngredients;
    ArrayList<Ingredient> chosenIngredients;

    @FXML FlowPane proteinPane;
    @FXML FlowPane carbPane;
    @FXML FlowPane veggiePane;
    @FXML FlowPane saucePane;

    @FXML Button addIngredientButton;
    @FXML Button displayRecipesButton;
    @FXML Button updateButton;

    @FXML CheckBox checkBox;


    /*********************************************************************
     * Get the chosen ingredient list back from ChooseRecipeMenuController
     * and update the check boxes and ListViews
     * Function assumes all CheckBoxes and ListViews are empty
     *********************************************************************/
    public void initDataFromChooseRecipeMenu(List<Ingredient> ingredientList){
        //handle check boxes
        HandlerCheckBox.repopulateCheckBoxes(proteinPane, ingredientList);
        HandlerCheckBox.repopulateCheckBoxes(carbPane, ingredientList);
        HandlerCheckBox.repopulateCheckBoxes(veggiePane, ingredientList);
        HandlerCheckBox.repopulateCheckBoxes(saucePane, ingredientList);
        //update the List views
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

    private List<String> getIngredientsFromListViews() {
        List<String> chosenIngredients = new ArrayList<>();

        chosenIngredients.addAll(proteinListView.getItems());
        chosenIngredients.addAll(carbListView.getItems());
        chosenIngredients.addAll(veggieListView.getItems());
        chosenIngredients.addAll(sauceListView.getItems());
        return chosenIngredients;
    }

    /*******************************
     * displays the Recipe to command line
     * @param event
     */
    public void displayRecipeButtonPressed(ActionEvent event) {
        for(Recipe e: allRecipes){
            System.out.println(e.toString());
        }
    }

    /*********************************
     Fills Grid full of buttons
     * @param event
     *********************************/
    public void fillGrid(ActionEvent event) {
        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
                int number = NUM_BUTTON_LINES * r + c;
                Button button = new Button(String.valueOf(number));
                this.grid.add(button, c, r);
            }
        }
    }


    /******************************
     * STILL IN PROGRESS
     * Adds ingredient to a list
     *
     *******************************/
    public void updateIngredientListsButtonPushed(ActionEvent event) {


        //======== check protein pane =========
        ObservableList<Node> proteinList = proteinPane.getChildren();
        for (int i =0; i < proteinList.size(); i++) {
            CheckBox cb = (CheckBox)proteinList.get(i);
            if (cb.isSelected()){
                //First check if ingredient has already been added.
                if( !proteinListView.getItems().contains(cb.getText())) {
                    proteinListView.getItems().add(cb.getText());
                    ChosenIngredients.addToProteinList(cb.getText());
                }
            }
            else {
                if( proteinListView.getItems().contains(cb.getText())){
                    proteinListView.getItems().remove(cb.getText());
                }
            }
        }
        //======== check carb pane =========
        ObservableList<Node> carbList = carbPane.getChildren();
        for (int i =0; i < carbList.size(); i++) {
            CheckBox cb = (CheckBox)carbList.get(i);
            if (cb.isSelected()){
                //First check if ingredient has already been added.
                if(!carbListView.getItems().contains(cb.getText())) {
                    carbListView.getItems().add(cb.getText());
                }
            }
            else {
                if( carbListView.getItems().contains(cb.getText())){
                    carbListView.getItems().remove(cb.getText());
                }
            }
        }
        //======== check veggie pane =========
        ObservableList<Node> veggieList = veggiePane.getChildren();
        for (int i =0; i < veggieList.size(); i++) {
            CheckBox cb = (CheckBox)veggieList.get(i);
            if (cb.isSelected()){
                //First check if ingredient has already been added.
                if(!veggieListView.getItems().contains(cb.getText())) {
                    veggieListView.getItems().add(cb.getText());
                }
            }
            else {
                if( veggieListView.getItems().contains(cb.getText())){
                    veggieListView.getItems().remove(cb.getText());
                }
            }
        }
        //======== check Sauce pane =========
        ObservableList<Node> sauceList = saucePane.getChildren();
        for (int i =0; i < sauceList.size(); i++) {
            CheckBox cb = (CheckBox)sauceList.get(i);
            if (cb.isSelected()){
                //First check if ingredient has already been added.
                if(!sauceListView.getItems().contains(cb.getText())) {
                    sauceListView.getItems().add(cb.getText());
                }
            }
            else {
                if( sauceListView.getItems().contains(cb.getText())){
                    sauceListView.getItems().remove(cb.getText());
                }
            }
        }
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
