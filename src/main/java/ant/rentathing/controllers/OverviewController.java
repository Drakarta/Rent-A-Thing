package ant.rentathing.controllers;

import ant.rentathing.classes.User;
import ant.rentathing.classes.Observer;
import ant.rentathing.classes.products.Product;
import ant.rentathing.classes.singleton.ProductList;
import ant.rentathing.controllers.displayHelper.OverviewDisplayHelper;
import ant.rentathing.util.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OverviewController extends BaseController implements Observer<List<Product>> {
    private final User user;
    private final OverviewDisplayHelper displayHelper;

    @FXML
    private Pane rootLayout;
    @FXML
    private Label account;
    @FXML
    private VBox list;

    public OverviewController(User user) {
        this.user = user;
        this.displayHelper = new OverviewDisplayHelper(this, user);
    }

    @Override
    @FXML
    public void initialize() throws IOException {
        Loader.loadCss("overview.css", rootLayout);
        ProductList.getInstance().addObserver(this);
        account.setText("Account: " + user.getUsername());
        displayHelper.updateProductList(list);
    }

    @Override
    public void update(List<Product> products) throws IOException {
        displayHelper.updateProductList(list);
    }

    public void handleProductButton(Product product) throws IOException {
        DetailController controller = new DetailController(user, product);
        Loader.newWindow("Detail.fxml", controller, "Rent-A-Thing", 480, 360, false);
    }

    public void handleBackButton() {
        MenuController controller = new MenuController(user);
        Loader.loadFxml(rootLayout, "Menu.fxml", controller);
    }
}
