package ant.rentathing.controllers;

import ant.rentathing.classes.User;
import ant.rentathing.util.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ManageController extends BaseController {
    private final User user;
    @FXML
    private Pane rootLayout;
    @FXML
    private Label account;

    public ManageController(User user) {
        this.user = user;
    }

    @Override
    @FXML
    public void initialize() {
        Loader.loadCss("overview.css", rootLayout);
        account.setText("Account: " + user.getUsername());
    }

}
