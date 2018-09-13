package sample.AdminUI.fragmnets;

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
import sample.MarketModel.Transaction;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

public class TransactionsFragment {
    public static final double COLUMN_WIDTH =831/7 ;
    public ObservableList<TransactionsFragment.transactionItem> codeObservableList;
    public FacadeMarketProvider facadeMarketProvider=new FacadeMarketProvider();
    public  void  onTableItemSelected(JFXTextField idField ,JFXTextField userEmailField, JFXTextField productNameField, JFXTextField sellingPriceField, JFXTextField quantityField, JFXTreeTableView treeTableView)
    {
        treeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log.e("clicked");
                int r_index = treeTableView.getSelectionModel().getSelectedIndex();
                TransactionsFragment.transactionItem RuleItem = codeObservableList.get(r_index);
                StringProperty id = RuleItem.id;
                StringProperty userEmail = RuleItem.userEmail;
                StringProperty productName = RuleItem.productName;
                StringProperty sellingPrice = RuleItem.sellingPrice;
                StringProperty quantity = RuleItem.quantity;

                idField.setText(id.getValue());
                userEmailField.setText(userEmail.getValue());
                productNameField.setText(productName.getValue());
                sellingPriceField.setText(sellingPrice.get());
                quantityField.setText(quantity.getValue());

            }
        });

    }
    public void deleteTransaction(JFXTextField id,JFXTextField userEmail, JFXTextField productName, JFXTextField sellingPrice, JFXTextField quantity,Label hint_id,Label hint_userEmail,Label hint_productName,Label hint_sellingPrice ,Label hint_quantity, JFXTreeTableView treeTableView)
    {
        int r_index = treeTableView.getSelectionModel().getSelectedIndex();
        TransactionsFragment.transactionItem RuleItem = codeObservableList.get(r_index);

        StringProperty getIdForSelectedItem = RuleItem.id;
        int transaction_id = Integer.parseInt(getIdForSelectedItem.getValue());

        Transaction transaction = Transaction.newTransaction().id(transaction_id).build();

        facadeMarketProvider.deleteTransaction(transaction);
        clearFields(id,userEmail,productName,sellingPrice,quantity,hint_id,hint_userEmail,hint_productName,hint_sellingPrice,hint_quantity);
        try {
            TransactionTableColumn(treeTableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(transaction_id+"");
    }
    public void updateTransaction(JFXTextField id,JFXTextField userEmail, JFXTextField productName, JFXTextField sellingPrice, JFXTextField quantity, Label hint_id,Label hint_userEmail,Label hint_productName,Label hint_sellingPrice ,Label hint_quantity, JFXTreeTableView treeTableView) throws Exception
    {
        if (!id.getText().equals("") && !userEmail.getText().equals("") && !productName.getText().equals("") && !sellingPrice.getText().equals("") && !quantity.getText().equals("")) {
            Transaction transaction = Transaction.newTransaction()
                    .id(Integer.parseInt(id.getText()))
                    .email(userEmail.getText())
                    .productName(productName.getText())
                    .sellingPrioce(Integer.parseInt(sellingPrice.getText()))
                    .quantity(Integer.parseInt(quantity.getText()))
                    .build();


            facadeMarketProvider.updateTransaction(transaction);
            TransactionTableColumn(treeTableView);
            clearFields(id,userEmail,productName,sellingPrice,quantity,hint_id,hint_userEmail,hint_productName,hint_sellingPrice,hint_quantity);
        } else {
            UiValidation.validateInput(id.getText(), hint_id, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "num");
            UiValidation.validateInput(userEmail.getText(), hint_userEmail, "empty filed not allowed", "not valid email", "valid", "email");
            UiValidation.validateInput(productName.getText(), hint_productName, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(sellingPrice.getText(), hint_sellingPrice, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "num");
            UiValidation.validateInput(quantity.getText(),hint_quantity,"empty filed not allowed" , "only numbers","valid","num");

     }
    }
    private void clearFields(JFXTextField id, JFXTextField userEmail, JFXTextField productName, JFXTextField sellingPrice, JFXTextField quantity, Label hint_id, Label hint_userEmail, Label hint_productName, Label hint_sellingPrice, Label hint_quantity)
    {
        id.setEditable(true);
        userEmail.clear();
        productName.clear();
        sellingPrice.clear();
        quantity.clear();
//
        hint_id.setText("");
        hint_userEmail.setText("");
        hint_productName.setText("");
        hint_sellingPrice.setText("");
        hint_quantity.setText("");
    }










    //    Transaction Table
    public void TransactionTableColumn(JFXTreeTableView treeTableView) throws Exception
    {
        JFXTreeTableColumn<TransactionsFragment.transactionItem, String> id = new JFXTreeTableColumn<>("id");
        id.setPrefWidth(COLUMN_WIDTH);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String> param) {
                return param.getValue().getValue().id;
            }
        });


        JFXTreeTableColumn<TransactionsFragment.transactionItem, String> userEmail = new JFXTreeTableColumn<>("User Email");
        userEmail.setPrefWidth(COLUMN_WIDTH);
        userEmail.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String> param) {
                return param.getValue().getValue().userEmail;
            }
        });


        JFXTreeTableColumn<TransactionsFragment.transactionItem, String> productName = new JFXTreeTableColumn<>("Product name");
        productName.setPrefWidth(COLUMN_WIDTH);
        productName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String> param) {
                return param.getValue().getValue().productName;
            }
        });


        JFXTreeTableColumn<TransactionsFragment.transactionItem, String> sellingPrice = new JFXTreeTableColumn<>("Selling Price");
        sellingPrice.setPrefWidth(COLUMN_WIDTH);
        sellingPrice.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        JFXTreeTableColumn<TransactionsFragment.transactionItem, String> quantity = new JFXTreeTableColumn<>("Quantity");
        quantity.setPrefWidth(COLUMN_WIDTH);
        quantity.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TransactionsFragment.transactionItem, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        try {
        } catch (Exception e) {
            e.printStackTrace();
        }

        codeObservableList = FXCollections.observableArrayList();
        for (Transaction transaction: facadeMarketProvider.getAllTransactions())
        {

            codeObservableList.add(new TransactionsFragment.transactionItem(transaction.getId(),transaction.getUserEmail(), transaction.getProductName(), transaction.getSellingPrice(),transaction.getQuantity()));

        }
        final TreeItem<TransactionsFragment.transactionItem> root = new RecursiveTreeItem<>(codeObservableList, RecursiveTreeObject::getChildren);


        treeTableView.getColumns().setAll(id,userEmail,productName,sellingPrice,quantity);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

    }
    class transactionItem extends RecursiveTreeObject<TransactionsFragment.transactionItem>
    {
        StringProperty id;
        StringProperty userEmail;
        StringProperty productName;
        StringProperty sellingPrice;
        StringProperty quantity;

        public transactionItem(int id, String userEmail, String productName, int sellingPrice, int quantity) {
            this.id = new SimpleStringProperty(String.valueOf(id));
            this.userEmail = new SimpleStringProperty(userEmail);
            this.productName = new SimpleStringProperty(productName);
            this.sellingPrice = new SimpleStringProperty(String.valueOf(sellingPrice));
            this.quantity = new SimpleStringProperty(String.valueOf(quantity));
        }
    }
}
