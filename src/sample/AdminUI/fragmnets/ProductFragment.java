package sample.AdminUI.fragmnets;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.Debugging.Log;
import sample.MarketModel.Category;
import sample.MarketModel.Product;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

import java.io.FileInputStream;
import java.time.LocalDate;

public class ProductFragment {
    public static final double COLUMN_WIDTH = 831 / 7;
    public ObservableList<ProductItem> codeObservableList;
    public FacadeMarketProvider facadeMarketProvider = new FacadeMarketProvider();

    public void onTableItemSelected(JFXTextField p_name, JFXTextField p_price, JFXTextField p_company, JFXTextField p_quantity, JFXDatePicker p_date, JFXDatePicker expi_date, JFXTreeTableView treeTableView) {
        treeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log.e("clicked");
                int r_index = treeTableView.getSelectionModel().getSelectedIndex();
                ProductItem RuleItem = codeObservableList.get(r_index);
                StringProperty name = RuleItem.productName;
                StringProperty price = RuleItem.productPrice;
                StringProperty company = RuleItem.productedCompany;
                StringProperty quantity = RuleItem.quantity;
                StringProperty date = RuleItem.productionDate;
                StringProperty edate = RuleItem.expiredDate;
                p_name.setEditable(false);

                p_name.setText(name.getValue());
                p_price.setText(price.getValue());
                p_company.setText(company.getValue());
                p_date.setValue(LocalDate.parse(date.getValue().replace(" ", "").replace(":", "").replace(".", "").replace("0000000", "")));
                expi_date.setValue(LocalDate.parse(edate.getValue().replace(" ", "").replace(":", "").replace(".", "").replace("0000000", "")));
//                expi_date.setValue(LocalDate.parse(edate.getValue()));
                p_quantity.setText(quantity.getValue());


            }
        });

    }

    public void deleteProduct(JFXTextField p_name, JFXTextField p_price, JFXTextField p_company, JFXTextField p_quantity, JFXDatePicker p_date, JFXDatePicker expi_date, Label hint_p_name, Label hint_p_price, Label hint_p_company, Label hint_p_quantity, Label hint_p_date, Label hint_expi_date, JFXTreeTableView treeTableView) {
        int r_index = treeTableView.getSelectionModel().getSelectedIndex();
        ProductItem RuleItem = codeObservableList.get(r_index);
        StringProperty getIdForSelectedItem = RuleItem.productName;
        String product_name = getIdForSelectedItem.getValue();
        Product product = Product.newProduct().productName(product_name).build();
        facadeMarketProvider.deleteProduct(product);
        clearFields(p_name, p_price, p_company, p_quantity, p_date, expi_date, hint_p_name, hint_p_price, hint_p_company, hint_p_quantity, hint_p_date, hint_expi_date);
        try {
            ProductTableColumn(treeTableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(product_name);
    }

    public void updateProduct(JFXTextField p_name, JFXTextField p_price, JFXTextField p_company, JFXTextField p_quantity, JFXDatePicker p_date, JFXDatePicker expi_date, String image_path, Label image_hint, Integer selectedItem, Label selected_hint_id, Label hint_p_name, Label hint_p_price, Label hint_p_company, Label hint_p_quantity, Label hint_p_date, Label hint_expi_date, JFXTreeTableView treeTableView) throws Exception {
        if (!p_name.getText().equals("") && !p_price.getText().equals("") && !expi_date.getTypeSelector().equals("") && !p_date.getTypeSelector().equals("") && !p_quantity.getText().equals("") && !p_company.getText().equals("")) {
            Product product = Product.newProduct()
                    .productName(p_name.getText())
                    .productPrice(Integer.parseInt(p_price.getText()))
                    .productedCompany(p_company.getText())
                    .productionDate(p_date.getValue().toString())
                    .expiredDate(expi_date.getValue().toString())
                    .quantity(Integer.parseInt(p_quantity.getText()))
                    .imagePath(image_path)
                    .Cat_id(selectedItem)
                    .build();

            facadeMarketProvider.updateProduct(product);
            ProductTableColumn(treeTableView);
            clearFields(p_name, p_price, p_company, p_quantity, p_date, expi_date, hint_p_name, hint_p_price, hint_p_company, hint_p_quantity, hint_p_date, hint_expi_date);
        } else {
            UiValidation.validateInput(p_name.getText(), hint_p_name, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(p_company.getText(), hint_p_company, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(p_price.getText(), hint_p_price, "empty filed not allowed", "only numbers", "valid", "num");
            UiValidation.validateInput(p_quantity.getText(), hint_p_quantity, "empty filed not allowed", "only numbers", "valid", "num");
            UiValidation.validateInput(String.valueOf(selectedItem), selected_hint_id, "empty filed not allowed", "only numbers", "valid", "normal");
            UiValidation.validateInput(p_date.getValue().toString(), hint_p_date, "empty filed not allowed", "not valid", "valid", "normal");
            UiValidation.validateInput(expi_date.getValue().toString(), hint_expi_date, "empty filed not allowed", "not valid", "valid", "normal");
//
        }
    }

    public void addProduct(JFXTextField p_name, JFXTextField p_price, JFXTextField p_company, JFXTextField p_quantity, JFXDatePicker p_date, JFXDatePicker expi_date, String image_path, Label image_hint, Integer selectedCategoryItem, Label selected_hint_id, Label hint_p_name, Label hint_p_price, Label hint_p_company, Label hint_p_quantity, Label hint_p_date, Label hint_expi_date, JFXTreeTableView treeTableView) throws Exception {
        if (!p_name.getText().equals("") && !p_price.getText().equals("") && !expi_date.getTypeSelector().equals("") && !p_date.getTypeSelector().equals("") && !p_quantity.getText().equals("") && !p_company.getText().equals("")) {
            Product product = Product.newProduct()
                    .productName(p_name.getText())
                    .productPrice(Integer.parseInt(p_price.getText()))
                    .productedCompany(p_company.getText())
                    .productionDate(p_date.getValue().toString())
                    .expiredDate(expi_date.getValue().toString())
                    .quantity(Integer.parseInt(p_quantity.getText()))
                    .imagePath(image_path)
                    .Cat_id(selectedCategoryItem)
                    .build();
            Log.i(product.getImage_path());
            facadeMarketProvider.insertProduct(product);
            ProductTableColumn(treeTableView);
            clearFields(p_name, p_price, p_company, p_quantity, p_date, expi_date, hint_p_name, hint_p_price, hint_p_company, hint_p_quantity, hint_p_date, hint_expi_date);
        } else {
            UiValidation.validateInput(p_name.getText(), hint_p_name, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(p_company.getText(), hint_p_company, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(p_price.getText(), hint_p_price, "empty filed not allowed", "only numbers", "valid", "num");
            UiValidation.validateInput(p_quantity.getText(), hint_p_quantity, "empty filed not allowed", "only numbers", "valid", "num");
            UiValidation.validateInput(String.valueOf(selectedCategoryItem), selected_hint_id, "empty filed not allowed", "only numbers", "valid", "normal");
            UiValidation.validateInput(p_date.getValue().toString(), hint_p_date, "empty filed not allowed", "not valid", "valid", "normal");
            UiValidation.validateInput(expi_date.getValue().toString(), hint_expi_date, "empty filed not allowed", "not valid", "valid", "normal");


            


        }

    }

    public void clearFields(JFXTextField p_name, JFXTextField p_price, JFXTextField p_company, JFXTextField p_quantity, JFXDatePicker p_date, JFXDatePicker expi_date, Label hint_p_name, Label hint_p_price, Label hint_p_company, Label hint_p_quantity, Label hint_p_date, Label hint_expi_date) {
        p_name.setEditable(true);
        p_name.clear();
        p_price.clear();
        p_company.clear();
        p_quantity.clear();
        p_date.setValue(null);
        expi_date.setValue(null);

//        

        hint_p_name.setText("");
        hint_p_price.setText("");
        hint_p_company.setText("");
        hint_p_quantity.setText("");
        hint_p_date.setText("");
        hint_expi_date.setText("");
    }

    //    PRODUCT TABLE
    public void ProductTableColumn(JFXTreeTableView treeTableView) throws Exception {
        JFXTreeTableColumn<ProductItem, String> name = new JFXTreeTableColumn<>("name");
        name.setPrefWidth(COLUMN_WIDTH);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductItem, String> param) {
                return param.getValue().getValue().productName;
            }
        });


        JFXTreeTableColumn<ProductItem, String> price = new JFXTreeTableColumn<>("price");
        price.setPrefWidth(COLUMN_WIDTH);
        price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductItem, String> param) {
                return param.getValue().getValue().productPrice;
            }
        });


        JFXTreeTableColumn<ProductItem, String> company = new JFXTreeTableColumn<>("company");
        company.setPrefWidth(COLUMN_WIDTH);
        company.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductItem, String> param) {
                return param.getValue().getValue().productedCompany;
            }
        });


        JFXTreeTableColumn<ProductItem, String> quantity = new JFXTreeTableColumn<>("quantity");
        quantity.setPrefWidth(COLUMN_WIDTH);
        quantity.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductItem, String> param) {
                return param.getValue().getValue().quantity;
            }
        });

        JFXTreeTableColumn<ProductItem, String> productionDate = new JFXTreeTableColumn<>("Production date");
        productionDate.setPrefWidth(COLUMN_WIDTH);
        productionDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductItem, String> param) {
                return param.getValue().getValue().productionDate;
            }
        });
        JFXTreeTableColumn<ProductItem, String> expiredDate = new JFXTreeTableColumn<>("Expired date");
        expiredDate.setPrefWidth(COLUMN_WIDTH);
        expiredDate.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductItem, String> param) {
                return param.getValue().getValue().expiredDate;
            }
        });

        JFXTreeTableColumn<ProductItem, String> category = new JFXTreeTableColumn<>("Category");
        category.setPrefWidth(COLUMN_WIDTH);
        category.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<ProductItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<ProductItem, String> param) {
                return param.getValue().getValue().category;
            }
        });

