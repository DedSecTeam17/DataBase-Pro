package sample.Seller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SellerController implements Initializable {

    @FXML private JFXListView productsList;

    @FXML private AnchorPane transactionDialog;

    @FXML private AnchorPane cartPane;

    @FXML private JFXTreeTableView cart;

    @FXML JFXButton startTransactionButton;


    @FXML JFXTextField spField;
    @FXML JFXTextField quantityField;

    //save transaction info
    public class transaction
    {
        transaction(String name , int sp , int quantity)
        {
            this.name = name;
            this.sellingPrice = sp;
            this.quantity = quantity;
        }
        String name;
        int sellingPrice;
        int quantity;

    }

    ArrayList<transaction> cartList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        for (int i = 0; i < 20; i++) { testList(); }

        //show the transaction dialog (selling price,quantity fields ) after selecting the product you wanna add
        startTransactionButton.setOnAction(event -> transactionDialog.setVisible(!transactionDialog.isVisible()));
    }

    @FXML public void cartButtonClicked()
    {
        cartPane.setVisible(true);
    }

    @FXML public void addToCart()
    {

        //add a new item to cart after selecting the name
        //from the list view and the selling price ,quantity
        //from the text fields

        String name = ((Label)(productsList.getSelectionModel().getSelectedItem())).getText();
        int sp = Integer.parseInt(spField.getText()) ;
        int quantity = Integer.parseInt(quantityField.getText()) ;

        transaction temp = new transaction(name , sp , quantity);
        cartList.add(temp);
        hideAllPanes();
    }

    void testList()
    {
        //just to test the JFX list view
        Label dummy_item = new Label("dummy item");
        productsList.getItems().add(dummy_item);
    }


    void hideAllPanes()
    {
        //clear panes on screen
        cartPane.setVisible(false);
        transactionDialog.setVisible(false);
    }

    @FXML void cartCheckOut()
    {
        //Code add the transactions list to the transactions table in the database
    }
}
