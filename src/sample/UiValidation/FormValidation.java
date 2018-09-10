package sample.UiValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidation {
    private static FormValidation ourInstance = new FormValidation();
    public static FormValidation getInstance() {
        return ourInstance;
    }
    private FormValidation() {
    }

    //#region Fields Regex Checks
    public boolean checkPassword(String text) {
        String theRegex = "[A-Za-z0-9]{6,}";
        return checkRegex(theRegex,text);
    }

    public boolean checkEmail(String text) {
        String theRegex = "[A-Za-z0-9.-_]+@[A-Za-z]+\\.[A-Za-z]{2,3}";
        return checkRegex(theRegex,text);
    }

    public boolean checkuserName(String text) {
        String theRegex = "\\w{6,15}\\w";
        return checkRegex(theRegex,text);
    }
    //checks for numbers only with no spaces
    public boolean isNumberOnly(String text) {
        String theRegex = "[0-9]+$";
        return checkRegex(theRegex,text);
    }
    //checks for letters only with or without spaces
    public boolean isLettersOnly(String text) {
        String theRegex = "[A-Za-z\\s]+$";
        return checkRegex(theRegex,text);
    }
    //#endregion

    private boolean checkRegex(String theRegex, String text)
    {
        Pattern checkRegex = Pattern.compile(theRegex);
        Matcher match = checkRegex.matcher(text);

        while (match.find()) {
            if (match.group().length() != 0 && match.start() == 0)
                return true;
        }

        return false;
    }


}
