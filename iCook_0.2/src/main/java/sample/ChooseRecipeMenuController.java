package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.model.ChosenIngredients;
import sample.model.Ingredient;
import sample.model.MySQLConnector;
import sample.model.Recipe;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChooseRecipeMenuController implements Initializable {

    @FXML ListView recipeListView;

    private List<String> ingredientNamesList;

    /************************************************************
     *
     * @return
     ***********************************************************/
    public List<Ingredient> getChosenIngredientsList() throws SQLException{
        List<String> ingredientStringList = new ArrayList<>(recipeListView.getItems());
        List<Ingredient> ingredientList = new ArrayList<>(MySQLConnector.getChosenIngredients(ingredientStringList));

        return ingredientList;
    }

    /********************************************************
     * When this button is pressed it will change the scene
     * F
     ****************************************************/
    public void returnToMainMenu(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("ingredients_menu.fxml"));
        Parent ingredientMenuParent = loader.load();

        Scene ingredientMenuScene = new Scene(ingredientMenuParent);

        //access Controller
        IngredientMenuController controller = loader.getController();
        controller.initDataFromChooseRecipeMenu(getChosenIngredientsList());

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(ingredientMenuScene);
        window.show();

    }


    private void fillRecipeList() throws SQLException {

        List<Recipe> recipeList = new ArrayList<>(MySQLConnector.getAllRecipes());
        //List<String> nameList = new ArrayList<>();

        for (Recipe e: recipeList){

            //nameList.add(e.getName());
            recipeListView.getItems().add(e.getName());
        }

        //recipeListView.getItems().addAll(nameList);
        System.out.println("filled!");
    }

//    private List<Recipe> chooseRecipes(List<Recipe> allRecipes){
//
//        allRecipes
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        recipeListView.getItems().addAll(ChosenIngredients.getChosenIngredientNamesList());
        //recipeListView = new ListView();
//        try {
//           // fillRecipeList();
//            recipeListView.getItems().addAll(ingredientNamesList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
