package sample.model;

import java.util.ArrayList;
import java.util.List;

public class ChosenIngredients {

    private static List<String> proteinList = new ArrayList<>();
    private static List<String> carbList = new ArrayList<>();
    private static List<String> veggieList = new ArrayList<>();
    private static List<String> sauceList = new ArrayList<>();


    /**
     * Adds the name of an ingredient to a List.
     * Checks for duplicates.
     * @param ingredient
     */
    public static void addToProteinList(String ingredient){
        if(!proteinList.contains(ingredient)) {
            proteinList.add(ingredient);
        }
    }
    public static void removeToProteinList(String ingredient){
        if(proteinList.contains(ingredient)) {
            proteinList.remove(ingredient);
        }
    }
    public static void addToCarbList (String ingredient) {
        if(!carbList.contains(ingredient)){
            carbList.add(ingredient);
        }
    }
    public static void removeToCarbList (String ingredient) {
        if(carbList.contains(ingredient)){
            carbList.remove(ingredient);
        }
    }
    public static void addToVeggieList (String ingredient) {
        if(!veggieList.contains(ingredient)){
            veggieList.add(ingredient);
        }
    }
    public static void removeToVeggieList (String ingredient) {
        if(veggieList.contains(ingredient)){
            veggieList.remove(ingredient);
        }
    }
    public static void addToSauceList (String ingredient) {
        if(!sauceList.contains(ingredient)){
            sauceList.add(ingredient);
        }
    }
    public static void removeToSauceList (String ingredient) {
        if(sauceList.contains(ingredient)){
            sauceList.remove(ingredient);
        }
    }

    /**
     * Adds all the lists together and returns them
     * @return chosenIngredientList
     */
    public static List<String> getChosenIngredientNamesList(){
        List<String> chosenIngredientList = new ArrayList<>();
        chosenIngredientList.addAll(proteinList);
        chosenIngredientList.addAll(carbList);
        chosenIngredientList.addAll(veggieList);
        chosenIngredientList.addAll(sauceList);
        return chosenIngredientList;
    }

    public static List<String> getProteinList(){

        return proteinList;
    }

    public static List<String> getCarbList() {
        return carbList;
    }

    public static List<String> getVeggieList() {
        return veggieList;
    }

    public static List<String> getSauceList() {
        return sauceList;
    }
}
