package sample;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import sample.MarketModel.User;
import sample.MarketProvider.FacadeMarketProvider;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static final boolean ADMIN_ROLE = true;
    private static final boolean SELLER_ROLE = false;
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

//        login
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
            List<User> result = facadeMarketProvider.login(user);
            if (result.get(0).isRole()) {
                if (!result.get(0).getEmail().equals("")) {
                    if (result.get(0).getMessage().equals("")) {
                        //                redirect user into admin dashboard
                        Log.i("this is admin and his email :" + result.get(0).getEmail());
                    } else {
                        email_hint.setStyle("-fx-text-fill:   #f64747");
                        email_hint.setText("this email is used or not registered");
                    }

                } else {
                    email_hint.setStyle("-fx-text-fill:   #f64747");
                    email_hint.setText("this email is used or not registered");
                }


            } else {
                if (!result.get(0).getEmail().equals("")) {
                    if (result.get(0).getMessage().equals("")) {

                        Log.i("this is seller and his email :" + result.get(0).getEmail());
                    } else {
                        email_hint.setStyle("-fx-text-fill:   #f64747");
                        email_hint.setText("this email is used or not registered");
                    }
//                redirect seller into his scene
                } else {
                    email_hint.setStyle("-fx-text-fill:   #f64747");
                    email_hint.setText("this email is used or not registered");
                }
            }

        } else {
//            inform user with his error on email
            if (!FormValidation.getInstance().checkEmail(_email)) {
                if (_email.equals("")) {
                    email_hint.setStyle("-fx-text-fill:   #f64747");
                    email_hint.setText("empty field not allowed");
                } else {
                    email_hint.setStyle("-fx-text-fill:   #f64747");
                    email_hint.setText("email address not valid");
                }
                Log.e("not valid email address");

            } else {
                email_hint.setStyle("-fx-text-fill:   limegreen");
                email_hint.setText("valid email");
            }

// inform user with his err password
            if (!FormValidation.getInstance().checkPassword(_password)) {
                if (_password.equals("")) {
                    password_hint.setStyle("-fx-text-fill:   #f64747");
                    password_hint.setText("empty filed not allowed");
                } else {
                    password_hint.setStyle("-fx-text-fill:   #f64747");
                    password_hint.setText("password not valid");
                }
                Log.e("not valid password password");
            } else {
                password_hint.setStyle("-fx-text-fill:   limegreen");
                password_hint.setText("valid password");
            }

            if (this.selectedUser.equals("")) {
                radio_hint.setStyle("-fx-text-fill:   #f64747");
                radio_hint.setText("required");
            } else {
                radio_hint.setStyle("-fx-text-fill:   limegreen");
                radio_hint.setText("valid");
            }
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
//            add user
            String warning = facadeMarketProvider.insertAdmin(user);

//            User user1= User.newUser().
//            Log.i(warning);

            if (!warning.equals("")) {
                SignUp_email_hint.setStyle("-fx-text-fill:   #f64747");
                SignUp_email_hint.setText("this email already used");
            } else {
                try {
                    Auth.getInstance().RedirectUser(login_reg_pane, "../sample/AdminUI/Admin.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {

            if (!FormValidation.getInstance().checkuserName(_fname)) {
                if (_fname.equals("")) {
                    SignUp_fname_hint.setStyle("-fx-text-fill:   #f64747");
                    SignUp_fname_hint.setText("empty filed not allowed");
                } else {
                    SignUp_fname_hint.setStyle("-fx-text-fill:   #f64747");
                    SignUp_fname_hint.setText("first name to short ,must be bigger than 7 char");
                }
                Log.e("not valid first name");
            } else {
                SignUp_fname_hint.setStyle("-fx-text-fill:   limegreen");
                SignUp_fname_hint.setText("valid first name");
            }

            if (!FormValidation.getInstance().checkuserName(_lname)) {
                if (_lname.equals("")) {
                    SignUp_lname_hint.setStyle("-fx-text-fill:   #f64747");
                    SignUp_lname_hint.setText("empty filed not allowed");
                } else {
                    SignUp_lname_hint.setStyle("-fx-text-fill:   #f64747");
                    SignUp_lname_hint.setText("last name to short ,must be bigger than 7 char");
                }
                Log.e("not valid  last name");
            } else {
                SignUp_lname_hint.setStyle("-fx-text-fill:   limegreen");
                SignUp_lname_hint.setText("valid last name");
            }
//            inform user with his error on email
            if (!FormValidation.getInstance().checkEmail(_email)) {
                if (_email.equals("")) {
                    SignUp_email_hint.setStyle("-fx-text-fill:   #f64747");
                    SignUp_email_hint.setText("empty field not allowed");
                } else {
                    SignUp_email_hint.setStyle("-fx-text-fill:   #f64747");
                    SignUp_email_hint.setText("email address not valid");
                }
                Log.e("not valid email address");

            } else {
                SignUp_email_hint.setStyle("-fx-text-fill:   limegreen");
                SignUp_email_hint.setText("valid email");
            }

// inform user with his err password
            if (!FormValidation.getInstance().checkPassword(_password)) {
                if (_password.equals("")) {
                    SignUp_password_hint.setStyle("-fx-text-fill:   #f64747");
                    SignUp_password_hint.setText("empty filed not allowed");
                } else {
                    SignUp_password_hint.setStyle("-fx-text-fill:   #f64747");
                    SignUp_password_hint.setText("password must only contains char and num");
                }
                Log.e("not valid password password");
            } else {
                SignUp_password_hint.setStyle("-fx-text-fill:   limegreen");
                SignUp_password_hint.setText("valid password");
            }


        }

    }
    //#endregion


}


