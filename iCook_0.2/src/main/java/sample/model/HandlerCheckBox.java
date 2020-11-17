package sample.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static sample.model.Ingredient.FoodCategory.*;

/**************************************************************************
 * This class handles the check boxes in IngredientMenuController.java
 * It intilizes
 ***************************************************************************/
public class HandlerCheckBox {


    /**
     * This method is called during initialization.
     * This method gives the check boxes their ability to tell the program
     * when they have been ticked off.
     * @param event
     */
    public static void handleCheckBoxes(ActionEvent event, FlowPane pane, ListView listView) {

        CheckBox cb = (CheckBox) event.getSource();

        if (cb.isSelected()) {
            //First check if ingredient has already been added.
            if (!listView.getItems().contains(cb.getText())) {
                listView.getItems().add(cb.getText());

                if (pane.getId().equals("proteinPane")) {
                    ChosenIngredients.addToProteinList(cb.getText());
                } else if (pane.getId().equals("carbPane")) {
                    ChosenIngredients.addToCarbList(cb.getText());
                } else if (pane.getId().equals("veggiePane")) {
                    ChosenIngredients.addToVeggieList(cb.getText());
                } else if (pane.getId().equals("saucePane")) {
                    ChosenIngredients.addToSauceList(cb.getText());
                }
            }
        }
        else {
            if (listView.getItems().contains(cb.getText())) {
                listView.getItems().remove(cb.getText());
            }
            if (pane.getId().equals("proteinPane")) {
                ChosenIngredients.removeToProteinList(cb.getText());
            } else if (pane.getId().equals("carbPane")) {
                ChosenIngredients.removeToCarbList(cb.getText());
            } else if (pane.getId().equals("veggiePane")) {
                ChosenIngredients.removeToVeggieList(cb.getText());
            } else if (pane.getId().equals("saucePane")) {
                ChosenIngredients.removeToSauceList(cb.getText());
            }
        }

    }

    /**
     * Repopulates the check boxes when returning from Choose Recipe View
     * @param pane
     * @param list
     */
    public static void repopulateCheckBoxes(FlowPane pane, List<Ingredient> list) {
        CheckBox checkBox;
        //find out which pane we are adding to
        System.out.println("IN HANDLE CHECK BOXES");
        if(pane.getId().equals("proteinPane")) {
            System.out.println("IN PROTEIN PANE");
            System.out.println("LIST SIZE=" + list.size());
            for (int i=0; i < list.size(); i++){
                System.out.println(list.get(i).toString());
            }

            for (int i=0; i < list.size(); i++) {
                if (list.get(i).getCategory() == PROTEIN) {
                    System.out.println("Ingredient is a protein !!");
                    //Access the checkbox in the pane and check it off
                    for (Node node : pane.getChildren()) {
                        checkBox = (CheckBox) node;//cast the node as a checkbox
                        if (checkBox.getText().equals(list.get(i).getName())) {//check if the checkbox name matches
                            checkBox.setSelected(true);
                            //remove ingredient from list
                            //list.remove(list.get(i));
                            break;
                        }
                    }
                }
            }
        }
        else if(pane.getId().equals("carbPane")){
            for (int i=0; i < list.size(); i++) {
                if (list.get(i).getCategory() == CARB) {
                    //Access the checkbox in the pane and check it off
                    for (Node node : pane.getChildren()) {
                        checkBox = (CheckBox) node;//cast the node as a checkbox
                        if (checkBox.getText().equals(list.get(i).getName())) {//check if the checkbox name matches
                            checkBox.setSelected(true);
                            //remove ingredient from list
                            //list.remove(list.get(i));
                            break;
                        }
                    }
                }
            }
        }
        else if (pane.getId().equals("veggiePane")){
            for (int i=0; i < list.size(); i++) {
                if (list.get(i).getCategory() == VEGGIE) {
                    //Access the checkbox in the pane and check it off
                    for (Node node : pane.getChildren()) {
                        checkBox = (CheckBox) node;//cast the node as a checkbox
                        if (checkBox.getText().equals(list.get(i).getName())) {//check if the checkbox name matches
                            checkBox.setSelected(true);
                            //remove ingredient from list
                            //list.remove(list.get(i));
                            break;
                        }
                    }
                }
            }
        }
        else if (pane.getId().equals("saucePane")){
            for (int i=0; i < list.size(); i++) {
                if (list.get(i).getCategory() == SAUCE) {
                    //Access the checkbox in the pane and check it off
                    for (Node node : pane.getChildren()) {
                        checkBox = (CheckBox) node;//cast the node as a checkbox
                        if (checkBox.getText().equals(list.get(i).getName())) {//check if the checkbox name matches
                            checkBox.setSelected(true);
                            //remove ingredient from list
                            //list.remove(list.get(i));
                            break;
                        }
                    }
                }
            }
        }
    }
}

