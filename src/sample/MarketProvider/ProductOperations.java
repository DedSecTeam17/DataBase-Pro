package sample.MarketProvider;

import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;
import sample.MarketSingeltons.ProductDataBaseSingleton;

import java.sql.SQLException;
import java.util.List;

public class ProductOperations implements CrudOperations ,AdminPrivilidgeOpertation,SellerPrivilegeOperation {
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

    //
    @Override
    public String insertProduct(Product product) {
        return ProductDataBaseSingleton.getInstance().addProduct(product);
    }

    @Override
    public String updateProduct(Product product) {
        return ProductDataBaseSingleton.getInstance().updateProduct(product);
    }

    @Override
    public String deleteProduct(Product product) {
        return ProductDataBaseSingleton.getInstance().deleteProduct(product);
    }

    //
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
        return null;
    }

    @Override
    public List<Product> getAllAdminProducts() throws SQLException, ClassNotFoundException {

        return ProductDataBaseSingleton.getInstance().getAllProduct();
    }

    @Override
    public List<Product> getAllProductForSellers() throws SQLException, ClassNotFoundException {

        return ProductDataBaseSingleton.getInstance().getAllProductForSellers();
    }
}
