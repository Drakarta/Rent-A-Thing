package ant.rentathing.controllers;

import ant.rentathing.classes.User;
import ant.rentathing.classes.products.Product;
import ant.rentathing.classes.singleton.ProductList;
import ant.rentathing.controllers.displayHelper.DetailDisplayHelper;
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
public class DetailController extends BaseController {
    private final User user;
    private final Product product;
    private DetailDisplayHelper displayHelper;

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
        this.displayHelper = new DetailDisplayHelper(this, user, product);
    }

    @Override
    @FXML
    public void initialize() {
        Loader.loadCss("detail.css", rootLayout);
        account.setText("Account: " + user.getUsername());
        displayHelper.displayDetail(detailList);
        displayHelper.displayBottomBar(botBarRight);
    }

    public void updateTotalPrice() {
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

    public void handleRentAction() throws IOException {
        String title = "Rent product";
        if (customer.getText().isEmpty()) {
            Loader.newAlert(Alert.AlertType.ERROR, title, "Missing fields.", null);
            return;
        }
        if (daysPicker.getText().isEmpty()) {
            Loader.newAlert(Alert.AlertType.ERROR, title, "Missing fields.", null);
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
            displayHelper.displayDetail(detailList);
            displayHelper.displayBottomBar(botBarRight);
        }
    }

    public void handleReturnAction() throws IOException {
        Alert alert = Loader.newAlert(Alert.AlertType.CONFIRMATION, "Return product", "Are you sure you want to return?", null);
        if (alert.getResult() == ButtonType.OK) {
            product.setAvailable(true);
            product.setCustomer(null);
            product.setEmployee(null);
            product.setInsured(false);
            product.setDays(1);
            ProductList.getInstance().updateProduct(product);
            displayHelper.displayDetail(detailList);
            displayHelper.displayBottomBar(botBarRight);
        }
    }

    public void handleCloseButton(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }

}
