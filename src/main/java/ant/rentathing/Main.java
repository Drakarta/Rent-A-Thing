package ant.rentathing;

import ant.rentathing.controllers.LoginController;
import ant.rentathing.util.Loader;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Loader.newWindow("Auth.fxml", new LoginController(), "Rent-A-Thing", 320, 240, false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}