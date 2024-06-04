package ant.rentathing.Util;

import ant.rentathing.Controllers.BaseController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Loader {
    public static void LoadFxml(Pane rootLayout, String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Loader.class.getResource("/ant/rentathing/" + fxml));
            Parent newContent = loader.load();
            rootLayout.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LoadFxml(Pane rootLayout, String fxml, BaseController controller) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Loader.class.getResource("/ant/rentathing/" + fxml));
            loader.setController(controller);
            Parent newContent = loader.load();
            rootLayout.getChildren().setAll(newContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void NewWindow(String fxml, BaseController controller, String title, int width, int height) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Loader.class.getResource("/ant/rentathing/" + fxml));
        loader.setController(controller);
        Scene scene = new Scene(loader.load(), width, height);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public static void newAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void LoadCss(String cssPath, Pane rootLayout) {
        URL cssResource = Loader.class.getResource("/ant/rentathing/Stylesheets/" + cssPath);
        if (cssResource == null) {
            System.out.println("CSS resource not found: " + cssPath);
        } else {
            rootLayout.getStylesheets().add(cssResource.toExternalForm());
        }
    }
}
