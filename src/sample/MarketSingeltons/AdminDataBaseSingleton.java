package sample.MarketSingeltons;

import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.User;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDataBaseSingleton {
    private static AdminDataBaseSingleton ourInstance = new AdminDataBaseSingleton();
    public static AdminDataBaseSingleton getInstance() {
        return ourInstance;
    }
    private AdminDataBaseSingleton() {
    }
    public  String addAdmin(User user)
    {

        String data_base_message="";

        try {
            Connection connection=Config.getInstance().getConnection();
            String sql = "INSERT INTO MarketUser(fname,lname,password,created_at,email,role) VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, Auth.getInstance().md5(user.getPassword()));
            preparedStatement.setTimestamp(4,getCurrentTimeStamp());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, String.valueOf(user.isRole()));
            preparedStatement.executeUpdate();



        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message=e.getMessage();

        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();

        }

        return  data_base_message;

    }
    public  String deleteAdmin(User user)
    {
        return  "message come from server";

    }
    public  String updateAdmin(User user)
    {
        return  "message come from server";
    }
    private  java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }
    public  List<User>  loginAdmin(User user)
    {
        String data_base_message="";
        String email="";
        String role="";
        Connection connection = null;
        User loggedUser;
        List<User> selected_user=new ArrayList<>();
        try {
            connection = Config.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            data_base_message=e.getMessage();
        }

        try {

            String sql = String.format("SELECT  * FROM  MarketUser where email='%s' and password='%s' and role='%s' ",user.getEmail(),Auth.getInstance().md5(user.getPassword()),String.valueOf(user.isRole()));
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet set = statement.executeQuery(sql);



            while (set.next()) {
             email = set.getString("email");
             role = set.getString("role");
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            data_base_message=e.getMessage();
        }
        loggedUser= User.newUser().email(email).role(Boolean.parseBoolean(role)).message(data_base_message). build();
        selected_user.add(loggedUser);
        return  selected_user;
    }



}
