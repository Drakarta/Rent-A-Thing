package ant.rentathing.controllers;

import ant.rentathing.classes.singleton.Session;
import ant.rentathing.classes.User;
import ant.rentathing.classes.singleton.UserList;
import ant.rentathing.util.Loader;
import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController extends AuthController {
    @FXML
    private Pane rootLayout;
    @FXML
    private Label title;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button changeWindow;

    @Override
    @FXML
    public void initialize() {
        Loader.loadCss("auth.css", rootLayout);
        title.setText("Login");
        changeWindow.setText("Register");
    }

    @Override
    public String getUsername() {
        return usernameField.getText();
    }

    @Override
    public String getPassword() {
        return passwordField.getText();
    }

    @Override
    public void authProcess(String username, String password) throws IOException {
        for (User user : UserList.getInstance().getItems()) {
            if (!user.getUsername().equals(username)) continue;

            if (Session.getInstance().getActiveUsers().contains(user)) continue;

            if (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                Loader.newAlert(Alert.AlertType.INFORMATION, "Login", "Login successful", null);
                MenuController controller = new MenuController(user);
                Session.getInstance().add(user);
                Loader.newWindow("Menu.fxml", controller, "Rent-A-Thing", 640, 480, true);
                System.out.println(Session.getInstance().activeUsers);
                return;
            }
        }
        Loader.newAlert(Alert.AlertType.ERROR, "Login", "Invalid username or password", null);

    }

    @Override
    public void handleChangeWindowButton() {
        Loader.loadFxml(rootLayout, "Auth.fxml", new RegisterController());
    }
}
