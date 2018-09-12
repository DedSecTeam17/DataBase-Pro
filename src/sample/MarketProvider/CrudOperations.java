package sample.MarketProvider;

import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;

import java.sql.SQLException;
import java.util.List;

public interface CrudOperations {

    //    user operation
    public String insertUser(User user);

    public String updateUser(User user);

    public String deleteUser(User user);


    //    category operation
    public String insertProduct(Product product);

    public String updateProduct(Product product);

    public String deleteProduct(Product product);
    public  Category getItemById(int id) throws SQLException, ClassNotFoundException;

//

    public String insertCategory(Category Category);

    public String updateCategory(Category Category);

    public String deleteCategory(Category Category);
//    transaction operation

    public String insertTransaction(Transaction transaction);

    public String updateTransaction(Transaction transaction);

    public String deleteTransaction(Transaction transaction);

    public List<Transaction> getAllTransaction() throws SQLException, ClassNotFoundException;


//


}
