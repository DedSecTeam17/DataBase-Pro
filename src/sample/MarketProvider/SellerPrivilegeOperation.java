package sample.MarketProvider;

import sample.MarketModel.Product;

import java.sql.SQLException;
import java.util.List;

public interface SellerPrivilegeOperation {


    public List<Product> getAllProductForSellers() throws SQLException, ClassNotFoundException;
}
