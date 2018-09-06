package sample.MarketSingeltons;

import sample.MarketModel.Product;
import sample.MarketModel.Transaction;

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
        return  "message come from server";

    }
    public  String deleteTransaction(Transaction Transaction)
    {
        return  "message come from server";

    }
    public  String updateTransaction(Transaction Transaction)
    {
        return  "message come from server";

    }

    public List<Transaction> getAllTransactionGroupedWithSellerEmailOrderedwithHighProfit()
    {
        return  null;
    }
}
