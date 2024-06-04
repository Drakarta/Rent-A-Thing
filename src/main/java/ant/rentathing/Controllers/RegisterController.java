package ant.rentathing.Controllers;

import ant.rentathing.Classes.User;
import ant.rentathing.Util.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class RegisterController extends BaseController {
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
        new User(username, password);
        Loader.newAlert(Alert.AlertType.INFORMATION, "Register", "Registration successful", null);
        Loader.LoadFxml(rootLayout, "Login.fxml");
    }


    public void handleLoginButton() {
        Loader.LoadFxml(rootLayout, "Login.fxml");
    }
}
