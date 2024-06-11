package ant.rentathing.controllers;

import ant.rentathing.classes.User;
import ant.rentathing.classes.Observer;
import ant.rentathing.classes.products.Product;
import ant.rentathing.classes.singleton.ProductList;
import ant.rentathing.util.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OverviewController extends BaseController implements Observer<List<Product>> {
    private final User user;
    @FXML
    private Pane rootLayout;
    @FXML
    private Label account;
    @FXML
    private VBox list;

    public OverviewController(User user) {
        this.user = user;
    }

    @Override
    @FXML
    public void initialize() throws IOException {
        Loader.loadCss("overview.css", rootLayout);
        ProductList.getInstance().addObserver(this);
        account.setText("Account: " + user.getUsername());
        updateProductList();
    }

    @Override
    public void update(List<Product> products) throws IOException {
        updateProductList();
    }

    private void handleProductButton(Product product) throws IOException {
        DetailController controller = new DetailController(user, product);
        Loader.newWindow("Detail.fxml", controller, "Detail", 480, 360, false);
    }

    private void createProductButton(Product product) {
        Button button = new Button();
        GridPane gridPane = createProductGridPane(product);
        button.setGraphic(gridPane);
        button.setOnAction(event -> {
            try {
                handleProductButton(product);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        list.getChildren().add(button);
    }

    private GridPane createProductGridPane(Product product) {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);

        Label productType = new Label("Product type: " + product.getProductType());
        Label pricePerDay = new Label("Price/day: " + product.getPricePerDay());
        Label description = new Label(product.getDescription());
        Label availability = new Label("Available: " + product.isAvailable());

        gridPane.add(productType, 0, 0);
        gridPane.add(pricePerDay, 1, 0);
        gridPane.add(description, 0, 1);
        gridPane.add(availability, 1, 1);

        return gridPane;
    }

    public void updateProductList() throws IOException {
        List<Product> products = ProductList.getInstance().getItems();
        list.getChildren().clear();
        for (Product product : products) {
            createProductButton(product);
        }
    }
}
