package ant.rentathing.Controllers;

import ant.rentathing.Classes.Session;
import ant.rentathing.Classes.User;
import ant.rentathing.Util.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MenuController extends BaseController {
    public User user;
    @FXML
    private Pane rootLayout;
    @FXML
    private Label account;

    @Override
    @FXML
    public void initialize() {
        Loader.LoadCss("login-register.css", rootLayout);
        account.setText(user.getUsername());
    }

    public void handleOverviewButton() {
        System.out.println(user.getUsername());
    }

    public void handleManageButton() {
        System.out.println(user.getUsername());
    }

    public void handleLogOutButton(ActionEvent event) {
        Session.getInstance().remove(user);
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }
}
