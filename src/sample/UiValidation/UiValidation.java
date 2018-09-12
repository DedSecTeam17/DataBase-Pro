package sample.UiValidation;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;

public class UiValidation {


    public static void InputErrorValidationWithHint(JFXPasswordField field, Label label, String message1, String message2) {
        if (field .equals("")) {
            hintErr(label, message1);
        } else {
            hintErr(label, message2);
        }
    }

    public static void InputErrorValidationWithHint(String field, Label label, String message1, String message2) {
        if (field .equals("")) {
            hintErr(label, message1);
        } else {
            hintErr(label, message2);
        }
    }

    public static void hintErr(Label hint, String message) {
        hint.setStyle("-fx-text-fill:   #f64747");
        hint.setText(message);
    }

    public static void hintSuccess(Label label, String message) {
        label.setStyle("-fx-text-fill:   limegreen");
        label.setText(message);
    }

    public static void InputSuccessValidationWithHint(Label label, String message1) {
        hintSuccess(label, message1);
    }


    public static void validateInput(String field, Label label, String message_err1, String message_err2, String sucess_message, String type) {

        switch (type) {
            case "normal":
                if (!FormValidation.getInstance().checkuserName(field )) {
                    UiValidation.InputErrorValidationWithHint(field, label, message_err1, message_err2);
                } else {
                    UiValidation.InputSuccessValidationWithHint(label, sucess_message);
                }
                break;
            case "email":
                if (!FormValidation.getInstance().checkEmail(field )) {
                    UiValidation.InputErrorValidationWithHint(field, label, message_err1, message_err2);
                } else {
                    UiValidation.InputSuccessValidationWithHint(label, sucess_message);
                }
                break;
            case "num":
                if (!FormValidation.getInstance().isNumberOnly(field )) {
                    UiValidation.InputErrorValidationWithHint(field, label, message_err1, message_err2);
                } else {
                    UiValidation.InputSuccessValidationWithHint(label, sucess_message);
                }
        }

    }

      public static void validateInput(String field, Label label, String message_err1, String message_err2, String sucess_message) {
              if (!FormValidation.getInstance().checkuserName(field )) {
            UiValidation.InputErrorValidationWithHint(field, label, message_err1, message_err2);
        } else {
            UiValidation.InputSuccessValidationWithHint(label, sucess_message);
        }
    }


}
