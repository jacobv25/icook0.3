package sample.model;
//import sample.model.Ingredient;
//llololol

import javax.lang.model.element.NestingKind;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recipe implements Serializable {

    //public static final int RESET_R = 1;//lowest id
    //private static int cur_idR = RESET_R;
    private final int idR;
    private String name;

    //private int code;
    //private String name;
    //private String description;
    private ArrayList<Ingredient> ingredients;

    public Recipe( int idR, String name) {
        this.idR = idR;
        this.name = name;
        //this.description = description;
        ingredients = new ArrayList<>();
    }

    public String toString(){

        String s = "Recipe[\n" +
                "\tid=" + this.idR + "\n" +
                "\tname=" + this.name + "\n" +
                "\tIngredients[\n" +
                    "\t\t" + displayAllIngredients();
        return s;
    }


    //    public static int getCur_idR() {
//        return cur_idR;
//    }
//
//    public static void setCur_idR(int cur_idR) {
//        Recipe.cur_idR = cur_idR;
//    }
//
//    public static void resetCur_idI() {
//        Recipe.cur_idR = RESET_R;
//    }


    public int getIdR() {
        return idR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getDescription() {
//        return description;
//    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }

    public String displayAllIngredients(){
        String s = "";
        for(Ingredient e: ingredients){
            s += e.toString();
        }
        return s;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = new ArrayList<>(ingredients);
    }
}
