package sample.SellerUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Transaction;
import sample.SellerUI.Fragments.SellerProductsFragment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SellerController implements Initializable {

    @FXML
    private JFXTreeTableView<?> seller_products_table;

    @FXML
    private JFXButton add_item_to_cart;

    @FXML
    private Label selected_product_name;

    @FXML
    private JFXTextField customer_quantity;

    @FXML
    private Label selected_product_price;

    @FXML
    private Label quantity_hint;

    @FXML
    private JFXTreeTableView<?> cart_table;

    @FXML
    private JFXButton delete_cart_item;

    @FXML
    private JFXButton add_all_cart_item_into_transaction;

    private SellerProductsFragment sellerProductsFragment;
    private List<Transaction> transactions;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transactions=new ArrayList<>();
        sellerProductsFragment=new SellerProductsFragment();
        try {
            sellerProductsFragment.CartTableColumn(seller_products_table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sellerProductsFragment.onTableItemSelected(seller_products_table,selected_product_name,selected_product_price);
        add_item_to_cart.setOnAction(event ->
        {
            try {
                sellerProductsFragment.addCategoryItem(customer_quantity,quantity_hint,selected_product_name,selected_product_price,seller_products_table,transactions);
                for (Transaction transaction:transactions)
                {
                    Log.i(transaction.getProductName()+" \t"+transaction.getSellingPrice());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



    }

}
