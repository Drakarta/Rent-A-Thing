package ant.rentathing.util;

import ant.rentathing.controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Loader {
    public static final String BASE_RESOURCE_PATH = "/ant/rentathing/";
    public static final String BASE_STYLESHEET_PATH = "/ant/rentathing/Stylesheets/";

    private Loader() {
        // Util Class
    }

    public static void loadFxml(Pane rootLayout, String fxml) {
        loadFxml(rootLayout, fxml, null);
    }

    public static void loadFxml(Pane rootLayout, String fxml, BaseController controller) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Loader.class.getResource(BASE_RESOURCE_PATH + fxml));
            if (controller != null) {
                loader.setController(controller);
            }
            Parent newContent = loader.load();
            rootLayout.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void newWindow(String fxml, String title, int width, int height, boolean exitFunction) throws IOException {
        newWindow(fxml, null, title, width, height, exitFunction);
    }

    public static void newWindow(String fxml, BaseController controller, String title, int width, int height, boolean exitFunction) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Loader.class.getResource(BASE_RESOURCE_PATH + fxml));
        if (controller != null) {
            loader.setController(controller);
        }
        Scene scene = new Scene(loader.load(), width, height);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(scene);
        if (exitFunction) {
            stage.setOnCloseRequest(event -> {
                if (controller != null) {
                    controller.handleWindowClose();
                }
            });
        }
        stage.show();
    }

    public static Alert newAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        return alert;
    }

    public static void loadCss(String cssPath, Pane rootLayout) {
        URL cssResource = Loader.class.getResource(BASE_STYLESHEET_PATH + cssPath);
        if (cssResource == null) {
            System.out.println("CSS resource not found: " + cssPath);
        } else {
            rootLayout.getStylesheets().add(cssResource.toExternalForm());
        }
    }
}
