package ant.rentathing.controllers;

import ant.rentathing.classes.User;
import ant.rentathing.classes.products.Drill;
import ant.rentathing.classes.products.PassengerCar;
import ant.rentathing.classes.products.Product;
import ant.rentathing.classes.products.Truck;
import ant.rentathing.controllers.displayHelper.CreateProductDisplayHelper;
import ant.rentathing.util.Loader;
import ant.rentathing.util.NumericTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lombok.Setter;

import java.io.IOException;

@Setter
public class CreateProductController extends BaseController {
    private User user;
    private String productType;
    private CreateProductDisplayHelper displayHelper;

    @FXML
    private Pane rootLayout;
    @FXML
    private Label account;
    @FXML
    private VBox detailList;
    @FXML
    private HBox botBarRight;

    // Fields to gather input data
    private TextField descriptionField;
    private TextField brandField;
    private TextField typeField;
    private NumericTextField weightField;
    private NumericTextField engineCapacityField;
    private NumericTextField loadField;

    public CreateProductController(User user, String productType) {
        this.user = user;
        this.productType = productType;
        this.displayHelper = new CreateProductDisplayHelper(this, productType);
    }

    @Override
    @FXML
    public void initialize() throws IOException {
        Loader.loadCss("detail.css", rootLayout);
        if (user != null) {
            account.setText("Account: " + user.getUsername());
        }
        displayHelper.displayProduct(detailList);
        displayHelper.displayBottomBar(botBarRight);
    }

    public void createProduct() throws IOException {
        String description = descriptionField.getText();

        Product product = null;
        switch (productType) {
            case "Drill":
                String drillBrand = brandField.getText();
                String drillType = typeField.getText();
                if (validateFields(description, drillBrand, drillType)) {
                    Alert alert = Loader.newAlert(Alert.AlertType.CONFIRMATION, "Add product", "Are you sure you want to add the product?", null);
                    if (alert.getResult() == ButtonType.OK) {
                        product = new Drill(drillBrand, drillType, description);
                    }
                }
                break;
            case "PassengerCar":
                String carBrand = brandField.getText();
                String weightText = weightField.getText();
                String engineCapacityText = engineCapacityField.getText();
                if (validateFields(description, carBrand, weightText, engineCapacityText)) {
                    int weight = Integer.parseInt(weightText);
                    int engineCapacity = Integer.parseInt(engineCapacityText);
                    Alert alert = Loader.newAlert(Alert.AlertType.CONFIRMATION, "Add product", "Are you sure you want to add the product?", null);
                    if (alert.getResult() == ButtonType.OK) {
                        product = new PassengerCar(carBrand, description, weight, engineCapacity);
                    }
                }
                break;
            case "Truck":
                String loadText = loadField.getText();
                String truckEngineCapacityText = engineCapacityField.getText();
                if (validateFields(description, loadText, truckEngineCapacityText)) {
                    int load = Integer.parseInt(loadText);
                    int truckEngineCapacity = Integer.parseInt(truckEngineCapacityText);
                    Alert alert = Loader.newAlert(Alert.AlertType.CONFIRMATION, "Add product", "Are you sure you want to add the product?", null);
                    if (alert.getResult() == ButtonType.OK) {
                        product = new Truck(description, load, truckEngineCapacity);
                    }
                }
                break;
            default:
                break;
        }

        if (product == null) {
            Loader.newAlert(Alert.AlertType.ERROR, "Add product", "Missing fields", null);
        }
    }

    private boolean validateFields(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void handleCloseButton(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }

}
