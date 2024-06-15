package ant.rentathing.controllers.displayHelper;

import ant.rentathing.classes.User;
import ant.rentathing.classes.products.Drill;
import ant.rentathing.classes.products.PassengerCar;
import ant.rentathing.classes.products.Product;
import ant.rentathing.classes.products.Truck;
import ant.rentathing.controllers.DetailController;
import ant.rentathing.util.GridField;
import ant.rentathing.util.NumericTextField;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;

public class DetailDisplayHelper {
    private final DetailController controller;
    private final User user;
    private final Product product;

    public DetailDisplayHelper(DetailController controller, User user, Product product) {
        this.controller = controller;
        this.user = user;
        this.product = product;
    }

    public void displayDetail(VBox detailList) {
        detailList.getChildren().clear();

        GridField grid = new GridField(2);
        grid.addToGridFull(new Label("Product type: " + product.getProductType()));
        grid.addToGridFull(new Label(product.getDescription()));
        grid.nextGrid();
        grid.addToGrid(new Label("Available: " + product.isAvailable()));
        grid.nextGrid();
        grid.addToGrid(addCustomerInfo());
        grid.nextGrid();
        grid.addToGrid(new Label("Employee: " + (product.isAvailable() ? "null" : product.getEmployee().getUsername())));
        grid.addToGrid(new Label("Price: €" + product.getPricePerDay() + "/day"));
        grid.addToGrid(addInsuranceInfo());
        grid.addToGrid(addDaysPicker());

        addProductSpecificDetails(grid.getGrid());

        detailList.getChildren().add(grid.getGrid());
    }

    private Node addCustomerInfo() {
        if (product.isAvailable()) {
            Label customerLabel = new Label("Customer: ");
            TextField customer = new TextField();
            controller.setCustomer(customer);
            return new HBox(customerLabel, customer);
        } else {
            return new Label("Customer: " + product.getCustomer());
        }
    }

    private Node addInsuranceInfo() {
        CheckBox insurance = new CheckBox();
        controller.setInsurance(insurance);
        if (!product.isAvailable()) {
            insurance.setSelected(product.isInsured());
            insurance.setDisable(true);
        } else {
            insurance.setOnAction(event -> controller.updateTotalPrice());
        }
        Label insuranceLabel = new Label(" Insurance: €" + product.getInsurancePerDay() + "/day");
        return new HBox(insurance, insuranceLabel);
    }

    private Node addDaysPicker() {
        if (product.isAvailable()) {
            Label daysPickerLabel = new Label("Days: ");
            NumericTextField daysPicker = new NumericTextField();
            daysPicker.getStyleClass().add("numeric-text-field");
            daysPicker.setText("1");
            daysPicker.textProperty().addListener((observable, oldValue, newValue) -> controller.updateTotalPrice());
            controller.setDaysPicker(daysPicker);
            return new HBox(daysPickerLabel, daysPicker);
        } else {
            return new Label("Days: " + product.getDays());
        }
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

    public void displayBottomBar(HBox botBarRight) {
        controller.updateTotalPrice();
        botBarRight.getChildren().clear();
        if (product.isAvailable()) {
            Button rentButton = new Button("Rent");
            rentButton.setOnAction(event -> {
                try {
                    controller.handleRentAction();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            botBarRight.getChildren().add(rentButton);
        } else {
            Button returnButton = new Button("Return");
            returnButton.setOnAction(event -> {
                try {
                    controller.handleReturnAction();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            botBarRight.getChildren().add(returnButton);
        }
    }
}
