package sample.MarketProvider;

import sample.MarketModel.Product;
import sample.MarketModel.User;

import java.sql.SQLException;
import java.util.List;

public interface AdminPrivilidgeOpertation
    {


    public List<User> login(User user);
    public  List<Product> getAllAdminProducts() throws SQLException, ClassNotFoundException;
}
