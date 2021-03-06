package sample;

import com.jfoenix.controls.*;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Atuhentication.Auth;
import sample.UiValidation.FormValidation;
import sample.Debugging.Log;
import sample.MarketModel.User;
import sample.MarketProvider.FacadeMarketProvider;
import sample.UiValidation.UiValidation;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final boolean ADMIN_ROLE = true;
    private static final boolean SELLER_ROLE = false;
    private static final double WINDOW_WIDTH = 1366;
    private static final double WINDOW_HEIGHT = 768;


    @FXML
    private AnchorPane login_reg_pane;
    @FXML
    private Pane login_Pane;

    @FXML
    private JFXTextField email_filed;

    @FXML
    private JFXPasswordField password_filed;

    @FXML
    private JFXButton btn_sign_in;

    @FXML
    private Label ForgotPassword;

    @FXML
    private Label email_hint;

    @FXML
    private Label password_hint;

    @FXML
    private JFXRadioButton admin_radio;

    @FXML
    private JFXRadioButton seller_radio;

    @FXML
    private Label radio_hint;

    @FXML
    private Pane SignUp_Pane;

    @FXML
    private JFXTextField Signup_FirstName;

    @FXML
    private JFXTextField Signup_Email;

    @FXML
    private JFXPasswordField Signup_Password;

    @FXML
    private JFXButton SignUp_Button;

    @FXML
    private JFXTextField Signup_LastName;

    @FXML
    private Label SignUp_fname_hint;

    @FXML
    private Label SignUp_email_hint;

    @FXML
    private Label SignUp_lname_hint;

    @FXML
    private Label SignUp_password_hint;


    @FXML
    private Label WarningText;

    @FXML
    private JFXToggleButton toggleButton;




    private String selectedUser = "";
    private ToggleGroup usersGroupSignUp;
    private ToggleGroup usersGroupSignIn;
    private FacadeMarketProvider facadeMarketProvider;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        facadeMarketProvider = new FacadeMarketProvider();
        setUpRadioBtnWithItToggle();
        toggleButton.setOnAction(event -> {
            if (toggleButton.isSelected()) {
                toggleButton.setText("Sign in");
                SignUp_Pane.setVisible(false);
                login_Pane.setVisible(true);
                clear_Fields();
            } else {
                toggleButton.setText("Sign up");
                login_Pane.setVisible(false);
                SignUp_Pane.setVisible(true);
                clear_Fields();
            }
        });
        btn_sign_in.setOnAction(event ->
        {
            SignIn(email_filed, password_filed);
        });
        SignUp_Button.setOnAction(event -> {
            SignUp(Signup_FirstName, Signup_LastName, Signup_Email, Signup_Password);

        });
    }

    private void setUpRadioBtnWithItToggle() {
        //        Sign In
        usersGroupSignIn = new ToggleGroup();
        usersGroupSignUp = new ToggleGroup();
        admin_radio.setToggleGroup(usersGroupSignIn);
        admin_radio.setUserData(true);
        seller_radio.setToggleGroup(usersGroupSignIn);
        seller_radio.setUserData(false);

        //        Sign Up


        usersGroupSignIn.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                selectedUser = usersGroupSignIn.getSelectedToggle().getUserData().toString();
                Log.i(usersGroupSignIn.getSelectedToggle().getUserData().toString());
            }
        });
        usersGroupSignUp.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                selectedUser = usersGroupSignUp.getSelectedToggle().getUserData().toString();
                Log.i(usersGroupSignUp.getSelectedToggle().getUserData().toString());
            }
        });

    }

    private void clear_Fields() {
        // ------------------ LABELS --------
//        registration
        this.SignUp_fname_hint.setText("");
        this.SignUp_password_hint.setText("");
        this.SignUp_email_hint.setText("");
        this.SignUp_lname_hint.setText("");

//        loginAdmin
        this.email_hint.setText("");
        this.password_hint.setText("");
        this.admin_radio.setUserData(true);
        this.seller_radio.setUserData(false);
        this.radio_hint.setText("");
        /// ------ FIELDS

//        LOGIN
        this.password_filed.clear();
        this.email_filed.clear();
//        REGISTRATIONS
        this.Signup_Email.clear();
        this.Signup_Password.clear();
        this.Signup_FirstName.clear();
        this.Signup_LastName.clear();

    }

    private void SignIn(JFXTextField email, JFXPasswordField password) {
        String _email = email.getText().trim();
        String _password = password.getText().trim();
        boolean role;
        if (selectedUser.equals("true")) {
            role = true;

        } else {
            role = false;

        }
        if (FormValidation.getInstance().checkEmail(_email) && FormValidation.getInstance().checkPassword(_password) && !this.selectedUser.equals("")) {
            User user = User.newUser().
                    email(_email).
                    password(_password).
                    role(role).
                    build();
            List<User> result = facadeMarketProvider.loginAdmin(user);
            List<User> result_seller = facadeMarketProvider.loginSeller(user);
            if (result.get(0).isRole()) {
                if (!result.get(0).getEmail().equals("")) {
                    if (result.get(0).getMessage().equals("")) {
                        //                redirect user into admin dashboard
                        Auth.getInstance().start_session();
                        Auth.getInstance().addUser(result.get(0).getEmail());
                        Log.i(Auth.getInstance().getCurrentUser());
                        if (Auth.getInstance().isset()) {
                            DirectUserWithFade(login_reg_pane, "../sample/AdminUI/Admin.fxml");
                        }
                        Log.i("this is admin and his email :" + result.get(0).getEmail());
                    } else {
                        UiValidation.hintErr(email_hint, result.get(0).getMessage());
                    }

                } else {
                    UiValidation.hintErr(email_hint, result.get(0).getMessage());
                }

            } else {
                if (!result_seller.get(0).getEmail().equals("")) {
                    if (result_seller.get(0).getMessage().equals("")) {
                        //                redirect user into admin dashboard
                        Auth.getInstance().start_session();
                        Auth.getInstance().addUser(result_seller.get(0).getEmail());
                        Log.i(Auth.getInstance().getCurrentUser());
                        if (Auth.getInstance().isset()) {
                            DirectUserWithFade(login_reg_pane, "../sample/SellerUI/seller.fxml");
                        }
                        Log.i("this is admin and his email :" + result_seller.get(0).getEmail());
                    } else {
                        UiValidation.hintErr(email_hint, result_seller.get(0).getMessage());
                    }

                } else {
                    UiValidation.hintErr(email_hint, "this email is used or not registered");
                }
            }
        } else {
            UiValidation.validateInput(password.getText(), password_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid first name");
            UiValidation.validateInput(email.getText(), email_hint, "empty filed not allowed", "invalid email", "valid email", "email");
        }
    }

    private void SignUp(JFXTextField first_name, JFXTextField last_name, JFXTextField email, JFXPasswordField password) {
        String _email = email.getText().trim();
        String _password = password.getText().trim();
        String _fname = first_name.getText().trim();
        String _lname = last_name.getText().trim();
        if (FormValidation.getInstance().checkEmail(_email) && FormValidation.getInstance().checkPassword(_password) && FormValidation.getInstance().checkuserName(_fname) && FormValidation.getInstance().checkuserName(_lname)) {
            User user = User.newUser().
                    firstName(_fname).
                    lastName(_lname).
                    email(_email).
                    password(_password).
                    role(ADMIN_ROLE).
                    build();
            String warning = facadeMarketProvider.insertAdmin(user);
            if (!warning.equals("")) {
                Auth.getInstance().start_session();
                Auth.getInstance().addUser(_email);
                Log.i(Auth.getInstance().getCurrentUser());
                if (Auth.getInstance().isset()) {
                    DirectUserWithFade(login_reg_pane, "../sample/SellerUI/seller.fxml");
                }
                UiValidation.hintSuccess(SignUp_email_hint, "this email already used");
            } else {
                DirectUserWithFade(login_reg_pane, "../sample/AdminUI/Admin.fxml");
            }
        } else {
            UiValidation.validateInput(first_name.getText(), SignUp_fname_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid first name", "normal");
            UiValidation.validateInput(last_name.getText(), SignUp_lname_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid first name", "normal");
            UiValidation.validateInput(password.getText(), SignUp_password_hint, "empty filed not allowed", "greater than 6 white space not allowed", "valid first name");
            UiValidation.validateInput(email.getText(), SignUp_email_hint, "empty filed not allowed", "invalid email", "valid email", "email");
        }

    }
    //#endregion
    private void DirectUserWithFade(AnchorPane currentPane, String fxml_file) {

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
                Scene newScene = new Scene(secondRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
                Stage curStage = (Stage) currentPane.getScene().getWindow();
                curStage.setMinWidth(WINDOW_WIDTH);
                curStage.setMinHeight(WINDOW_HEIGHT);

                curStage.setMaxHeight(WINDOW_HEIGHT);
                curStage.setMaxWidth(WINDOW_WIDTH);

                curStage.setScene(newScene);
            }
        });
        fadeTransition.play();
    }
}


