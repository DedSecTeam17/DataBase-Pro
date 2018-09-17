package sample.MarketSingeltons;

import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Product;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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



    public  static  void  main(String a[]){
        Auth.getInstance().start_session();
        Auth.getInstance().addUser("mohamed@yahoo.com");
        try {
            for (Product product:getInstance().getAllProductForSellers()){
                Log.i(product.getProductName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    
    public String addProduct(Product Product) {
        String data_base_message = "";

        try {
            FileInputStream fin =new FileInputStream(Product.getImage_path());
            Connection connection = Config.getInstance().getConnection();


            String sql = "INSERT INTO MARKET_PRODUCT(product_name,product_price,production_date,expired_date,production_company,admin_email,PRODUCT_QUANTITY,PRODUCT_IMAGE,CAT_ID) VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Product.getProductName());
            preparedStatement.setInt(2, Product.getProductPrice());
            preparedStatement.setDate(3, Date.valueOf(Product.getProductionDate()));
            preparedStatement.setDate(4, Date.valueOf(Product.getExpiredDate()));
            preparedStatement.setString(5, Product.getProductedCompany());
            preparedStatement.setString(6, Auth.getInstance().getCurrentUser());
            preparedStatement.setInt(7, Product.getQuantity());
            preparedStatement.setBinaryStream(8,fin,fin.available());
            preparedStatement.setInt(9,Product.getCat_id());
//
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
        } catch (ClassNotFoundException | IOException e) {
            data_base_message = e.getMessage();
        }

        return data_base_message;

    }

    public String deleteProduct(Product Product) {
        String meaasge = "";
        String sql = String.format("DELETE   FROM MARKET_PRODUCT WHERE  product_name='%s'", Product.getProductName());
        try {
            Connection connection = Config.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            meaasge = e.getMessage();
        }
        return meaasge;
    }

    public String updateProduct(Product Product) {
        String data_base_message = "";
        try {
            FileInputStream fin =new FileInputStream(Product.getImage_path());
            Connection connection = Config.getInstance().getConnection();
            String sql = "UPDATE MARKET_PRODUCT SET  product_price = ? ,  production_date = ? ,expired_date = ? ,production_company = ? ,admin_email = ?,PRODUCT_QUANTITY = ? , PRODUCT_IMAGE = ?, CAT_ID=? WHERE  product_name = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setInt(1, Product.getProductPrice());
            preparedStatement.setDate(2, Date.valueOf(Product.getProductionDate()));
            preparedStatement.setDate(3, Date.valueOf(Product.getExpiredDate()));
            preparedStatement.setString(4, Product.getProductedCompany());
            preparedStatement.setString(5, Auth.getInstance().getCurrentUser());
            preparedStatement.setInt(6, Product.getQuantity());
            preparedStatement.setBinaryStream(7,fin,fin.available());
            preparedStatement.setInt(8,Product.getCat_id());
            preparedStatement.setString(9, Product.getProductName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
        } catch (ClassNotFoundException e) {
            data_base_message = e.getMessage();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data_base_message;

    }

    public List<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        Connection connection = null;
//        product_name,product_price,production_date,expired_date,production_company,admin_email
        connection = Config.getInstance().getConnection();

        String sql = String.format("SELECT  * FROM  MARKET_PRODUCT WHERE  admin_email='%s'  ORDER  by production_date ASC ", Auth.getInstance().getCurrentUser());
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);

        ResultSet SET = statement.executeQuery(sql);
        List<Product> products = new ArrayList<>(20);
        while (SET.next()) {
            String p_name = SET.getString("product_name");
            int p_price = SET.getInt("product_price");
            String p_date = SET.getString("production_date");
            String p_expi_date = SET.getString("expired_date");
            String p_company = SET.getString("production_company");
            int p_quantity = SET.getInt("PRODUCT_QUANTITY");
            int cat_id = SET.getInt("CAT_ID");

            Product product = Product.newProduct()
                    .productName(p_name)
                    .productPrice(p_price)
                    .productedCompany(p_company)
                    .productionDate(p_date)
                    .expiredDate(p_expi_date)
                    .quantity(p_quantity)
                    .Cat_id(cat_id)
                    .build();

            Log.i(String.valueOf("test"+product.getCat_id()));


            products.add(product);
        }
        return products;
    }


    public  void  updateProductQuantity(String p_name,int quantity)
    {

        String data_base_message = "";
        try {
            Connection connection = Config.getInstance().getConnection();
            String sql = "UPDATE MARKET_PRODUCT SET  PRODUCT_QUANTITY = ?  WHERE  product_name = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setString(2, p_name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
            Log.i(data_base_message);
        } catch (ClassNotFoundException e) {
            data_base_message = e.getMessage();
            Log.i(data_base_message);
        }

    }


    public List<Product> getAllProductForSellers() throws SQLException, ClassNotFoundException {
        Connection connection = null;
//        product_name,product_price,production_date,expired_date,production_company,admin_email
        connection = Config.getInstance().getConnection();

        String sql = String.format("select  * from  MARKET_PRODUCT join  MARKETSELLER  on MARKET_PRODUCT.ADMIN_EMAIL = MARKETSELLER.ADMIN_EMAIL  where EMAIL='%s' ",Auth.getInstance().getCurrentUser());

        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);

        ResultSet SET = statement.executeQuery(sql);
        List<Product> products = new ArrayList<>(20);
        while (SET.next()) {
            String p_name = SET.getString("product_name");
            int p_price = SET.getInt("product_price");
            String p_date = SET.getString("production_date");
            String p_expi_date = SET.getString("expired_date");
            String p_company = SET.getString("production_company");
            int p_quantity = SET.getInt("PRODUCT_QUANTITY");

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
        return products;
    }

}
