package sample.MarketSingeltons;

import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Category;
import sample.MarketModel.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDataBaseSingleton {
    private static CategoryDataBaseSingleton ourInstance = new CategoryDataBaseSingleton();

    public static CategoryDataBaseSingleton getInstance() {
        return ourInstance;
    }


    private CategoryDataBaseSingleton() {
    }





    public String addCategory(Category Category) {
        String data_base_message = "";
        try {
            Connection connection = Config.getInstance().getConnection();
            String sql = "INSERT INTO MarketCategory(CAT_ID,CAT_NAME,EMAIL) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Category.getCategory_id());
            preparedStatement.setString(2, Category.getCategory_name());
            preparedStatement.setString(3, Auth.getInstance().getCurrentUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
        } catch (ClassNotFoundException e) {
            data_base_message = e.getMessage();
        }

        return data_base_message;

    }
    public String deleteCategory(Category Category) {
        String meaasge = "";
        String sql = String.format("DELETE   FROM MarketCategory WHERE  CAT_ID='%d'", Category.getCategory_id());
        try {
            Connection connection = Config.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            meaasge = e.getMessage();
        }
        return meaasge;
    }
    public String updateCategory(Category Category) {
        String data_base_message = "";
        try {
            Connection connection = Config.getInstance().getConnection();
            String sql = "UPDATE MarketCategory SET  CAT_NAME = ? WHERE  CAT_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Category.getCategory_name());
            preparedStatement.setInt(2, Category.getCategory_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
        } catch (ClassNotFoundException e) {
            data_base_message = e.getMessage();
        }

        return data_base_message;

    }
    public List<Category> getAllCategory() throws SQLException, ClassNotFoundException {
        Connection connection = null;
//        product_name,product_price,production_date,expired_date,production_company,admin_email
        connection = Config.getInstance().getConnection();

        String sql = String.format("SELECT  * FROM  MarketCategory WHERE  EMAIL='%s'  ORDER  by CAT_ID ASC ", Auth.getInstance().getCurrentUser());
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);

        ResultSet SET = statement.executeQuery(sql);
        List<Category> products = new ArrayList<>(20);
        while (SET.next()) {
            String cat_name = SET.getString("CAT_NAME");
            int cat_id = SET.getInt("CAT_ID");

            Category product = Category.newCategory()
                    .categoryName(cat_name)
                    .categoryID(cat_id)
                    .build();
            products.add(product);
        }
        return products;
    }
}
