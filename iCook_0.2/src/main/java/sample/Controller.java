//package sample;
//
//
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.control.*;
//import javafx.scene.layout.FlowPane;
//import javafx.scene.layout.GridPane;
//import main.java.sample.model.Ingredient;
//import main.java.sample.model.Recipe;
////import sample.model.Ingredient;
////import sample.model.Recipe;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class Controller implements Initializable {
//
//    private static final int NUM_BUTTON_LINES = 5;
//    private static final int BUTTONS_PER_LINE = 3;
//    private static final int MAX_INGREDIENTS = 3;
//    private static final int MAX_RECIPES = 3;
//
//    @FXML GridPane grid;
//    @FXML ListView list1 = new ListView();
//    String proteins[] = {"Chicken", "Beef", "Turkey"};
//    String vegetables[] = {"Broccoli", "Carrot", "Pea"};
//
//    @FXML TextArea textArea1;
//    String ingredients = "";
//    String spaghetti = "beef pasta spaghetti-sauce oil";
//    String chickenParm = "chicken bread-crumble oil pasta spaghetti-sauce";
//    ArrayList<String> possibleRecipesString = new ArrayList<>();
//    ArrayList<String> chosenIngredientsString = new ArrayList<>();
//
//    ArrayList<Recipe> allRecipes;
//    ArrayList<Recipe> possibleRecipes;
//    ArrayList<Ingredient> allIngredients;
//    ArrayList<Ingredient> dairyIngredients;
//    ArrayList<Ingredient> proteinIngredients;
//    ArrayList<Ingredient> chosenIngredients;
//
//    Recipe recipe1;
//    Ingredient ingredient1;
//    Ingredient ingredient2;
//    Ingredient ingredient3;
//
//    @FXML FlowPane dairyPane;
//
//    /*****************************
//     *
//     * @return string array of recipe names
//     ****************************/
////    public ArrayList<Recipe> search(){
////
////        ArrayList<Recipe> tempList = new ArrayList<>();
////        Recipe tempRecipe;
////
////        for(int i = 0; i < possibleRecipesString.size(); i++) {
////            for(int j = 0; j < chosenIngredientsString.size(); j++) {
////                if (possibleRecipesString.get(i).contains(chosenIngredientsString.get(j))) {
////
////                }
////            }
////        }
////    }
//    /*******************************
//     * Fills the first list with protein ingredients
//     * @param event
//     */
//    public void proteinButtonPress(ActionEvent event) {
//        fillListView(proteins);
//    }
//
//    /*********************************
//     Fills Grid full of buttons
//     * @param event
//     *********************************/
//    public void fillGrid(ActionEvent event) {
//        for (int r = 0; r < NUM_BUTTON_LINES; r++) {
//            for (int c = 0; c < BUTTONS_PER_LINE; c++) {
//                int number = NUM_BUTTON_LINES * r + c;
//                Button button = new Button(String.valueOf(number));
//                this.grid.add(button, c, r);
//            }
//        }
//    }
//
//    /*******************************
//     * Fills the list view
//     * @param ar array holding items
//     *******************************/
//    void fillListView(String[] ar) {
//
//        for (int i = 0; i < MAX_INGREDIENTS; i++) {
//
//            this.list1.getItems().add(ar[i]);
//
//        }
//    }
//    /******************************
//     *
//     *******************************/
//    void fillDairyIngredientPane(ArrayList<Ingredient> list) {
//
//
//    }
//
//    /*******************************
//     *
//     ******************************/
//    public void ingredientPush(ActionEvent event){
//
//        ObservableList list = list1.getSelectionModel().getSelectedItems();
//
//        for (Object item: list) {
//            if(ingredients.contains((String) item)){
//                continue;
//            }
//            chosenIngredientsString.add((String)item);//keep track of chosen ingredients
//            this.ingredients += String.format("%s%n", (String)item);
//        }
//        textArea1.setText(this.ingredients);
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//
//        allRecipes = new ArrayList<>();
//        allIngredients = new ArrayList<>();
//        dairyIngredients = new ArrayList<>();
//        proteinIngredients = new ArrayList<>();
//
//        //recipe1 = new Recipe( 0, "Scrambled Eggs");
////        ingredient1 = new Ingredient(0 , "Egg", "2 whole eggs");
////        ingredient2 = new Ingredient(1, "Butter", "1 Tbs Butter");
////        ingredient3 = new Ingredient(2, "Milk", "3 cups Milk");
//
//        //add all recipes
//        allRecipes.add(recipe1);
//        //add all ingredients
//        allIngredients.add(ingredient1);
//        allIngredients.add(ingredient2);
//        allIngredients.add(ingredient3);
//        //add dairy ingredients
//        dairyIngredients.add(ingredient2);
//        dairyIngredients.add(ingredient3);
//        //add protein ingredients
//        proteinIngredients.add(ingredient1);
//
//        //create check boxes and
//        //add check boxes for dairy pane
//        CheckBox checkBox = null;
//        for (Ingredient dairyIngredient : dairyIngredients) {
//            checkBox = new CheckBox(dairyIngredient.getName());
//            System.out.println("Added?=" + dairyPane.getChildren().add(checkBox));
//        }
//
//        /*************************************************************************/
//
//        //get possibleRecipesString ready
////        possibleRecipesString.add(spaghetti);
////        possibleRecipesString.add(chickenParm);
////        //get list ready
////        list1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//    }
//
//}

