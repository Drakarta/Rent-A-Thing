package ant.rentathing.controllers;

import ant.rentathing.classes.User;
import ant.rentathing.util.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class RegisterController extends AuthController {
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
        title.setText("Register");
        changeWindow.setText("Login");
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
        if (username.isEmpty() && password.isEmpty()) {
            Loader.newAlert(Alert.AlertType.ERROR, "Register", "Invalid fields", null);
            return;
        }
        new User(username, password);
        Loader.newAlert(Alert.AlertType.INFORMATION, "Register", "Registration successful", null);
        Loader.loadFxml(rootLayout, "Auth.fxml", new LoginController());
    }

    @Override
    public void handleChangeWindowButton() {
        Loader.loadFxml(rootLayout, "Auth.fxml", new LoginController());
    }
}
