package sample.AdminUI;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sample.AdminUI.fragmnets.CategoryFragment;
import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Product;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private static final double COLUMN_WIDTH = 831 / 6;
    private HamburgerBackArrowBasicTransition hamburgerTransition;

    @FXML
    private AnchorPane sellersPanel;

    @FXML
    private AnchorPane productsPanel;

    @FXML
    private JFXTreeTableView products_table;

    @FXML
    private JFXTextField p_name;

    @FXML
    private JFXTextField p_price;

    @FXML
    private JFXTextField p_company;

    @FXML
    private JFXTextField p_quantity;

    @FXML
    private JFXDatePicker p_date;

    @FXML
    private JFXDatePicker expi_date;

    @FXML
    private Label p_name_hint;

    @FXML
    private Label p_price_hint;

    @FXML
    private Label p_company_hint;

    @FXML
    private Label p_quantity_hint;

    @FXML
    private Label produ_hint;

    @FXML
    private Label p_expi_hint;

    @FXML
    private JFXButton add_product;

    @FXML
    private JFXButton update_product;

    @FXML
    private JFXButton delete_product;

    @FXML
    private AnchorPane transactionsPanel;

    @FXML
    private AnchorPane categoriesPanel;

    @FXML
    private JFXTreeTableView categor_tree_table;

    @FXML
    private JFXTextField cat_id;

    @FXML
    private JFXTextField cat_name;

    @FXML
    private Label cat_id_hint;

    @FXML
    private Label cat_name_hint;

    @FXML
    private JFXButton add_cat;

    @FXML
    private JFXButton remove_cat;

    @FXML
    private JFXButton update_cat;

    @FXML
    private AnchorPane homePanel;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox vbox;

    @FXML
    private Label selectionLabel;

    @FXML
    private JFXHamburger hamburgerButton;


    private ObservableList<ProductItem> codeObservableList;
    private FacadeMarketProvider facadeMarketProvider;
    private CategoryFragment categoryFragment = new CategoryFragment();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeMarketProvider = new FacadeMarketProvider();
        try {
            ProductTableColumn();
            categoryFragment.CategorytTableColumn(categor_tree_table);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Admin UI debugging" + Auth.getInstance().getCurrentUser());
        setUpAsideNavBar();


        add_product.setOnAction(event -> {
            try {
                addProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        products_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log.e("clicked");
                int r_index = products_table.getSelectionModel().getSelectedIndex();
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
        delete_product.setOnAction(event -> {
            deleteProduct();
        });
        update_product.setOnAction(event ->
        {
            try {
                updateProduct();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        categoryFragment.onTableItemSelected(categor_tree_table, cat_name, cat_id);
        add_cat.setOnAction(event -> {
            try {
                categoryFragment.addCategory(cat_name, cat_id, cat_id_hint, cat_name_hint, categor_tree_table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        remove_cat.setOnAction(event -> categoryFragment.deleteCategory(cat_name, cat_id, cat_id_hint, cat_name_hint, categor_tree_table));
        update_cat.setOnAction(event -> {
            try {
                categoryFragment.updateCategory(cat_name, cat_id, cat_id_hint, cat_name_hint, categor_tree_table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void deleteProduct() {
        int r_index = products_table.getSelectionModel().getSelectedIndex();
        ProductItem RuleItem = codeObservableList.get(r_index);
        StringProperty getIdForSelectedItem = RuleItem.productName;
        String product_name = getIdForSelectedItem.getValue();
        Product product = Product.newProduct().productName(product_name).build();
        facadeMarketProvider.deleteProduct(product);
        clearFields();
        try {
            ProductTableColumn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(product_name);
    }

    private void updateProduct() throws Exception {
        if (!p_name.getText().equals("") && !p_price.getText().equals("") && !expi_date.getTypeSelector().equals("") && !p_date.getTypeSelector().equals("") && !p_quantity.getText().equals("") && !p_company.getText().equals("")) {
            Product product = Product.newProduct()
                    .productName(p_name.getText())
                    .productPrice(Integer.parseInt(p_price.getText()))
                    .productedCompany(p_company.getText())
                    .productionDate(p_date.getValue().toString())
                    .expiredDate(expi_date.getValue().toString())
                    .quantity(Integer.parseInt(p_quantity.getText()))
                    .build();
            facadeMarketProvider.updateProduct(product);
            ProductTableColumn();
            clearFields();
        } else {
            UiValidation.validateInput(p_name, p_name_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");


        }
    }

    private void addProduct() throws Exception {
        if (!p_name.getText().equals("") && !p_price.getText().equals("") && !expi_date.getTypeSelector().equals("") && !p_date.getTypeSelector().equals("") && !p_quantity.getText().equals("") && !p_company.getText().equals("")) {
            Product product = Product.newProduct()
                    .productName(p_name.getText())
                    .productPrice(Integer.parseInt(p_price.getText()))
                    .productedCompany(p_company.getText())
                    .productionDate(p_date.getValue().toString())
                    .expiredDate(expi_date.getValue().toString())
                    .quantity(Integer.parseInt(p_quantity.getText()))
                    .build();

            facadeMarketProvider.insertProduct(product);
            ProductTableColumn();
            clearFields();
        } else {
            UiValidation.validateInput(p_name, p_name_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");


        }

    }

    private void setUpAsideNavBar() {
        drawer.setSidePane(vbox);
        hamburgerTransition = new HamburgerBackArrowBasicTransition(hamburgerButton);
        hamburgerTransition.setRate(-1);
    }

    private void clearFields() {
        p_name.setEditable(true);
        p_name.clear();
        p_price.clear();
        p_company.clear();
        p_quantity.clear();
        p_date.setValue(null);
        expi_date.setValue(null);
    }

    @FXML
    private void handleHamburgerClick() {
        //Open and close drawer on hamburger button click
        hamburgerTransition.setRate(hamburgerTransition.getRate() * -1);
        hamburgerTransition.play();
        if (drawer.isOpened()) {
            drawer.close();
        } else {
            drawer.open();
        }
    }

    @FXML
    void CategoriesButtonClicked(ActionEvent event) {
        highlight(((JFXButton) event.getSource()).getLayoutY());
        hideAllPanels();
        categoriesPanel.setVisible(true);
    }

    @FXML
    void ProductsButtonClicked(ActionEvent event) {
        highlight(((JFXButton) event.getSource()).getLayoutY());
        hideAllPanels();
        productsPanel.setVisible(true);
    }

    @FXML
    void HomeButtonClicked(ActionEvent event) {
        highlight(((JFXButton) event.getSource()).getLayoutY());
        hideAllPanels();
        homePanel.setVisible(true);
    }

    @FXML
    void SellersButtonClicked(ActionEvent event) {
        highlight(((JFXButton) event.getSource()).getLayoutY());
        hideAllPanels();
        sellersPanel.setVisible(true);
    }

    @FXML
    void TransactionsButtonClicked(ActionEvent event) {
        highlight(((JFXButton) event.getSource()).getLayoutY());
        hideAllPanels();
        transactionsPanel.setVisible(true);
    }

    void highlight(double y) {
        //moves the selection label to the y-axis of the selected button
        selectionLabel.setLayoutY(y);
    }

    void hideAllPanels() {
        sellersPanel.setVisible(false);
        productsPanel.setVisible(false);
        transactionsPanel.setVisible(false);
        categoriesPanel.setVisible(false);
        homePanel.setVisible(false);
    }

    @FXML
    void signOut() {
        //Code to return to the login scene
    }

    //    PRODUCT TABLE
    private void ProductTableColumn() throws Exception {
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

        try {


        } catch (Exception e) {
            e.printStackTrace();
        }

        codeObservableList = FXCollections.observableArrayList();
        for (Product product : facadeMarketProvider.getAllProduct()
                ) {

            codeObservableList.add(new ProductItem(product.getProductName(), product.getProductPrice(), product.getProductionDate(), product.getExpiredDate(), product.getProductedCompany(), product.getQuantity()));

        }
        final TreeItem<ProductItem> root = new RecursiveTreeItem<ProductItem>(codeObservableList, RecursiveTreeObject::getChildren);


        products_table.getColumns().setAll(name, price, productionDate, expiredDate, company, quantity);
        products_table.setRoot(root);
        products_table.setShowRoot(false);


    }

    class ProductItem extends RecursiveTreeObject<ProductItem> {
        StringProperty productName;
        StringProperty productPrice;
        StringProperty productionDate;
        StringProperty expiredDate;
        StringProperty productedCompany;
        StringProperty quantity;

        public ProductItem(String productName, int productPrice, String productionDate, String expiredDate, String productedCompany, int quantity) {
            this.productName = new SimpleStringProperty(productName);
            this.productPrice = new SimpleStringProperty(String.valueOf(productPrice));
            this.productionDate = new SimpleStringProperty(productionDate);
            this.expiredDate = new SimpleStringProperty(expiredDate);
            this.productedCompany = new SimpleStringProperty(productedCompany);
            this.quantity = new SimpleStringProperty(String.valueOf(quantity));
            this.productedCompany = new SimpleStringProperty(productedCompany);
        }
    }
}
