package ant.rentathing.Controllers;

import ant.rentathing.Classes.Session;
import ant.rentathing.Classes.User;
import ant.rentathing.Classes.UserList;
import ant.rentathing.Util.Loader;
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
        Loader.LoadCss("login-register.css", rootLayout);
    }

    @FXML
    public void handleSubmitButton() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        for (User user : UserList.getInstance().users) {
            if (user.getUsername().equals(username)) {
                if (!Session.getInstance().ActiveUsers.contains(user)) {
                    if (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                        Loader.newAlert(Alert.AlertType.INFORMATION, "Login", "Login successful", null);
                        MenuController controller = new MenuController();
                        controller.user = user;
                        Session.getInstance().add(user);
                        Loader.NewWindow("Menu.fxml", controller, "test", 360, 240);
                        return;
                    }
                }
            }
        }
        Loader.newAlert(Alert.AlertType.ERROR, "Login", "Invalid username or password", null);
    }

    public void handleRegisterButton() {
        Loader.LoadFxml(rootLayout, "register.fxml");
    }
}
