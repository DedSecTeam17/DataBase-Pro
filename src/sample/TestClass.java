package sample;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// this class used for testing purpose
public class TestClass {

// Testing oracle 10g connection and select items from DB
public static  void  main(String a[])
{



    System.out.println("-------- Oracle JDBC Connection Testing ------");


    try {

        Class.forName("oracle.jdbc.driver.OracleDriver");

    } catch (ClassNotFoundException e) {

        System.out.println("Where is your Oracle JDBC Driver?");
        e.printStackTrace();
        return;

    }

    System.out.println("Oracle JDBC Driver Registered!");

    Connection connection = null;
    try {
        connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:hr", "hr", "hr");
        String sql = "SELECT  * FROM  employees ";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet set = statement.executeQuery(sql);
        List<String> rules = new ArrayList<>();
        while (set.next()) {
            String first_name = set.getString("FIRST_NAME");
            String last_name = set.getString("LAST_NAME");
            rules.add(first_name+'\t'+last_name);
        }
        for (String name: rules ) {
            System.out.println(name);
        }
    } catch (SQLException e) {
        System.out.println("Connection Failed! Check output console");
        e.printStackTrace();
        return;
    }

    if (connection != null) {
        System.out.println("You made it, take control your database now!");
    } else {
        System.out.println("Failed to make connection!");
    }


}

}
