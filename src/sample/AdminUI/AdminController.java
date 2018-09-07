package sample.AdminUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.Atuhentication.Auth;
import sample.Debugging.Log;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    private HamburgerBackArrowBasicTransition hamburgerTransition;

    @FXML private JFXDrawer drawer;
    @FXML private VBox vbox;
    @FXML private JFXHamburger hamburgerButton;
    @FXML private Label selectionLabel;

    @FXML private AnchorPane sellersPanel;

    @FXML private AnchorPane productsPanel;

    @FXML private AnchorPane transactionsPanel;

    @FXML private AnchorPane categoriesPanel;

    @FXML private AnchorPane homePanel;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Log.i("Admin UI debugging"+Auth.getInstance().getCurrentUser());
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
}
