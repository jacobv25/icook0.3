package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private static final int NUM_BUTTON_LINES = 5;
    private static final int BUTTONS_PER_LINE = 3;
    private static final int MAX_INGREDIENTS = 3;
    private static final int MAX_RECIPES = 3;

    @FXML GridPane grid;
    @FXML ListView list1 = new ListView();
    String proteins[] = {"Chicken", "Beef", "Turkey"};
    String vegetables[] = {"Broccoli", "Carrot", "Pea"};

    @FXML TextArea textArea1;
    String ingredients = "";
    String spaghetti = "beef pasta spaghetti-sauce oil";
    String chickenParm = "chicken bread-crumble oil pasta spaghetti-sauce";
    ArrayList<String> possibleRecipes = new ArrayList<>();
    ArrayList<String> chosenIngredients = new ArrayList<>();


    /*****************************
     * 
     * @return string array of recipe names
     ****************************/
   /* public String[] search(){

        String[] tempAr = new String[MAX_RECIPES];
        String temp = textArea1.getText();

        for(int i = 0; i < possibleRecipes.size(); i++) {
            for(int j = 0; j < chosenIngredients.size(); j++) {
                if (possibleRecipes.get(i).contains(chosenIngredients.get(j))) {

                }
            }
        }
    }*/
    /*******************************
     * Fills the first list with protein ingredients
     * @param event
     */
    public void proteinButtonPress(ActionEvent event) {
        fillListView(proteins);
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

    /*******************************
     * Fills the list view
     * @param ar array holding items
     *******************************/
    void fillListView(String[] ar) {

        for (int i = 0; i < MAX_INGREDIENTS; i++) {

            this.list1.getItems().add(ar[i]);

        }
    }

    /*******************************
     *
     ******************************/
    public void ingredientPush(ActionEvent event){

        ObservableList list = list1.getSelectionModel().getSelectedItems();

        for (Object item: list) {
            if(ingredients.contains((String) item)){
                continue;
            }
            chosenIngredients.add((String)item);//keep track of chosen ingredients
            this.ingredients += String.format("%s%n", (String)item);
        }
        textArea1.setText(this.ingredients);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //get possibleRecipes ready
        possibleRecipes.add(spaghetti);
        possibleRecipes.add(chickenParm);
        //get list ready
        list1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}

