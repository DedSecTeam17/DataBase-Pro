package sample.MarketSingeltons;

import sample.Auth;
import sample.Log;
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


//    public  static  void  main(String a[])
//    {
//        User user= User.newUser().firstName("mohammed").lastName("elamin").email("mohammed@gmial.com").password("mohamed1337").role(true).build();
//      System.out.println(  getInstance().addAdmin(user));
//    }
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
    public  String  loginAdmin(User user)
    {
        Connection connection = null;
        User loggedUser;
        List<User> rules = new ArrayList<>(20);
        try {
            connection = Config.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            String sql = String.format("SELECT  * FROM  MarketUser where email='%s' and password='%s' ",user.getEmail(),Auth.getInstance().md5(user.getPassword()));
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet set = statement.executeQuery(sql);


            while (set.next()) {
                String email = set.getString("email");
                 loggedUser= User.newUser().email(email).build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return  user.getEmail();
    }



}
