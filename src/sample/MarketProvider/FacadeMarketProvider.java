package sample.MarketProvider;

import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;

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


    public String updateProduct(Product product) {
        return productOperations.updateProduct(product);
    }


    public String deleteProduct(Product product) {
        return productOperations.deleteProduct(product);
    }


    public String insertTransaction(Transaction transaction) {
        return transactionsOperations.insertTransaction(transaction);
    }


    public String updateTransaction(Transaction transaction) {
        return transactionsOperations.updateTransaction(transaction);
    }


    public String deleteTransaction(Transaction transaction) {
        return transactionsOperations.deleteTransaction(transaction);
    }


}
