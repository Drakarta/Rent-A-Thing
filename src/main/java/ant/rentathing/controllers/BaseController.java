package ant.rentathing.controllers;

import javafx.fxml.FXML;

import java.io.IOException;

public abstract class BaseController {
    @FXML
    public void initialize() throws IOException {}

    public void handleWindowClose() {}
}
