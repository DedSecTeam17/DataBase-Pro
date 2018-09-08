package sample.AdminUI.fragmnets;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;
import sample.AdminUI.AdminController;

import java.awt.*;

public class CategoryFragment 
{
//
//    private static final double COLUMN_WIDTH =70 ;
//
//    public  void  addCategory()
//    {
//
//    }
//    public  void  deleteCategory()
//    {
//
//    }
//    public  void  updateCategory()
//    {
//
//    }
//
//    private  void  clearFields(JFXTextField cat_name, JFXTextField cat_id, Label cat_name_hint,Label cat_id_hint)
//    {
//        cat_name.clear();
//        cat_id.clear();
//        cat_id_hint.setText("");
//        cat_name_hint.setText("");
//    }
//
//    private void CategorytTableColumn(JFXTreeTableView cat_tree_table) throws Exception {
//        JFXTreeTableColumn< CategorytItem, String> name = new JFXTreeTableColumn<>("name");
//        name.setPrefWidth(COLUMN_WIDTH);
//        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< CategorytItem, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< CategorytItem, String> param) {
//                return param.getValue().getValue().productName;
//            }
//        });
//
//
//        JFXTreeTableColumn< CategorytItem, String> price = new JFXTreeTableColumn<>("price");
//        price.setPrefWidth(COLUMN_WIDTH);
//        price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< CategorytItem, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< CategorytItem, String> param) {
//                return param.getValue().getValue().productPrice;
//            }
//        });
//
//
//        JFXTreeTableColumn< CategorytItem, String> company = new JFXTreeTableColumn<>("company");
//        company.setPrefWidth(COLUMN_WIDTH);
//        company.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< CategorytItem, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< CategorytItem, String> param) {
//                return param.getValue().getValue().productedCompany;
//            }
//        });
//
//
//        JFXTreeTableColumn< CategorytItem, String> quantity = new JFXTreeTableColumn<>("quantity");
//        quantity.setPrefWidth(COLUMN_WIDTH);
//        quantity.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< CategorytItem, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< CategorytItem, String> param) {
//                return param.getValue().getValue().quantity;
//            }
//        });
//
//        JFXTreeTableColumn< CategorytItem, String> productionDate = new JFXTreeTableColumn<>("Categorytion date");
//        productionDate.setPrefWidth(COLUMN_WIDTH);
//        productionDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< CategorytItem, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< CategorytItem, String> param) {
//                return param.getValue().getValue().productionDate;
//            }
//        });
//        JFXTreeTableColumn< CategorytItem, String> expiredDate = new JFXTreeTableColumn<>("Expired date");
//        expiredDate.setPrefWidth(COLUMN_WIDTH);
//        expiredDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures< CategorytItem, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures< CategorytItem, String> param) {
//                return param.getValue().getValue().expiredDate;
//            }
//        });
//
//        try {
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ObservableList<Object> codeObservableList = FXCollections.observableArrayList();
//        for (Categoryt product : facadeMarketProvider.getAllCategoryt()
//                ) {
//
//            codeObservableList.add(new  CategorytItem(product.getCategorytName(), product.getCategorytPrice(), product.getCategorytionDate(), product.getExpiredDate(), product.getCategorytedCompany(), product.getQuantity()));
//
//        }
//        final TreeItem< CategorytItem> root = new RecursiveTreeItem< CategorytItem>(codeObservableList, RecursiveTreeObject::getChildren);
//
//
//        products_table.getColumns().setAll(name, price, productionDate, expiredDate, company, quantity);
//        products_table.setRoot(root);
//        products_table.setShowRoot(false);
//
//
//    }
//    class CategorytItem extends RecursiveTreeObject<CategorytItem> {
//        StringProperty productName;
//        StringProperty productPrice;
//        StringProperty productionDate;
//        StringProperty expiredDate;
//        StringProperty productedCompany;
//        StringProperty quantity;
//
//        public CategorytItem(String productName, int productPrice, String productionDate, String expiredDate, String productedCompany, int quantity) {
//            this.productName = new SimpleStringProperty(productName);
//            this.productPrice = new SimpleStringProperty(String.valueOf(productPrice));
//            this.productionDate = new SimpleStringProperty(productionDate);
//            this.expiredDate = new SimpleStringProperty(expiredDate);
//            this.productedCompany = new SimpleStringProperty(productedCompany);
//            this.quantity = new SimpleStringProperty(String.valueOf(quantity));
//            this.productedCompany = new SimpleStringProperty(productedCompany);
//        }
//    }
//
//
//
}
