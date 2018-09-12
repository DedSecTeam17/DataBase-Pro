package sample.MarketSingeltons;

import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketProvider.ProductOperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDataBaseSingelton {

    private static TransactionDataBaseSingelton ourInstance = new TransactionDataBaseSingelton();

    public static TransactionDataBaseSingelton getInstance() {
        return ourInstance;
    }
    private TransactionDataBaseSingelton() {
    }
    public  String addTransaction(Transaction Transaction)
    {
        String data_base_message = "";
        try {

            //Calculate The Profit before adding it to the dataBase Table
            //By getting the price from the Products table and subtracting it from the Selling Price
            //in the transactions table
            Transaction.setProfit(Transaction.getSellingPrice() - getProductPrice(Transaction.getProductName()));

            Connection connection = Config.getInstance().getConnection();
            String sql = "INSERT INTO MARKETTRANSACTIONS(TRANSACTION_ID,TRANSACTION_USER_EMAIL,TRANSACTION_PRODUCT_NAME,TRANSACTION_SELLING_PRICE,TRANSACTION_QUANTITY,TRANSACTION_PROFIT,TRANSACTION_DATE) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Transaction.getId());
            preparedStatement.setString(2, Transaction.getUserEmail());
            preparedStatement.setString(3, Transaction.getProductName());
            preparedStatement.setInt(4, Transaction.getQuantity());
            preparedStatement.setInt(5,Transaction.getSellingPrice() );
            preparedStatement.setInt(6,Transaction.getProfit());
            preparedStatement.setTimestamp(7,getCurrentTimeStamp());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
        } catch (ClassNotFoundException e) {
            data_base_message = e.getMessage();
        }
        return data_base_message;
    }
    public  String deleteTransaction(Transaction Transaction)
    {
        String meaasge = "";
        String sql = String.format("DELETE FROM MARKET_TRANSACTIONS WHERE  TRANSACTION_ID='%d'", Transaction.getId());
        try {
            Connection connection = Config.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            meaasge = e.getMessage();
        }
        return meaasge;
    }
    public  String updateTransaction(Transaction Transaction)
    {
        String data_base_message = "";
        try {
            Connection connection = Config.getInstance().getConnection();
            String sql = "UPDATE MARKET_TRANSACTIONS SET  TRANSACTION_SELLING_PRICE = ? ,TRANSACTION_QUANTITY = ? WHERE  TRANSACTION_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Transaction.getId());
            preparedStatement.setInt(2, Transaction.getSellingPrice());
            preparedStatement.setInt(3, Transaction.getQuantity());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Log.i(e.getMessage());
            data_base_message = e.getMessage();
        } catch (ClassNotFoundException e) {
            data_base_message = e.getMessage();
        }

        return data_base_message;

    }

    public List<Transaction> getAllTransactionGroupedWithSellerEmailOrderedwithHighProfit() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        connection = Config.getInstance().getConnection();

        String sql = String.format("SELECT  * FROM  MARKETTRANSACTIONS WHERE  EMAIL='%s'  ORDER  by TRANSACTION_PROFIT Desc ",
                Auth.getInstance().getCurrentUser());
        PreparedStatement statement = null;
        statement = connection.prepareStatement(sql);

        ResultSet SET = statement.executeQuery(sql);
        List<Transaction> transactions = new ArrayList<>(20);
        while (SET.next()) {
            int transactio_id = SET.getInt("Transaction_id");
            String productName = SET.getString("T_PRODUCT_NAME");
            int sellingPrice = SET.getInt("SELLING_PRICE");
            int quantity = SET.getInt("QUANTITY");
            int profit = SET.getInt("TRANSACTION_PROFIT");
            String createdAt = SET.getString("TRANSACTION_DATE");


            Transaction transaction = Transaction.newTransaction().id(transactio_id).
                    productName(productName).
                    sellingPrioce(sellingPrice).
                    quantity(quantity).
                    profit(profit).
                    created_at(createdAt)
                    .build();

            transactions.add(transaction);
        }
        return transactions;
    }

    private int getProductPrice(String product_Name) throws SQLException, ClassNotFoundException {
        Connection connection = Config.getInstance().getConnection();
        String query = String.format("SELECT * FROM MARKETPRODUCT WHERE PRODUCT_NAME = '%s'",product_Name);

        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet set = statement.executeQuery();
        int productPrice = 0;
        while (set.next())
        {
            productPrice = set.getInt("PRODUCT_PRICE");
        }
        return productPrice;
    }

    private  java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

}
