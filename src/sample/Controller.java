package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {
    @FXML
    private JFXButton sign_in_btn;

    @FXML
    private JFXTextField email_filed;

    @FXML
    private JFXPasswordField password_field;

    @FXML
    private JFXButton sign_up_btn;


    @FXML
    private Label email_hint;

    @FXML
    private Label password_hint;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sign_in_btn.setOnAction(event ->
        {
            SignIn(email_filed, password_field);
        });
    }
    private void SignIn(JFXTextField email, JFXPasswordField password) {
        String _email = email.getText().trim();
        String _password = password.getText().trim();
        if (FormValidation.getInstance().checkEmail(_email) && FormValidation.getInstance().checkPassword(_password)) {
            User user = new User(_email, _password);
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
                password.setText("valid password");
            }


        }


    }

    //#endregion


}


