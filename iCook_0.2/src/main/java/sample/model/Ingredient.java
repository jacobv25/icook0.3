package sample.model;

import java.io.Serializable;

public class Ingredient implements Serializable {

    public enum FoodCategory { PROTEIN, CARB, VEGGIE, SAUCE};

    //public static final int RESET_I = 1;//the lowest id
    //private static int cur_idI = RESET_I;
    private String idI;
    private String idR;
    private String name;
    private String amount;
    private FoodCategory category;

    public Ingredient(String idI, String idR, String name, String amount, FoodCategory category) {
        this.idI = idI;
        this.idR = idR;
        this.name = name;
        this.amount = amount;
        this.category = category;
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

    public String getIdI() {
        return idI;
    }

    public void setIdI(String idI) {
        this.idI = idI;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setCategory(FoodCategory category) {
        this.category = category;
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
