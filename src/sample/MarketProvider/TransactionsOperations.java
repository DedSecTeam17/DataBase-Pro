package sample.MarketProvider;

import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;
import sample.MarketSingeltons.TransactionDataBaseSingelton;

import java.sql.SQLException;
import java.util.List;

public class TransactionsOperations implements CrudOperations {
    @Override
    public String insertUser(User user) {
        return null;
    }

    @Override
    public String updateUser(User user) {
        return null;
    }

    @Override
    public String deleteUser(User user) {
        return null;
    }

    @Override
    public String insertProduct(Product product) {
        return null;
    }

    @Override
    public String updateProduct(Product product) {
        return null;
    }

    @Override
    public String deleteProduct(Product product) {
        return null;
    }

    @Override
    public Category getItemById(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String insertCategory(Category Category) {
        return null;
    }

    @Override
    public String updateCategory(Category Category) {
        return null;
    }

    @Override
    public String deleteCategory(Category Category) {
        return null;
    }

    @Override
    public String insertTransaction(Transaction transaction) {
        return TransactionDataBaseSingelton.getInstance().addTransaction(transaction);
    }

    @Override
    public String updateTransaction(Transaction transaction) {
        return TransactionDataBaseSingelton.getInstance().updateTransaction(transaction);
    }

    @Override
    public String deleteTransaction(Transaction transaction) {
        return TransactionDataBaseSingelton.getInstance().deleteTransaction(transaction);
    }

    @Override
    public List<Transaction> getAllTransaction() throws SQLException, ClassNotFoundException {
        return TransactionDataBaseSingelton.getInstance().getAllTransactionGroupedWithSellerEmailOrderedwithHighProfit();
    }
}
