package sample.MarketSingeltons;

import sample.Debugging.Log;

import java.sql.*;

public class Config {
    private static final String USER_NAME = "hr";
    private static final String DB_PASSWORD ="hr" ;
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:hr";
    private static Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
    }
    
    
    
    public  Connection  getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection connection = DriverManager.getConnection(
                DB_URL, USER_NAME, DB_PASSWORD);
        if (connection != null) {
            Log.i("You made it, take control your database now!");
        } else {
           Log.e("Failed to make connection!");
        }
        return  connection;
    }
}