//

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }

        codeObservableList = FXCollections.observableArrayList();
        for (Product product : facadeMarketProvider.getAllProduct()
                ) {

            codeObservableList.add(new ProductItem(product.getProductName(), product.getProductPrice(), product.getProductionDate(), product.getExpiredDate(), product.getProductedCompany(), product.getQuantity(), facadeMarketProvider.getCategoryById(product.getCat_id()).getCategory_name()));

        }
        final TreeItem<ProductItem> root = new RecursiveTreeItem<ProductItem>(codeObservableList, RecursiveTreeObject::getChildren);


        treeTableView.getColumns().setAll(name, price, productionDate, expiredDate, company, quantity, category);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);

    }

    class ProductItem extends RecursiveTreeObject<ProductItem> {
        StringProperty productName;
        StringProperty productPrice;
        StringProperty productionDate;
        StringProperty expiredDate;
        StringProperty productedCompany;
        StringProperty quantity;
        StringProperty category;

        public ProductItem(String productName, int productPrice, String productionDate, String expiredDate, String productedCompany, int quantity, String category) {
            this.productName = new SimpleStringProperty(productName);
            this.productPrice = new SimpleStringProperty(String.valueOf(productPrice));
            this.productionDate = new SimpleStringProperty(productionDate);
            this.expiredDate = new SimpleStringProperty(expiredDate);
            this.productedCompany = new SimpleStringProperty(productedCompany);
            this.quantity = new SimpleStringProperty(String.valueOf(quantity));
            this.productedCompany = new SimpleStringProperty(productedCompany);
            this.category = new SimpleStringProperty(category);
        }
    }
}
