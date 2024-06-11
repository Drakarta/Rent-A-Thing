package ant.rentathing.controllers;

import ant.rentathing.classes.singleton.Session;
import ant.rentathing.classes.User;
import ant.rentathing.util.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class MenuController extends BaseController {
    private final User user;
    @FXML
    private Pane rootLayout;
    @FXML
    private Label account;

    public MenuController(User user) {
        this.user = user;
    }

    @Override
    @FXML
    public void initialize() {
        Loader.loadCss("menu.css", rootLayout);
        account.setText("Account: " + user.getUsername());
    }

    public void handleOverviewButton() {
        OverviewController overviewController = new OverviewController(user);
        Loader.loadFxml(rootLayout, "Overview.fxml", overviewController);
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

    @Override
    public void handleWindowClose() {
        Session.getInstance().remove(user);
    }
}
