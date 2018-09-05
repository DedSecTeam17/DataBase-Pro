package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sherlock on 12/27/2017.
 */
public class LoginSignupController 
{
    ////Login ///
    @FXML
    AnchorPane login_Pane;
    @FXML
    JFXButton SignIn_Button ;
    @FXML
    Label ForgotPassword;
    @FXML
    JFXTextField Login_Username;
    @FXML
    JFXPasswordField Login_Password ;
/////////

    ////SignUp ///
    @FXML
    AnchorPane SignUp_Pane;
    @FXML
    JFXButton SignUp_Button;
    @FXML
    JFXTextField Signup_Username;
    @FXML
    JFXPasswordField Signup_Password ;
    @FXML
    JFXTextField Signup_Email;
    @FXML
    JFXPasswordField Signup_ConfirmPassword;
    ////////
    @FXML
    JFXToggleButton toggleButton;
    @FXML
    Label WarningText;







    @FXML
    private void handleToggle(ActionEvent event){
        if (toggleButton.isSelected()){
            toggleButton.setText("Sign in");
            SignUp_Pane.setVisible(false);
            login_Pane.setVisible(true);
            clear_Fields();
        }
        else {
            toggleButton.setText("Sign up");
            login_Pane.setVisible(false);
            SignUp_Pane.setVisible(true);
            clear_Fields();
        }
    }

    void clear_Fields(){
        Login_Username.clear();
        Login_Password.clear();

        Signup_Username.clear();
        Signup_Email.clear();
        Signup_Password.clear();
        Signup_ConfirmPassword.clear();
    }

    @FXML
    private void handleLogin(ActionEvent event)
    {
        
    }

    @FXML
    private void handleSignUp(ActionEvent event)
    {
        
    }





    @FXML
    private void HandleClose(MouseEvent event){
        System.exit(0);
    }
}
