package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidation {
    private static FormValidation ourInstance = new FormValidation();

    public  static FormValidation getInstance() {
        return ourInstance;
    }

    private FormValidation() {
    }

    //#region Fields Regex Checks
    public   boolean checkPassword(String text) {
        String theRegex = "[A-Za-z0-9]{6,}";
        Pattern checkRegex = Pattern.compile(theRegex);

        Matcher match = checkRegex.matcher(text);

        while (match.find()) {
            if (match.group().length() != 0 && match.start() == 0)
                return true;
        }
        return false;
    }

    public   boolean checkEmail(String text) {
        String theRegex = "[A-Za-z0-9.-_]+@[A-Za-z]+\\.[A-Za-z]{2,3}";
        Pattern checkRegex = Pattern.compile(theRegex);

        Matcher match = checkRegex.matcher(text);

        while (match.find()) {
            if (match.group().length() != 0 && match.start() == 0)
                return true;
        }

        return false;
    }

    public   boolean checkuserName(String text) {
        String theRegex = "\\w{6,15}\\w";
        Pattern checkRegex = Pattern.compile(theRegex);

        Matcher match = checkRegex.matcher(text);

        while (match.find()) {
            if (match.group().length() != 0 && match.start() == 0)
                return true;
        }

        return false;
    }
    
    
    
    
}
