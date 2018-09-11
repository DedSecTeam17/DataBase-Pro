package sample.SellerUI.Fragments;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.Debugging.Log;
import sample.MarketModel.Product;
import sample.MarketModel.Transaction;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

import java.util.ArrayList;
import java.util.List;

public class CartFragments {


    private ObservableList<CartItem> codeObservableList;
    private static final double COLUMN_WIDTH = 322 / 3;
    private FacadeMarketProvider facadeMarketProvider = new FacadeMarketProvider();

    public void SellAllProducts(List<Transaction> transactionList) throws Exception {
        int totalCount = 0;
        for (Transaction transaction : transactionList) {
            totalCount += transaction.getSellingPrice();
        }
        Log.i(String.valueOf(totalCount));
    }


    public void deleteCartItem(JFXTreeTableView cartTable, List<Transaction> transactionList) {
        int r_index = cartTable.getSelectionModel().getSelectedIndex();
        CartItem categorytItem = codeObservableList.get(r_index);
        StringProperty _name = categorytItem.productName;
        String name = _name.getValue();

        for (Transaction transaction : transactionList) {
            if (transaction.getProductName().equals(name)) {
                int index = transactionList.indexOf(transaction);
                transactionList.remove(index);
                try {
                    CartTableColumn(cartTable, transactionList);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearFields(JFXTextField quantity, Label selectedItemName, Label selectItemSellingPrice) {
        quantity.clear();
        selectedItemName.setText("");
        selectItemSellingPrice.setText("");
    }

    public void CartTableColumn(JFXTreeTableView cat_tree_table, List<Transaction> transactions) throws Exception {
        JFXTreeTableColumn<CartItem, String> name = new JFXTreeTableColumn<>("product name");
        name.setPrefWidth(COLUMN_WIDTH);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CartItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CartItem, String> param) {
                return param.getValue().getValue().productName;
            }
        });


        JFXTreeTableColumn<CartItem, String> price = new JFXTreeTableColumn<>("product selling price");
        price.setPrefWidth(COLUMN_WIDTH);
        price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CartItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CartItem, String> param) {
                return param.getValue().getValue().productSellingPrice;
            }
        });

        JFXTreeTableColumn<CartItem, String> quantity = new JFXTreeTableColumn<>("product quantity");
        quantity.setPrefWidth(COLUMN_WIDTH);
        quantity.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<CartItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<CartItem, String> param) {
                return param.getValue().getValue().productQuantity;
            }
        });


        try {


        } catch (Exception e) {
            e.printStackTrace();


        }
        codeObservableList = FXCollections.observableArrayList();
        for (Transaction transaction : transactions
                ) {
            codeObservableList.add(new CartItem(transaction.getProductName(), transaction.getSellingPrice(), transaction.getQuantity()));
        }
        final TreeItem<CartItem> root = new RecursiveTreeItem<CartItem>(codeObservableList, RecursiveTreeObject::getChildren);
        cat_tree_table.getColumns().setAll(name, price, quantity);
        cat_tree_table.setRoot(root);
        cat_tree_table.setShowRoot(false);


    }

    class CartItem extends RecursiveTreeObject<CartItem> {
        StringProperty productName;
        StringProperty productSellingPrice;
        StringProperty productQuantity;

        public CartItem(String productName, int productSellingPrice, int productQuantity) {
            this.productName = new SimpleStringProperty(productName);
            this.productSellingPrice = new SimpleStringProperty(String.valueOf(productSellingPrice));
            this.productQuantity = new SimpleStringProperty(String.valueOf(productQuantity));

        }
    }


}
