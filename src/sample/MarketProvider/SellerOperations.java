package sample.MarketProvider;

import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;
import sample.MarketSingeltons.AdminDataBaseSingleton;
import sample.MarketSingeltons.CategoryDataBaseSingleton;
import sample.MarketSingeltons.SellerDataBaseSingleton;

import java.sql.SQLException;
import java.util.List;

public class SellerOperations implements CrudOperations,AdminPrivilidgeOpertation {
    @Override
    public String insertUser(User user) {
        return SellerDataBaseSingleton.getInstance().addSeller(user);
    }

    @Override
    public String updateUser(User user) {
        return SellerDataBaseSingleton.getInstance().updateSeller(user);
    }

    @Override
    public String deleteUser(User user) {
        return SellerDataBaseSingleton.getInstance().deleteSeller(user);
    }

    public List<User> getAllSellers() throws SQLException, ClassNotFoundException {
        return  SellerDataBaseSingleton.getInstance().getAllSeller();
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
        return null;
    }

    @Override
    public String updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public String deleteTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransaction() throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public List<User> login(User user) {
        return SellerDataBaseSingleton.getInstance().loginSeller(user);
    }

    @Override
    public List<Product> getAllAdminProducts() throws SQLException, ClassNotFoundException {
        return null;
    }
    public  User  getCurrrentSeller() throws SQLException, ClassNotFoundException {
        return  SellerDataBaseSingleton.getInstance().getCurrentSeller();
    }
}
