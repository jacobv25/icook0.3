package sample.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    public static String username;
    public static List<Ingredient> savedIngredients;
    public static List<Recipe> savedRecipes;
    public static int id;

    private static User currentUser = null;

    private User(){
        username = "default_name";
        savedIngredients = new ArrayList<>();
        savedIngredients = new ArrayList<>();
        id = 999999;
    }

    public static User getUser(){
        if(currentUser == null){
            //System.out.println("returning new user");
            return new User();
        }
        else {
            return currentUser;
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Ingredient> getSavedIngredients() {
        return this.savedIngredients;
    }

    public void saveIngredients(List<Ingredient> ingredientList) {
        this.savedIngredients = new ArrayList<>(ingredientList);
    }

    public List<Recipe> getSavedRecipes() {
        return this.savedRecipes;
    }

    public void saveRecipes(List<Recipe> recipe) {
        this.savedRecipes = new ArrayList<>(recipe);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
