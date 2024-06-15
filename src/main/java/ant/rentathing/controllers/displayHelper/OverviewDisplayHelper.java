package ant.rentathing.controllers.displayHelper;

import ant.rentathing.classes.User;
import ant.rentathing.classes.products.Product;
import ant.rentathing.classes.singleton.ProductList;
import ant.rentathing.controllers.OverviewController;
import ant.rentathing.util.GridField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class OverviewDisplayHelper {
    private final OverviewController controller;
    private final User user;

    public OverviewDisplayHelper(OverviewController controller, User user) {
        this.controller = controller;
        this.user = user;
    }

    public void updateProductList(VBox list) throws IOException {
        List<Product> products = ProductList.getInstance().getItems();
        list.getChildren().clear();
        for (Product product : products) {
            createProductButton(product, list);
        }
    }

    private void createProductButton(Product product, VBox list) {
        Button button = new Button();
        GridPane gridPane = createProductGridPane(product);
        button.setGraphic(gridPane);
        button.setOnAction(event -> {
            try {
                controller.handleProductButton(product);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        list.getChildren().add(button);
    }

    private GridPane createProductGridPane(Product product) {
        GridField grid = new GridField(2);

        grid.addToGrid(new Label("Product type: " + product.getProductType()));
        grid.addToGrid(new Label("Price/day: " + product.getPricePerDay()));
        grid.addToGrid(new Label(product.getDescription()));
        grid.addToGrid(new Label("Available: " + product.isAvailable()));

        return grid.getGrid();
    }
}
