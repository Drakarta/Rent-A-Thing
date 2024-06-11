package ant.rentathing.controllers;

import ant.rentathing.classes.singleton.Session;
import ant.rentathing.classes.User;
import ant.rentathing.classes.singleton.UserList;
import ant.rentathing.util.Loader;
import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController extends BaseController {
    @FXML
    private Pane rootLayout;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @Override
    @FXML
    public void initialize() {
        Loader.loadCss("login-register.css", rootLayout);
    }

    @FXML
    public void handleSubmitButton() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        for (User user : UserList.getInstance().getItems()) {
            if (!user.getUsername().equals(username)) continue;

            if (Session.getInstance().getActiveUsers().contains(user)) continue;

            if (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                Loader.newAlert(Alert.AlertType.INFORMATION, "Login", "Login successful", null);
                MenuController controller = new MenuController(user);
                Session.getInstance().add(user);
                Loader.newWindow("Menu.fxml", controller, "test", 640, 480, true);
                return;
            }
        }
        Loader.newAlert(Alert.AlertType.ERROR, "Login", "Invalid username or password", null);
    }

    public void handleRegisterButton() {
        Loader.loadFxml(rootLayout, "register.fxml");
    }
}
