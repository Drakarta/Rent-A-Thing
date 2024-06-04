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
    requires bcrypt;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens ant.rentathing to javafx.fxml;
    opens ant.rentathing.Controllers to javafx.fxml;
    opens ant.rentathing.Classes to com.fasterxml.jackson.databind;
    exports ant.rentathing;
    exports ant.rentathing.Controllers;
    exports ant.rentathing.Classes;
    exports ant.rentathing.Classes.Products;
    opens ant.rentathing.Classes.Products to com.fasterxml.jackson.databind;
}
