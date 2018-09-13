package sample.MarketSingeltons;

import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Category;
import sample.MarketModel.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDataBaseSingleton {
    private static SellerDataBaseSingleton ourInstance = new SellerDataBaseSingleton();

    public static SellerDataBaseSingleton getInstance() {
        return ourInstance;
    }

    private SellerDataBaseSingleton() {
    }






    public  String addSeller(User user)
    {
        String data_base_message = "";
        try {
            Connection connection = Config.getInstance().getConnection();
            String sql = "INSERT INTO MarketUser(fname,lname,password,created_at,email,role) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, Auth.getInstance().md5(user.getPassword()));
            preparedStatement.setTimestamp(4,getCurrentTimeStamp());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, String.valueOf(user.isRole()));
            preparedStatement.executeUpdate();

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
        } catch (ClassNotFoundException e) {
            data_base_message = e.getMessage();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return data_base_message;

    }
    public  String deleteSeller(User User)
    {
        String meaasge = "";
        String sql = String.format("DELETE   FROM MARKETUSER WHERE  EMAIL='%s'", User.getEmail());
        try {
            Connection connection = Config.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            meaasge = e.getMessage();
        }
        return meaasge;

    }
    public  String updateSeller(User User)
    {
        String data_base_message = "";
        try {
            Connection connection = Config.getInstance().getConnection();
            String sql = "UPDATE MARKETUSER SET  FNAME = ? , LNAME = ? , PASSWORD =? WHERE  EMAIL = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, User.getFirstName());
            preparedStatement.setString(2, User.getLastName());
            preparedStatement.setString(3, User.getPassword());

            preparedStatement.setString(4, User.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
        } catch (ClassNotFoundException e) {
            data_base_message = e.getMessage();
        }

        return data_base_message;
    }

    public List<User> getAllSeller() throws SQLException, ClassNotFoundException {
        Connection connection = null;
//        product_name,product_price,production_date,expired_date,production_company,admin_email
        connection = Config.getInstance().getConnection();

        String sql = "SELECT  * FROM  MARKETUSER where  ROLE='false'  ORDER by EMAIL ASC ";
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);

        ResultSet SET = statement.executeQuery(sql);
        List<User> users = new ArrayList<>(20);
        while (SET.next()) {


            String fname = SET.getString("FNAME") ;
            String lname = SET.getString("LNAME")  ;
            String pass = SET.getString("PASSWORD")  ;
            String email = SET.getString("EMAIL");

            User user = User.newUser().
                    firstName(fname).
                    lastName(lname).
                    password(pass).
                    email(email).
                    build();

            users.add(user);

        }
        return users;
    }

    private  java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
}
