package sample.SellerUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.AdminUI.fragmnets.CategoryFragment;
import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Transaction;
import sample.MarketModel.User;
import sample.MarketProvider.FacadeMarketProvider;
import sample.SellerUI.Fragments.CartFragments;
import sample.SellerUI.Fragments.SellerProductsFragment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    private Label selected_product_price;

    @FXML
    private Label quantity_hint;

    @FXML
    private JFXButton decrese;

    @FXML
    private JFXButton increase;

    @FXML
    private Label current_quantity;

    @FXML
    private JFXTreeTableView<?> cart_table;

    @FXML
    private JFXButton delete_cart_item;

    @FXML
    private JFXButton add_all_cart_item_into_transaction;

    @FXML
    private Label user_name;

    @FXML
    private Label email;

    @FXML
    private Label mony;

    @FXML
    private JFXButton logout;
    @FXML
    private StackPane main_pane;
    private int quan = 0;

    private static final double WINDOW_WIDTH = 800;
    private static final double WINDOW_HEIGHT = 500;


    private SellerProductsFragment sellerProductsFragment;
    private CartFragments cartFragments;
    private List<Transaction> transactions;

    FacadeMarketProvider facadeMarketProvider = new FacadeMarketProvider();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
       User user=facadeMarketProvider.getCurrrentSeller();
                if (user.getEmail().equals(Auth.getInstance().getCurrentUser())) {
                    user_name.setText(user.getFirstName() + "\t" + user.getLastName());
                    email.setText(user.getEmail());

            }


            logout.setOnAction(event -> {
                Auth.getInstance().destroyUser();
                DirectUserWithFade(main_pane, "../Login.fxml");
            });
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        increase.setOnAction(event -> {
            quan += 1;
            current_quantity.setText(String.valueOf(quan));
        });
        decrese.setOnAction(event -> {
            if (quan > 0)
                quan -= 1;
            current_quantity.setText(String.valueOf(quan));
        });


        transactions = new ArrayList<>();
        sellerProductsFragment = new SellerProductsFragment();
        cartFragments = new CartFragments();

        setupSellerProductOperations();
        setupCartOperations();


    }

    private void setupCartOperations() {
        try {
            cartFragments.CartTableColumn(cart_table, transactions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        delete_cart_item.setOnAction(event ->
        {
            try {
                cartFragments.deleteCartItem(cart_table, transactions);
                for (Transaction transaction : transactions) {
                    Log.i(transaction.getProductName() + " \t" + transaction.getSellingPrice());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        add_all_cart_item_into_transaction.setOnAction(event ->
                {
                    try {
                        cartFragments.SellAllProducts(transactions, cart_table,mony);
                        sellerProductsFragment.SellerProductsTableColumn(seller_products_table);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    private void setupSellerProductOperations() {
        try {
            sellerProductsFragment.SellerProductsTableColumn(seller_products_table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sellerProductsFragment.onTableItemSelected(seller_products_table, selected_product_name, selected_product_price);
        add_item_to_cart.setOnAction(event ->
        {
            this.quan = 0;
            try {
                sellerProductsFragment.addCartItem(current_quantity, quantity_hint, selected_product_name, selected_product_price, seller_products_table, transactions);
                cartFragments.CartTableColumn(cart_table, transactions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    private void DirectUserWithFade(StackPane pane, String fxml_file) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(100));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent secondRoot = null;
                try {
                    secondRoot = FXMLLoader.load(getClass().getResource(fxml_file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene newScene = new Scene(secondRoot);

                Stage curStage = (Stage) pane.getScene().getWindow();
                curStage.setMinWidth(WINDOW_WIDTH);
                curStage.setMinHeight(WINDOW_HEIGHT);
                curStage.setMaxHeight(WINDOW_HEIGHT);
                curStage.setMaxWidth(WINDOW_WIDTH);
                curStage.setScene(newScene);
                curStage.show();
            }
        });
        fadeTransition.play();

    }
}
