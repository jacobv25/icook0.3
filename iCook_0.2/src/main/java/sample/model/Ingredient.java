package sample.model;

import java.io.Serializable;

public class Ingredient implements Serializable {

    public enum FoodCategory { PROTEIN, CARB, VEGGIE, SAUCE};

    public static final int RESET_I = 1;//the lowest id
    private static int cur_idI = RESET_I;
    private final int idI;
    private String idR;
    private String name;
    private String amount;
    private FoodCategory category;

    public Ingredient(String idR, String name, String amount, FoodCategory category) {
        this.idI = cur_idI++;
        //Ingredient.cur_idI++;
        this.idR = idR;
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public Ingredient(){
        this.idI = cur_idI++;
        //Ingredient.cur_idI++;
        //System.out.println("INGREDIENT CREATED!");
        //System.out.println("idI=" + idI);
        //System.out.println("curID=" + cur_idI);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "idI=" + idI +
                ", idR='" + idR + '\'' +
                ", name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", category=" + category +
                '}';
    }

    public FoodCategory getCategory() {
        return category;
    }

    public static void setCur_idI(int cur_idI) {
        Ingredient.cur_idI = cur_idI;
    }

    public static int getCur_idI() {
        return cur_idI;
    }

    public static void resetCur_idI() {
        Ingredient.cur_idI = RESET_I;
    }

    public int getIdI() {
        return idI;
    }

    public String getIdR() {
        return idR;
    }

    public void setIdR(String idR) {
        this.idR = idR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
