package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.prefs.Preferences;
/*
* The utility of this module verifies the user who moves between the pages.
 * So that it saves the user's email temporarily and
 * compares it with the databases. If it exists in the databases it is allowed to initiate
  * the operations imposed on it.
* */
public class Auth {

    private static Auth ourInstance = new Auth();
    private static Preferences preferences = Preferences.userRoot().node(Auth.class.getName());

    public static Auth getInstance() {
        return ourInstance;
    }
    private Auth() {
    }
    // this is hash function give it user password and return hash value {encrypted password saved in the database}
//    compared it with hash key {only person has know the key is the user }
    public String md5(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(message.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    //     initialize session class
    public Preferences start_session() {
        return preferences;
    }
    // check if session is set for Authentication
    public boolean isset() {
        if (preferences.get("username", "session").equals("no_user"))
            return false;
        else
            return true;
    }
// add user email to preference object ------> Done when user login
    public boolean addUser(String value) {
        if (!value.equals("")) {
            try {
                preferences.put("username", value);
                return true;
            } catch (NullPointerException e) {
                Log.e("check your object initialization");
                return false;
            }
        } else {
            return false;
        }
    }
    // add user email to preference object ------> Done when user logout
    public void destroyUser() {
        try {
            preferences.put("username", "no_user");
        } catch (NullPointerException e) {
            Log.e("check your object initialization");
        }
    }
// get current user use the system
    public String getCurrentUser() {
        return preferences.get("username", "session");
    }


//    user redirection


    public  void  RedirectUser(Pane CurrentScene,String destinationScene) throws IOException {
        Parent   secondRoot = FXMLLoader.load(getClass().getResource(destinationScene));
        Scene newScene = new Scene(secondRoot);
        Stage curStage = (Stage) CurrentScene.getScene().getWindow();
        curStage.setScene(newScene);

    }
}
