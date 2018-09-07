package sample.MarketSingeltons;

import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Product;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductDataBaseSingleton {
    private static ProductDataBaseSingleton ourInstance = new ProductDataBaseSingleton();

    public static ProductDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private ProductDataBaseSingleton() {
    }




    public  String addProduct(Product Product)
    {
        String data_base_message="";
        try {
            Connection connection=Config.getInstance().getConnection();
            String sql = "INSERT INTO MarketProduct(product_name,product_price,production_date,expired_date,production_company,admin_email,PRODUCT_QUANTITY) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Product.getProductName());
            preparedStatement.setInt(2,Product.getProductPrice());
            preparedStatement.setDate(3, Date.valueOf(Product.getProductionDate()));
            preparedStatement.setDate(4, Date.valueOf(Product.getExpiredDate()));
            preparedStatement.setString(5, Product.getProductedCompany());
            preparedStatement.setString(6, Auth.getInstance().getCurrentUser());
            preparedStatement.setInt(7, Product.getQuantity());
            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message=e.getMessage();
        } catch (ClassNotFoundException e) {
            data_base_message=e.getMessage();
        }

        return  data_base_message;

    }
    public  String deleteProduct(Product Product)
    {
        return  "message come from server";

    }
    public  String updateProduct(Product Product)
    {
        return  "message come from server";

    }

    public List<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        Connection connection = null;
//        product_name,product_price,production_date,expired_date,production_company,admin_email
            connection = Config.getInstance().getConnection();

        String sql = String.format("SELECT  * FROM  MarketProduct WHERE  admin_email='%s'  ORDER  by production_date ASC ",Auth.getInstance().getCurrentUser());
        PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);

        ResultSet set = statement.executeQuery(sql);
        List<Product> products = new ArrayList<>(20);
        while (set.next()) {
            String p_name = set.getString("product_name");
            int p_price = set.getInt("product_price");
            String p_date = set.getString("production_date");
            String p_expi_date = set.getString("expired_date");
            String p_company = set.getString("production_company");
            int p_quantity = set.getInt("PRODUCT_QUANTITY");

            Product product = Product.newProduct()
                    .productName(p_name)
                    .productPrice(p_price)
                    .productedCompany(p_company)
                    .productionDate(p_date)
                    .expiredDate(p_expi_date)
                    .quantity(p_quantity)
                    .build();


            products.add(product);
        }
        return  products;
    }
}
