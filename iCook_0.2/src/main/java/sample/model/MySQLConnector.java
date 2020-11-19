package sample.model;

import com.sun.deploy.association.RegisterFailedException;
import sample.LoginMenuController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnector {


    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/icookdb";
        String username = "root";
        String password = "password";

        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connected!");
        return  connection;
    }

    /*************************************************************
     * Used for Save Ingredient Button.
     * Not going to be used.
     * @throws SQLException
     *************************************************************/
    public static void saveUserIngredients() throws SQLException {
        Connection connection = getConnection();
        String sql;
        PreparedStatement statement;
        //User user = User.getUser();

        for(String e: ChosenIngredients.getChosenIngredientNamesList()) {
            System.out.println(e.toString());
            sql = "INSERT INTO useringredient(userID, ingredientName) VALUES(?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, User.id);
            statement.setString(2, e);
            statement.executeUpdate();
        }
    }

    /*****************************************************
     * Deletes a saved user ingredient
     * @param ingredientName
     * @throws SQLException
     ****************************************************/
    public static void deleteUserIngredient(String ingredientName) throws SQLException {

        Connection connection = getConnection();
        String sql;
        PreparedStatement statement;


        sql = "DELETE FROM useringredient WHERE ingredientName = ? AND userID = ?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, ingredientName);
        statement.setInt(2, User.id);
        statement.executeUpdate();
    }

    /********************************************************
     * Saves a user's ingredient
     * @param ingredientName
     * @throws SQLException
     *******************************************************/
    public static void saveUserIngredient(String ingredientName) throws SQLException {
        Connection connection = getConnection();
        String sql;
        PreparedStatement statement;


        sql = "INSERT INTO useringredient(userID, ingredientName) VALUES(?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, User.id);
        statement.setString(2, ingredientName);
        statement.executeUpdate();
    }

    /***********************************************
     * Returns the correct user data
     * @param userID
     * @throws SQLException
     **********************************************/
    public static List<String> getSavedUserIngredientNamess(int userID) throws SQLException {

        Connection connection = getConnection();
        String sql;
        PreparedStatement statement;

        //get user who's username matches parameter
        sql = "SELECT ingredientName FROM useringredient WHERE userID = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, userID);
        ResultSet resultSet = statement.executeQuery();

        List<String> ingredientNames = new ArrayList<>();
        String name;
        List<Ingredient> ingredients = new ArrayList<>();


        while (resultSet.next()){
            name = resultSet.getString("ingredientName");
            ingredientNames.add(name);
        }
        return ingredientNames;
    }

    /***************************************************
     * Gets user's saved ingredient list
     * @param ingredientNames
     * @return
     * @throws SQLException
     **************************************************/
    public static List<Ingredient> getSavedUserIngredient(List<String> ingredientNames) throws SQLException {

        Connection connection = getConnection();
        String sql;
        Statement statement;

        StringBuilder nameQuery = new StringBuilder();
        String name;
        List<Ingredient> ingredients = new ArrayList<>();

        if(ingredientNames.isEmpty()){
            return ingredients;
        }
        else {
            for (int i = 0; i < ingredientNames.size(); i++) {
                name = ingredientNames.get(i);
                nameQuery.append("nameI = '" + name + "'");
                //if i isn't the last index
                if (i != ingredientNames.size() - 1) {
                    nameQuery.append(" OR ");
                }
            }
            System.out.println(nameQuery.toString());
            //get user who's username matches parameter
            sql = "SELECT * FROM ingredient WHERE " + nameQuery.toString();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int idI, idR;
            String nameI, amount;
            Ingredient.FoodCategory fc;

            while (resultSet.next()) {
                idI = resultSet.getInt("idI");
                idR = resultSet.getInt("idR");
                nameI = resultSet.getString("nameI");
                amount = resultSet.getString("amount");
                //Slightly convoluted way to retrieve enums from mySQL
                fc = Ingredient.FoodCategory.valueOf(resultSet.getString("category"));

                ingredients.add(new Ingredient(Integer.toString(idI), Integer.toString(idR), nameI, amount, fc));
            }
            return ingredients;
        }
    }

    /*****************************************************************
     * Registers a user in the database if one does not already exist.
     * @param username
     * @param password
     * @throws SQLException
     *****************************************************************/
    public static void registerUser(String username, String password) throws SQLException {

        Connection connection = getConnection();
        String sql;
        PreparedStatement statement;

        //check if username already exists
        sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        //statement.setString(3, password);
        statement.executeUpdate();
    }

    /************************************************************
     * Check if username and password input are correct keys
     * @param u Data comes from usernameTextView
     * @param p Data comes from passwordTextView
     * @return resultSet  Contains all the data retrieved from DB
     * @throws SQLException
     * @throws LoginFailedException
     ***********************************************************/
    public static ResultSet requestLogin(String u, String p) throws SQLException, LoginFailedException{

        Connection connection = getConnection();
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, u);
        statement.setString(2, p);
        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.next()){
            System.out.println("login failed");
            //Login failed. Throw exception
            throw new LoginFailedException();
        }
        else {
            System.out.println("success...");
            //Login success. Return resultSet to retrieve data
            return resultSet;
        }
    }

    /**********************************************************************************
     * Returns a list of chosen Ingredients
     * @param ingredientNames
     * @return
     * @throws SQLException
     **********************************************************************************/
    public static List<Ingredient> getChosenIngredients(List<String> ingredientNames) throws SQLException{

        List<Ingredient> allIngredients = new ArrayList<>();

        Connection connection = getConnection();

        for(String name: ingredientNames) {
            String sql = "SELECT * FROM ingredient WHERE nameI = ?";
            //String sql = "SELECT * FROM ingredient";
            //PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setString(1, name);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            int idI, idR;
            String nameI;
            String amount;
            Ingredient.FoodCategory foodCategory;
            Ingredient ingredient;
            while (resultSet.next()){
                idI = resultSet.getInt("idI");
                idR = resultSet.getInt("idR");
                nameI = resultSet.getString("nameI");
                amount = resultSet.getString("amount");
                //Slightly convoluted way to retrieve enums from mySQL
                foodCategory = Ingredient.FoodCategory.valueOf(resultSet.getString("category"));

                ingredient = new Ingredient(Integer.toString(idI), Integer.toString(idR), nameI, amount, foodCategory);
                allIngredients.add(ingredient);
            }
        }
        connection.close();
        return allIngredients;
    }

    /*************************************************************************************
     * Generate Recipes Test function attempt #1
     * This is the search algortithm.
     * It succeeds in getting every recipe that matches each chosen ingredient.
     * VERY PRIMITIVE. NEEDS WORK
     * @param ingredientNames
     * @return
     * @throws SQLException
     *************************************************************************************/
    public static List<String> generateRecipeNames(List<String> ingredientNames) throws SQLException {

        Connection connection = getConnection();

        String sql;
        Statement statement;
        ResultSet resultSet;

        List<Integer> tempRecipeIDList = new ArrayList<>();
        List<String> recipeNameList = new ArrayList<>();
        Recipe recipe;

        String recipeName;
        int recipeID;

        //Iterate through all the ingredient names
        for(String name: ingredientNames ) {
            //==============EXECUTE SQL QUERY=======================

            //This code is for joining two tables . Not needed at this time, but interesting to look at.
            //String sql = "SELECT recipe.idR, recipe.nameR, ingredient.idI, ingredient.nameI, ingredient.amount, ingredient.category FROM recipe INNER JOIN ingredient ON recipe.idR = ingredient.idR";
            sql = "SELECT idR FROM ingredient WHERE nameI == " + name;

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //============ Result Set ! =============================
            //Get all ingredients from DB that match the name of ingredientNames[i] and add them to a list called 'tempAllIngredients'
            while (resultSet.next()){
                recipeID = resultSet.getInt("idR");
                tempRecipeIDList.add(recipeID);//shouldn't have any duplicates.... but i may be wrong
            }
            //Iterate through tempAllIngredients and request from DB all recipes whose id matches tempAllIngredients[i].getidR()
            for (Integer id: tempRecipeIDList){

                sql = "SELECT nameR FROM recipe WHERE idR == " + id;
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            }
            //add those recipe names to a list called generatedRecipes
            while(resultSet.next()) {
                recipeName = resultSet.getString("nameR");
                recipeNameList.add(recipeName);
            }
        }
        connection.close();
        return recipeNameList;

    }
    /**************************************************************
     * Gets Recipe ID and name from the database
     * @return a List of all the recipes
     **************************************************************/
    public static List<Recipe> getAllRecipes() throws SQLException {

        List<Recipe> allRecipes = new ArrayList<>();

        Connection connection = getConnection();

        //String sql = "SELECT recipe.idR, recipe.nameR, ingredient.idI, ingredient.nameI, ingredient.amount, ingredient.category FROM recipe INNER JOIN ingredient ON recipe.idR = ingredient.idR";
        String sql = "SELECT * FROM recipe";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int count = 0;
        int idR;//recipe id
        //int idI;//ingredient id


        String nameR;
        //String nameI;
        //String amount;


        //Ingredient.FoodCategory foodCategory;
        //Ingredient ingredient;
        Recipe recipe;

        while (resultSet.next()){
            idR = resultSet.getInt("idR");
            nameR = resultSet.getString("nameR");
//               idI = resultSet.getInt("idI");
//               nameI = resultSet.getString("nameI");
//                amount = resultSet.getString("amount");
//                //Slightly convoluted way to retrieve enums from mySQL
//                foodCategory = Ingredient.FoodCategory.valueOf(resultSet.getString("category"));
             count++;

             recipe = new Recipe(idR, nameR);

                //ingredient = new Ingredient(Integer.toString(idR), nameI, amount, foodCategory);
                //System.out.println("Ingredient " + count + ": " + idR + ", " + nameI + ", " + amount);
                //System.out.println("Ingredient was added to recipe: " + nameR +"!");
                //recipe.addIngredient(ingredient);

            allRecipes.add(recipe);
        }
        connection.close();
        System.out.println("number of recipes=" + allRecipes.size());

        return allRecipes;
    }

    /***************************************************************************
     * Get's a list of all Ingredient names from DB
     * @return A String list of names
     * @throws SQLException
     **************************************************************************/
    public static ArrayList<String> getAllIngredientNames() throws SQLException {

        ArrayList<String> allIngredientNames = new ArrayList<>();

        Connection connection = getConnection();

        String sql = "SELECT nameI FROM ingredient";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        String name;

        while (resultSet.next()){

            name = resultSet.getString("nameI");

            allIngredientNames.add(name);
        }
        connection.close();

        return allIngredientNames;
    }

    /***************************************************************************
     * Get's all the ingredients from the DB
     * @return a Ingredient List
     * @throws SQLException
     ****************************************************************************/
    public static List<Ingredient> getAllIngredients() throws SQLException {

        ArrayList<Ingredient> allIngredients = new ArrayList<>();

        Connection connection = getConnection();

        String sql = "SELECT * FROM ingredient";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int count = 0;
            int idI, idR;
            String name;
            String amount;
            Ingredient.FoodCategory foodCategory;
            Ingredient ingredient;
            while (resultSet.next()){
                idI = resultSet.getInt("idI");
                idR = resultSet.getInt("idR");
                name = resultSet.getString("nameI");
                amount = resultSet.getString("amount");
                //Slightly convoluted way to retrieve enums from mySQL
                foodCategory = Ingredient.FoodCategory.valueOf(resultSet.getString("category"));

                count++;
                ingredient = new Ingredient(Integer.toString(idI), Integer.toString(idR), name, amount, foodCategory);
//                System.out.println("Ingredient " + count + ": " + idR + ", " + name + ", " + amount);
//                System.out.println("was added to allIngredients!");
                allIngredients.add(ingredient);
            }
            connection.close();

            return allIngredients;
    }

}
