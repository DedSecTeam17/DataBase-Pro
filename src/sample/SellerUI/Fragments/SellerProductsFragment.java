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

import java.util.List;

public class SellerProductsFragment
{


    private ObservableList<SellerProductsItem> codeObservableList;
    private static final double COLUMN_WIDTH =831/3;
    private FacadeMarketProvider facadeMarketProvider=new FacadeMarketProvider();

    public  void  addCategoryItem(JFXTextField quantity, Label quantity_hint , Label selectedItemName, Label selectItemSellingPrice, JFXTreeTableView sellerProductTable, List<Transaction> transactions) throws Exception {
        if (!quantity.getText().equals("")) {
            Transaction transaction= Transaction.newTransaction()
                    .productName(selectedItemName.getText())
                    .quantity(Integer.parseInt(quantity.getText()))
                    .sellingPrioce(Integer.parseInt(selectItemSellingPrice.getText())).
                            build();
            transactions.add(transaction);
//            updeate new quantity
            SellerProductsTableColumn(sellerProductTable);
            clearFields(quantity,selectedItemName,selectItemSellingPrice);
        } else {
            UiValidation.validateInput(quantity, quantity_hint, "empty filed not allowed", "not valid", "valid", "num");
        }

    }

    public  void  onTableItemSelected(JFXTreeTableView treeTableView,Label name,Label price)
    {
        treeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log.e("clicked");
                int r_index = treeTableView.getSelectionModel().getSelectedIndex();
                SellerProductsItem cartItem = codeObservableList.get(r_index);
                StringProperty _name = cartItem.productName;
                StringProperty _price = cartItem.productSellingPrice;
                name.setText(_name.getValue());
                price.setText(_price.getValue());


            }
        });
    }







    private void clearFields(JFXTextField quantity, Label selectedItemName, Label selectItemSellingPrice)
    {
        quantity.clear();
        selectedItemName.setText("");
        selectItemSellingPrice.setText("");
    }
    public void SellerProductsTableColumn(JFXTreeTableView cat_tree_table) throws Exception {
        JFXTreeTableColumn< SellerProductsItem, String> name = new JFXTreeTableColumn<>("product name");
        name.setPrefWidth(COLUMN_WIDTH);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< SellerProductsItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< SellerProductsItem, String> param) {
                return param.getValue().getValue().productName;
            }
        });


        JFXTreeTableColumn< SellerProductsItem, String> price = new JFXTreeTableColumn<>("product selling price");
        price.setPrefWidth(COLUMN_WIDTH);
        price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< SellerProductsItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< SellerProductsItem, String> param) {
                return param.getValue().getValue().productSellingPrice;
            }
        });

        JFXTreeTableColumn< SellerProductsItem, String> quantity = new JFXTreeTableColumn<>("product quantity");
        quantity.setPrefWidth(COLUMN_WIDTH);
        quantity.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< SellerProductsItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SellerProductsItem, String> param) {
                return param.getValue().getValue().productQuantity;
            }
        });



        try {


        } catch (Exception e) {
            e.printStackTrace();
        
        
        
        
        }
        codeObservableList = FXCollections.observableArrayList();
        for (Product product : facadeMarketProvider.getAllProductForSellers()
                ) {
            codeObservableList.add(new SellerProductsItem(product.getProductName(),product.getProductPrice(),product.getQuantity()));
        }
        final TreeItem< SellerProductsItem> root = new RecursiveTreeItem< SellerProductsItem>(codeObservableList, RecursiveTreeObject::getChildren);
        cat_tree_table.getColumns().setAll(name, price,quantity);
        cat_tree_table.setRoot(root);
        cat_tree_table.setShowRoot(false);


    }
    class SellerProductsItem extends RecursiveTreeObject<SellerProductsItem> {
        StringProperty productName;
        StringProperty productSellingPrice;
        StringProperty productQuantity;

        public SellerProductsItem(String productName, int productSellingPrice,int productQuantity) {
            this.productName = new SimpleStringProperty(productName);
            this.productSellingPrice = new SimpleStringProperty(String.valueOf(productSellingPrice));
            this.productQuantity = new SimpleStringProperty(String.valueOf(productQuantity));

        }
    }





}
