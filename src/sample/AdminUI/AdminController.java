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
import sample.AdminUI.fragmnets.ProductFragment;
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


    private FacadeMarketProvider facadeMarketProvider;
    private CategoryFragment categoryFragment = new CategoryFragment();
    private ProductFragment productFragment = new ProductFragment();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        facadeMarketProvider = new FacadeMarketProvider();
        try {
            productFragment.ProductTableColumn(products_table);
            categoryFragment.CategorytTableColumn(categor_tree_table);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Admin UI debugging" + Auth.getInstance().getCurrentUser());
        setUpAsideNavBar();
        productFragment.onTableItemSelected(p_name, p_price, p_company, p_quantity, p_date, expi_date, products_table);

        add_product.setOnAction(event -> {
            try {
                productFragment.addProduct(p_name, p_price, p_company, p_quantity, p_date, expi_date, p_name_hint, p_price_hint, p_company_hint, p_quantity_hint, produ_hint, p_expi_hint, products_table);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        delete_product.setOnAction(event -> {
            productFragment.deleteProduct(p_name, p_price, p_company, p_quantity, p_date, expi_date, p_name_hint, p_price_hint, p_company_hint, p_quantity_hint, produ_hint, p_expi_hint, products_table);
        });
        update_product.setOnAction(event ->
        {
            try {
                productFragment.updateProduct(p_name, p_price, p_company, p_quantity, p_date, expi_date, p_name_hint, p_price_hint, p_company_hint, p_quantity_hint, produ_hint, p_expi_hint, products_table);
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


    private void setUpAsideNavBar() {
        drawer.setSidePane(vbox);
        hamburgerTransition = new HamburgerBackArrowBasicTransition(hamburgerButton);
        hamburgerTransition.setRate(-1);
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


}
