package ant.rentathing.controllers;

import ant.rentathing.classes.User;
import ant.rentathing.classes.products.Product;
import ant.rentathing.util.Loader;
import ant.rentathing.util.SubClassFinder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Set;

public class ManageController extends BaseController {
    private final User user;
    @FXML
    private Pane rootLayout;
    @FXML
    private Label account;
    @FXML
    private VBox productList;

    public ManageController(User user) {
        this.user = user;
    }

    @Override
    @FXML
    public void initialize() throws IOException {
        Loader.loadCss("overview.css", rootLayout);
        account.setText("Account: " + user.getUsername());
        createButtons();
    }

    public void createButtons() {
        productList.getChildren().clear();
        Set<Class<?>> subclasses = SubClassFinder.findSubclasses("ant.rentathing.classes.products", Product.class);
        for (Class<?> subclass : subclasses) {
            Button button = new Button(subclass.getSimpleName());
            BaseController controller = new CreateProductController(user, subclass.getSimpleName());
            button.setOnAction(event -> {
                try {
                    Loader.newWindow("Detail.fxml", controller, "Rent-A-Thing", 480, 360, false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            productList.getChildren().add(button);
        }
    }

    public void handleBackButton() {
        MenuController controller = new MenuController(user);
        Loader.loadFxml(rootLayout, "Menu.fxml", controller);
    }
}
