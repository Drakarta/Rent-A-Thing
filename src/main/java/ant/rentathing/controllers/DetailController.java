package ant.rentathing.controllers;

import ant.rentathing.classes.User;
import ant.rentathing.classes.products.Drill;
import ant.rentathing.classes.products.PassengerCar;
import ant.rentathing.classes.products.Product;
import ant.rentathing.classes.products.Truck;
import ant.rentathing.classes.singleton.ProductList;
import ant.rentathing.util.Loader;
import ant.rentathing.util.NumericTextField;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailController extends BaseController {
    private final User user;
    private final Product product;

    @FXML
    private Pane rootLayout;
    @FXML
    private Label account;
    @FXML
    private VBox detailList;
    @FXML
    private Label totalPrice;
    @FXML
    private HBox botBarRight;
    private TextField customer;
    private NumericTextField daysPicker;
    private CheckBox insurance;

    public DetailController(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    @Override
    @FXML
    public void initialize() {
        Loader.loadCss("detail.css", rootLayout);
        account.setText("Account: " + user.getUsername());
        displayDetail();
        displayBottomBar();
    }

    private void displayDetail() {
        detailList.getChildren().clear();

        GridPane gridPane = createGridPane();

        addBasicProductInfo(gridPane);
        addCustomerInfo(gridPane);
        addEmployeeInfo(gridPane);
        addPriceInfo(gridPane);
        addDaysPicker(gridPane);
        addProductSpecificDetails(gridPane);

        detailList.getChildren().add(gridPane);
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(col1, col2);

        return gridPane;
    }

    private void addBasicProductInfo(GridPane gridPane) {
        Label productType = new Label("Product type: " + product.getProductType());
        gridPane.add(productType, 0, 0, 2, 1);

        Label description = new Label(product.getDescription());
        gridPane.add(description, 0, 1, 2, 1);
    }

    private void addCustomerInfo(GridPane gridPane) {
        Label availability = new Label("Available: " + product.isAvailable());
        gridPane.add(availability, 1, 2);

        if (product.isAvailable()) {
            Label customerLabel = new Label("Customer: ");
            customer = new TextField();
            HBox hbox = new HBox(customerLabel, customer);
            gridPane.add(hbox, 1, 3);
        } else {
            Label customerLabel = new Label("Customer: " + product.getCustomer());
            gridPane.add(customerLabel, 1, 3);
        }
    }

    private void addEmployeeInfo(GridPane gridPane) {
        Label employee = new Label("Employee: " + (product.isAvailable() ? "null" : product.getEmployee().getUsername()));
        gridPane.add(employee, 1, 4);
    }

    private void addPriceInfo(GridPane gridPane) {
        Label pricePerDay = new Label("Price: €" + product.getPricePerDay() + "/day");
        gridPane.add(pricePerDay, 0, 5);

        insurance = new CheckBox();
        if (!product.isAvailable()) {
            insurance.setSelected(product.isInsured());
            insurance.setDisable(true);
        } else {
            insurance.setOnAction(event -> updateTotalPrice());
        }
        Label insuranceLabel = new Label(" Insurance: €" + product.getInsurancePerDay() + "/day");
        HBox hbox = new HBox(insurance, insuranceLabel);
        gridPane.add(hbox, 1, 5);
    }

    private void addDaysPicker(GridPane gridPane) {
        if (product.isAvailable()) {
            Label daysPickerLabel = new Label("Days: ");
            daysPicker = new NumericTextField();
            daysPicker.getStyleClass().add("numeric-text-field");
            daysPicker.setText("1");
            daysPicker.textProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice());
            HBox hbox = new HBox(daysPickerLabel, daysPicker);
            gridPane.add(hbox, 0, 6);
        } else {
            Label daysPickerLabel = new Label("Days: " + product.getDays());
            gridPane.add(daysPickerLabel, 0, 6);
        }
    }

    private void updateTotalPrice() {
        int days;
        if (product.isAvailable()) {
            if (daysPicker.getText().isEmpty()) {
                return;
            }
            days = Integer.parseInt(daysPicker.getText());
        } else {
            days = product.getDays();
        }
        boolean includeInsurance = insurance.isSelected();
        double total = product.calculateTotalPrice(days, includeInsurance);
        totalPrice.setText("Total price: €" + total);
    }

    private void addProductSpecificDetails(GridPane gridPane) {
        switch (product.getProductType()) {
            case "Drill":
                Drill drill = (Drill) product;
                addDrillDetails(gridPane, drill);
                break;
            case "PassengerCar":
                PassengerCar car = (PassengerCar) product;
                addPassengerCarDetails(gridPane, car);
                break;
            case "Truck":
                Truck truck = (Truck) product;
                addTruckDetails(gridPane, truck);
                break;
            default:
                break;
        }
    }

    private void addDrillDetails(GridPane gridPane, Drill drill) {
        Label brand = new Label("Brand: " + drill.getBrand());
        gridPane.add(brand, 0, 2);

        Label type = new Label("Type: " + drill.getType());
        gridPane.add(type, 0, 3);
    }

    private void addPassengerCarDetails(GridPane gridPane, PassengerCar car) {
        Label brand = new Label("Brand: " + car.getBrand());
        gridPane.add(brand, 0, 2);

        Label weight = new Label("Weight: " + car.getWeight());
        gridPane.add(weight, 0, 3);

        Label engineCapacity = new Label("Engine capacity: " + car.getEngineCapacity());
        gridPane.add(engineCapacity, 0, 4);
    }

    private void addTruckDetails(GridPane gridPane, Truck truck) {
        Label loadCapacity = new Label("Load Capacity: " + truck.getLoadCapacity());
        gridPane.add(loadCapacity, 0, 2);

        Label engineCapacity = new Label("Engine Capacity: " + truck.getEngineCapacity());
        gridPane.add(engineCapacity, 0, 3);
    }

    private void displayBottomBar() {
        updateTotalPrice();
        botBarRight.getChildren().clear();
        if (product.isAvailable()) {
            Button rentButton = new Button("Rent");
            rentButton.setOnAction(event -> {
                try {
                    handleRentAction();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            botBarRight.getChildren().add(rentButton);
        } else {
            Button returnButton = new Button("Return");
            returnButton.setOnAction(event -> {
                try {
                    handleReturnAction();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            botBarRight.getChildren().add(returnButton);
        }

    }

    private void handleRentAction() throws IOException {
        String title = "Rent product";
        if (customer.getText().isEmpty()) {
            Loader.newAlert(Alert.AlertType.ERROR, title, "Customer field is required.", null);
            return;
        }
        if (daysPicker.getText().isEmpty()) {
            Loader.newAlert(Alert.AlertType.ERROR, title, "Days field is required.", null);
            return;
        }
        Alert alert = Loader.newAlert(Alert.AlertType.CONFIRMATION, title, "Are you sure you want to rent?", null);
        if (alert.getResult() == ButtonType.OK) {
            product.setAvailable(false);
            product.setCustomer(customer.getText());
            product.setEmployee(user);
            product.setInsured(insurance.isSelected());
            product.setDays(Integer.parseInt(daysPicker.getText()));
            ProductList.getInstance().updateProduct(product);
            displayDetail();
            displayBottomBar();
        }
    }

    private void handleReturnAction() throws IOException {
        Alert alert = Loader.newAlert(Alert.AlertType.CONFIRMATION, "Return product", "Are you sure you want to return?", null);
        if (alert.getResult() == ButtonType.OK) {
            product.setAvailable(true);
            product.setCustomer(null);
            product.setEmployee(null);
            product.setInsured(false);
            product.setDays(1);
            ProductList.getInstance().updateProduct(product);
            displayDetail();
            displayBottomBar();
        }
    }

    public void handleCloseButton(javafx.event.ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }
}
