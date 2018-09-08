package sample.MarketProvider;

import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;
import sample.MarketSingeltons.AdminDataBaseSingleton;

import java.util.List;

public class AdminOperations implements CrudOperations,AdminPrivilidgeOpertation {
    @Override
    public String insertUser(User user) {
        return AdminDataBaseSingleton.getInstance().addAdmin(user);
    }

    @Override
    public String updateUser(User user) {
        return AdminDataBaseSingleton.getInstance().updateAdmin(user);
    }

    @Override
    public String deleteUser(User user) {
        return AdminDataBaseSingleton.getInstance().deleteAdmin(user);
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
    public List<User> login(User user) {
        return AdminDataBaseSingleton.getInstance().loginAdmin(user);
    }

    @Override
    public List<Product> getAllAdminProducts() {
        return null;
    }


}
