package ant.rentathing.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginController implements Controllers {
    @FXML
    private Pane rootLayout;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @Override
    @FXML
    public void initialize() {
        rootLayout.getStylesheets().add(getClass().getResource("/Stylesheets/login.css").toExternalForm());
    }
}
