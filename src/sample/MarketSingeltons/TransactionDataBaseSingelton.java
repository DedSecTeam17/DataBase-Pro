package sample.MarketSingeltons;

import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;

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
            Connection connection = Config.getInstance().getConnection();
            String sql = "INSERT INTO MARKETTRANSACTIONS(T_USER_EMAIL,T_PRODUCT_NAME,TRANSACTION_DATE,SELLING_PRICE,QUANTITY,TRANSACTION_TOTAL,TRANSACTION_PROFIT) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Auth.getInstance().getCurrentUser());
            preparedStatement.setString(2, Transaction.getProductName());
            preparedStatement.setDate(3, Date.valueOf(Transaction.getCreated_at()));
            preparedStatement.setInt(4, Transaction.getSellingPrice());
            preparedStatement.setInt(5,Transaction.getQuantity() );
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
        String sql = String.format("DELETE FROM MARKETTRANSACTIONS WHERE  Transaction_id='%d'", Transaction.getId());
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
            String sql = "UPDATE MarketProduct SET  SELLING_PRICE = ? ,QUANTITY = ? WHERE  Transaction_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, Transaction.getSellingPrice());
            preparedStatement.setInt(2, Transaction.getQuantity());
            preparedStatement.setInt(3, Transaction.getId());

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
}
