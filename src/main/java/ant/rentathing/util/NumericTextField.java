package ant.rentathing.util;

import javafx.scene.control.TextField;

public class NumericTextField extends TextField {
    public NumericTextField() {
        this.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setText(newValue.replaceAll("\\D", ""));
            }
        });
    }
}
