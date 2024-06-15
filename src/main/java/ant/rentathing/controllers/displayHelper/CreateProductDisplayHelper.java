package ant.rentathing.controllers.displayHelper;

import ant.rentathing.controllers.CreateProductController;
import ant.rentathing.util.GridField;
import ant.rentathing.util.NumericTextField;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;

public class CreateProductDisplayHelper {
    private final CreateProductController controller;
    private final String productType;

    public CreateProductDisplayHelper(CreateProductController controller, String productType) {
        this.controller = controller;
        this.productType = productType;
    }

    public void displayProduct(VBox detailList) {
        GridField grid = new GridField(1);

        grid.addToGrid(new Label("Product type: " + productType));
        grid.addToGrid(addDescription());
        addGridPaneItems(grid);

        detailList.getChildren().add(grid.getGrid());
    }

    private Node addDescription() {
        Label descriptionLabel = new Label("Description: ");
        TextField descriptionField = new TextField();
        controller.setDescriptionField(descriptionField);
        return new HBox(descriptionLabel, descriptionField);
    }

    private void addGridPaneItems(GridField grid) {
        if (productType.equals("Drill")) {
            addDrillFields(grid);
        } else if (productType.equals("PassengerCar")) {
            addPassengerCarFields(grid);
        } else {
            addTruckFields(grid);
        }
    }

    private void addDrillFields(GridField grid) {
        Label brandLabel = new Label("Brand: ");
        TextField brandField = new TextField();
        controller.setBrandField(brandField);
        grid.addToGrid(new HBox(brandLabel, brandField));

        Label typeLabel = new Label("Type: ");
        TextField typeField = new TextField();
        controller.setTypeField(typeField);
        grid.addToGrid(new HBox(typeLabel, typeField));
    }

    private void addPassengerCarFields(GridField grid) {
        Label brandLabel = new Label("Brand: ");
        TextField brandField = new TextField();
        controller.setBrandField(brandField);
        grid.addToGrid(new HBox(brandLabel, brandField));

        Label weightLabel = new Label("Weight: ");
        NumericTextField weightField = new NumericTextField();
        controller.setWeightField(weightField);
        grid.addToGrid(new HBox(weightLabel, weightField));

        Label engineCapacityLabel = new Label("Engine Capacity: ");
        NumericTextField engineCapacityField = new NumericTextField();
        controller.setEngineCapacityField(engineCapacityField);
        grid.addToGrid(new HBox(engineCapacityLabel, engineCapacityField));
    }

    private void addTruckFields(GridField grid) {
        Label loadLabel = new Label("Load: ");
        NumericTextField loadField = new NumericTextField();
        controller.setLoadField(loadField);
        grid.addToGrid(new HBox(loadLabel, loadField));

        Label engineCapacityLabel = new Label("Engine Capacity: ");
        NumericTextField engineCapacityField = new NumericTextField();
        controller.setEngineCapacityField(engineCapacityField);
        grid.addToGrid(new HBox(engineCapacityLabel, engineCapacityField));
    }

    public void displayBottomBar(HBox botBarRight) {
        Button createProductButton = new Button("Create");
        createProductButton.setOnAction(event -> {
            try {
                controller.createProduct();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        botBarRight.getChildren().add(createProductButton);
    }
}
