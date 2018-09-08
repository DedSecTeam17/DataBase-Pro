package sample.MarketProvider;

import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;
import sample.MarketSingeltons.CategoryDataBaseSingleton;

import java.sql.SQLException;
import java.util.List;

public class CategoryOperations implements CrudOperations {
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
    public String insertCategory(Category Category) {
        return CategoryDataBaseSingleton.getInstance().addCategory(Category);
    }

    @Override
    public String updateCategory(Category Category) {
        return CategoryDataBaseSingleton.getInstance().updateCategory(Category);
    }

    @Override
    public String deleteCategory(Category Category) {
        return CategoryDataBaseSingleton.getInstance().deleteCategory(Category);
    }

    public List<Category> getAllCategories() throws SQLException, ClassNotFoundException {
        return  CategoryDataBaseSingleton.getInstance().getAllCategory();
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
}
