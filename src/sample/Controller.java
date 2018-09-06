package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.MarketModel.User;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
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





    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
            SignUp(Signup_FirstName,Signup_LastName,Signup_Email,Signup_Password);
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
        if (FormValidation.getInstance().checkEmail(_email) && FormValidation.getInstance().checkPassword(_password)) {
            User user=User.newUser().
                    firstName("mohammed").
                    lastName("elamin").
                    email("mohanned").
                    password("mohamed1337").
                    build();
            Log.i("user email " + user.getEmail());
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
        }
    }
    private void SignUp(JFXTextField first_name, JFXTextField last_name, JFXTextField email, JFXPasswordField password) {
        String _email = email.getText().trim();
        String _password = password.getText().trim();
        String _fname = first_name.getText().trim();
        String _lname = last_name.getText().trim();
        if (FormValidation.getInstance().checkEmail(_email) && FormValidation.getInstance().checkPassword(_password) && FormValidation.getInstance().checkuserName(_fname) && FormValidation.getInstance().checkuserName(_lname)) {

            User user=User.newUser().
                    firstName("mohammed").
                    lastName("elamin").
                    email("mohanned").
                    password("mohamed1337").
                    build();

//            User user1= User.newUser().
            Log.i("user email " + user.getFirstName()+"\t"+user.getLastName());
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


