package sample.AdminUI;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.animation.FadeTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.AdminUI.fragmnets.CategoryFragment;
import sample.AdminUI.fragmnets.ProductFragment;
import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Product;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private static final double COLUMN_WIDTH = 831 / 6;
    private static final double WINDOW_WIDTH =800 ;
    private static final double WINDOW_HEIGHT = 500;
    private HamburgerBackArrowBasicTransition hamburgerTransition;

    //region UI Variables


    //#region sellers

    //#region textFields
    @FXML
    private JFXTextField seller_FirstName;

    @FXML
    private JFXTextField seller_LastName;

    @FXML
    private JFXTextField seller_Email;

    @FXML
    private JFXTextField seller_Password;
    //#endregion

    //#region hints
    @FXML
    private Label hint_Seller_FirstName;

    @FXML
    private Label hint_Seller_LastName;

    @FXML
    private Label hint_Seller_Email;

    @FXML
    private Label hint_Seller_Password;
    //#endregion

    //#region buttons
    @FXML
    private JFXButton seller_AddButton;

    @FXML
    private JFXButton seller_UpdateButton;

    @FXML
    private JFXButton seller_RemoveButton;
    //#endregion


    //#endregion

    //#region products

    //#region buttons
    @FXML
    private JFXButton add_product;

    @FXML
    private JFXButton update_product;

    @FXML
    private JFXButton delete_product;
    //#endregion

    //#region textFields
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

    //#endregion

    //#region hints
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
    //#endregion

    //#endregion

    //#region transactions
    @FXML
    private JFXButton transaction_RemoveButton;
    //#endregion

    //#region Categories

    //#region textFields
    @FXML
    private JFXTextField cat_id;

    @FXML
    private JFXTextField cat_name;
    //#endregion

    //#region hints
    @FXML
    private Label cat_id_hint;

    @FXML
    private Label cat_name_hint;
    //#endregion

    //#region buttons
    @FXML
    private JFXButton add_cat;

    @FXML
    private JFXButton update_cat;

    @FXML
    private JFXButton remove_cat;
    //#endregion

    //#endregion

    //#region Tables
    @FXML
    private JFXTreeTableView<?> products_table;

    @FXML
    private JFXTreeTableView<?> categor_tree_table;
    //#endregion

    //#region Panes

    @FXML
    private AnchorPane admin_main_pane;

    @FXML
    private AnchorPane sellersPanel;


    @FXML
    private AnchorPane productsPanel;


    @FXML
    private AnchorPane transactionsPanel;

    @FXML
    private AnchorPane categoriesPanel;

    @FXML
    private AnchorPane homePanel;

    //#endregion

    @FXML
    private StackPane main_pane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox vbox;

    @FXML
    private Label selectionLabel;

    @FXML
    private JFXHamburger hamburgerButton;

    //#endregion

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
        drawer.close();
        hamburgerTransition.setRate(hamburgerTransition.getRate() * -1);
        hamburgerTransition.play();

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
        Auth.getInstance().destroyUser();
        DirectUserWithFade(main_pane,"../Login.fxml");

    }

    private void DirectUserWithFade(StackPane currentPane, String fxml_file) {

        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(currentPane);
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
                Scene newScene = new Scene(secondRoot,WINDOW_WIDTH,WINDOW_HEIGHT);

                Stage curStage = (Stage) currentPane.getScene().getWindow();

                curStage.setScene(newScene);
            }
        });
        fadeTransition.play();

    }

}
