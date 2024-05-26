module ant.rentathing {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens ant.rentathing to javafx.fxml;
    opens ant.rentathing.Controllers to javafx.fxml;
    exports ant.rentathing;
    exports ant.rentathing.Controllers;
}
