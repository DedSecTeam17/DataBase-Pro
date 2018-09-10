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
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.Debugging.Log;
import sample.MarketModel.Product;
import sample.MarketModel.User;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

import java.time.LocalDate;

public class SellerFragment
{
    public static final double COLUMN_WIDTH =831/6 ;
    public ObservableList<SellerFragment.seller> codeObservableList;
    public FacadeMarketProvider facadeMarketProvider=new FacadeMarketProvider();
    public  void  onTableItemSelected(JFXTextField fName, JFXTextField lName,JFXTextField emailField,JFXTextField passwordField ,JFXTreeTableView treeTableView)
    {
        treeTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Log.e("clicked");
                int r_index = treeTableView.getSelectionModel().getSelectedIndex();
                SellerFragment.seller RuleItem = codeObservableList.get(r_index);
                StringProperty firstName = RuleItem.firstName;
                StringProperty lastName = RuleItem.lastName;
                StringProperty email = RuleItem.email;
                StringProperty password = RuleItem.password;
                emailField.setEditable(false);

                fName.setText(firstName.getValue());
                lName.setText(lastName.getValue());
                emailField.setText(email.getValue());
                passwordField.setText(password.getValue());

            }
        });

    }
    public void deleteSeller(JFXTextField fName, JFXTextField lName,JFXTextField emailField,JFXTextField passwordField ,Label hint_fName, Label hint_lName, Label hint_email, Label hint_password, JFXTreeTableView treeTableView) {
        int r_index = treeTableView.getSelectionModel().getSelectedIndex();
        SellerFragment.seller RuleItem = codeObservableList.get(r_index);
        StringProperty getIdForSelectedItem = RuleItem.email;
        String product_name = getIdForSelectedItem.getValue();
        User user = User.newUser().email(product_name).build();
        facadeMarketProvider.deleteSeller(user);

        clearFields(fName,lName,emailField,passwordField,hint_fName,hint_lName,hint_email,hint_password);
        try {
            ProductTableColumn(treeTableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(product_name);
    }
    public void updateProduct(JFXTextField fName, JFXTextField lName,JFXTextField emailField,JFXTextField passwordField,Label hint_fName, Label hint_lName, Label hint_email, Label hint_password ,JFXTreeTableView treeTableView) throws Exception {
        if (!fName.getText().equals("") && !lName.getText().equals("") && !emailField.getText().equals("") && !passwordField.getText().equals("")) {
            User user = User.newUser()
                    .firstName(fName.getText())
                    .lastName(lName.getText())
                    .email(emailField.getText())
                    .password(passwordField.getText())
                    .build();

            facadeMarketProvider.updateSeller(user);
            ProductTableColumn(treeTableView);
            clearFields(fName,lName,emailField,passwordField,hint_fName,hint_lName,hint_email,hint_password);
        } else {

//            UiValidation.validateInput(fName, hint_p_name, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
//            UiValidation.validateInput(p_company, hint_p_name, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
//            UiValidation.validateInput(p_price, hint_p_price, "empty filed not allowed", "only numbers", "valid", "num");
//            UiValidation.validateInput(p_quantity, hint_p_quantity, "empty filed not allowed", "only numbers", "valid", "num");



        }
    }
    public void addProduct(JFXTextField fName,JFXTextField lName,JFXTextField emailField,JFXTextField passwordField,Label hint_fName,Label hint_lName,Label hint_email,Label hint_password,JFXTreeTableView treeTableView) throws Exception {
        if (!fName.getText().equals("") && !lName.getText().equals("") && !emailField.getText().equals("") && !passwordField.getText().equals("")) {
            User user = User.newUser()
                    .firstName(fName.getText())
                    .lastName(lName.getText())
                    .email(emailField.getText())
                    .password(passwordField.getText())
                    .build();

            facadeMarketProvider.insertSeller(user);
            ProductTableColumn(treeTableView);
            clearFields(fName,lName,emailField,passwordField,hint_email,hint_fName,hint_lName,hint_password);
        } else {
            UiValidation.validateInput(fName, hint_fName, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(lName, hint_lName, "empty filed not allowed", "greater than 6 white space not allowed", "valid", "normal");
            UiValidation.validateInput(emailField, hint_email, "empty filed not allowed", "only numbers", "valid", "num");
            UiValidation.validateInput(passwordField, hint_password, "empty filed not allowed", "only numbers", "valid", "num");



        }

    }
    public void clearFields(JFXTextField p_name,JFXTextField p_price,JFXTextField p_company,JFXTextField p_quantity, Label hint_fName, Label hint_lName,Label hint_passwordField,Label hint_emailField) {
        p_name.setEditable(true);
        p_name.clear();
        p_price.clear();
        p_company.clear();
        p_quantity.clear();

        hint_fName.setText("");
        hint_lName.setText("");
        hint_emailField.setText("");
        hint_passwordField.setText("");
    }

    //    PRODUCT TABLE
    public void ProductTableColumn(JFXTreeTableView treeTableView) throws Exception {
        JFXTreeTableColumn<SellerFragment.seller, String> fName = new JFXTreeTableColumn<>("name");
        fName.setPrefWidth(COLUMN_WIDTH);
        fName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SellerFragment.seller, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SellerFragment.seller, String> param) {
                return param.getValue().getValue().firstName;
            }
        });


        JFXTreeTableColumn<SellerFragment.seller, String> lName = new JFXTreeTableColumn<>("price");
        lName.setPrefWidth(COLUMN_WIDTH);
        lName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SellerFragment.seller, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SellerFragment.seller, String> param) {
                return param.getValue().getValue().lastName;
            }
        });


        JFXTreeTableColumn<SellerFragment.seller, String> email = new JFXTreeTableColumn<>("company");
        email.setPrefWidth(COLUMN_WIDTH);
        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SellerFragment.seller, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SellerFragment.seller, String> param) {
                return param.getValue().getValue().email;
            }
        });


        JFXTreeTableColumn<SellerFragment.seller, String> password = new JFXTreeTableColumn<>("quantity");
        password.setPrefWidth(COLUMN_WIDTH);
        password.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<SellerFragment.seller, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<SellerFragment.seller, String> param) {
                return param.getValue().getValue().password;
            }
        });

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        codeObservableList = FXCollections.observableArrayList();
        for (User user : facadeMarketProvider.getAllSellers()
                ) {

            codeObservableList.add(new SellerFragment.seller(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword()));

        }
        final TreeItem<SellerFragment.seller> root = new RecursiveTreeItem<SellerFragment.seller>(codeObservableList, RecursiveTreeObject::getChildren);


        treeTableView.getColumns().setAll(fName, lName, email, password);
        treeTableView.setRoot(root);
        treeTableView.setShowRoot(false);


    }
    class seller extends RecursiveTreeObject<SellerFragment.seller> {
        StringProperty firstName;
        StringProperty lastName;
        StringProperty email;
        StringProperty password;

        public seller(String firstName, String lastName, String email, String password) {
            this.firstName = new SimpleStringProperty(firstName);
            this.lastName = new SimpleStringProperty(lastName);
            this.email = new SimpleStringProperty(email);
            this.password = new SimpleStringProperty(password);
        }
    }

}
