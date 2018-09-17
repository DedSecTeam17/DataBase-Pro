package sample.MarketProvider;

import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;
import sample.MarketSingeltons.AdminDataBaseSingleton;

import java.sql.SQLException;
import java.util.List;

public class FacadeMarketProvider {

    AdminOperations adminOperations;
    CartOperations cartOperations;
    CategoryOperations categoryOperations;
    ProductOperations productOperations;
    TransactionsOperations transactionsOperations;
    SellerOperations sellerOperations;

    public FacadeMarketProvider() {
        adminOperations = new AdminOperations();
        cartOperations = new CartOperations();
        categoryOperations = new CategoryOperations();
        productOperations = new ProductOperations();
        transactionsOperations = new TransactionsOperations();
        sellerOperations = new SellerOperations();
    }


    public String insertAdmin(User user) {
        return adminOperations.insertUser(user);
    }

    public String updateAdmin(User user) {
        return adminOperations.updateUser(user);
    }


    public String deleteAdmin(User user) {
        return adminOperations.deleteUser(user);
    }


    public List<User> loginAdmin(User user) {
        return adminOperations.login(user);
    }
    public List<User> loginSeller(User user) {
        return sellerOperations.login(user);
    }
    //
    public String insertSeller(User user) {
        return sellerOperations.insertUser(user);
    }

    public String updateSeller(User user) {
        return sellerOperations.updateUser(user);
    }


    public String deleteSeller(User user) {
        return sellerOperations.deleteUser(user);
    }


    public String insertProduct(Product product) {
        return productOperations.insertProduct(product);
    }
    public  User getCurrrentSeller() throws SQLException, ClassNotFoundException {
        return  sellerOperations.getCurrrentSeller();
    }


    public String updateProduct(Product product) {
        return productOperations.updateProduct(product);
    }


    public String deleteProduct(Product product) {
        return productOperations.deleteProduct(product);
    }


    public String insertCategory(Category Category) {
        return categoryOperations.insertCategory(Category);
    }

    public  void  updateProductQuantity(String productName,int quantity)
    {
       productOperations.updateProductQuantity(productName,quantity);
    }
    public String updateCategory(Category Category) {
        return categoryOperations.updateCategory(Category);
    }


    public String deleteCategory(Category Category) {
        return categoryOperations.deleteCategory(Category);
    }

    public List<Category> getAllCategories() throws SQLException, ClassNotFoundException {
        return categoryOperations.getAllCategories();
    }
    public  List<Category> getAllCategoryForAllAdmins() throws SQLException, ClassNotFoundException {
        return categoryOperations.getAllCategoryForAllAdmins();
    }
    public  Category getCategoryById(int id) throws SQLException, ClassNotFoundException {
        return  categoryOperations.getItemById(id);
    }

    public List<User> getAllSellers() throws SQLException, ClassNotFoundException {
        return sellerOperations.getAllSellers();
    }
    public List<Transaction> getAllTransactions() throws SQLException, ClassNotFoundException {
        return  transactionsOperations.getAllTransaction();
    }

//

    public String insertTransaction(Transaction transaction) {
        return transactionsOperations.insertTransaction(transaction);
    }


    public String updateTransaction(Transaction transaction) {
        return transactionsOperations.updateTransaction(transaction);
    }


    public String deleteTransaction(Transaction transaction) {
        return transactionsOperations.deleteTransaction(transaction);
    }


    //    repeort
    public List<Product> getAllProduct() throws SQLException, ClassNotFoundException {
        return productOperations.getAllAdminProducts();
    }

    public List<Product> getAllProductForSellers() throws SQLException, ClassNotFoundException {
        return productOperations.getAllProductForSellers();
    }

}
