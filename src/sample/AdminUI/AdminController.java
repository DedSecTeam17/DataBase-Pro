package sample.AdminUI;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.Atuhentication.Auth;
import sample.Debugging.Log;
import sample.MarketModel.Product;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private HamburgerBackArrowBasicTransition hamburgerTransition;

    @FXML
    private AnchorPane sellersPanel;

    @FXML
    private AnchorPane productsPanel;

    @FXML
    private JFXTreeTableView<?> products_table;

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
    private JFXHamburger hamburgerButton;

    @FXML
    private AnchorPane transactionsPanel;

    @FXML
    private AnchorPane categoriesPanel;

    @FXML
    private AnchorPane homePanel;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox vbox;

    @FXML
    private Label selectionLabel;


private FacadeMarketProvider facadeMarketProvider;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        facadeMarketProvider=new FacadeMarketProvider();
        Log.i("Admin UI debugging"+Auth.getInstance().getCurrentUser());
        setUpAsideNavBar();


        add_product.setOnAction(event -> {






        });


    }

    private  void  addProduct() {
        if (!p_name.getText().equals("") && !p_price.getText().equals("")  && !expi_date.getTypeSelector().equals("")&& !p_date.getTypeSelector().equals("") && !p_quantity.getText().equals("") && !p_company.getText().equals(""))
        {
            Product product = Product.newProduct()
                    .productName(p_name.getText())
                    .productPrice(Integer.parseInt(p_price.getText()))
                    .productedCompany(p_company.getText())
                    .productionDate(p_date.getTypeSelector())
                    .expiredDate(expi_date.getTypeSelector())
                    .quantity(Integer.parseInt(p_quantity.getText()))
                    .build();
        facadeMarketProvider.insertProduct(product);

    }
    else
        {
            UiValidation.validateInput(p_name, p_name_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            


        }

    }






    private void setUpAsideNavBar() {
        drawer.setSidePane(vbox);
        hamburgerTransition = new HamburgerBackArrowBasicTransition(hamburgerButton);
        hamburgerTransition.setRate(-1);
    }

    @FXML
    private void handleHamburgerClick()
    {
        //Open and close drawer on hamburger button click
        hamburgerTransition.setRate(hamburgerTransition.getRate()*-1);
        hamburgerTransition.play();
        if (drawer.isOpened()){
            drawer.close();
        }
        else {
            drawer.open();
        }
    }


    @FXML
    void CategoriesButtonClicked(ActionEvent event)
    {
        highlight(((JFXButton)event.getSource()).getLayoutY());
        hideAllPanels();
        categoriesPanel.setVisible(true);
    }

    @FXML
    void ProductsButtonClicked(ActionEvent event)
    {
        highlight(((JFXButton)event.getSource()).getLayoutY());
        hideAllPanels();
        productsPanel.setVisible(true);
    }
    @FXML
    void HomeButtonClicked(ActionEvent event)
    {
        highlight(((JFXButton)event.getSource()).getLayoutY());
        hideAllPanels();
        homePanel.setVisible(true);
    }
    @FXML
    void SellersButtonClicked(ActionEvent event)
    {
        highlight(((JFXButton)event.getSource()).getLayoutY());
        hideAllPanels();
        sellersPanel.setVisible(true);
    }
    @FXML
    void TransactionsButtonClicked(ActionEvent event)
    {
        highlight(((JFXButton)event.getSource()).getLayoutY());
        hideAllPanels();
        transactionsPanel.setVisible(true);

    }
    void highlight(double y)
    {
        //moves the selection label to the y-axis of the selected button
        selectionLabel.setLayoutY(y);
    }
    void hideAllPanels()
    {
        sellersPanel.setVisible(false);
        productsPanel.setVisible(false);
        transactionsPanel.setVisible(false);
        categoriesPanel.setVisible(false);
        homePanel.setVisible(false);
    }
    @FXML void signOut()
    {
        //Code to return to the login scene
    }
}
