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
    requires static lombok;
    requires reflections;

    opens ant.rentathing to javafx.fxml, org.testfx;
    opens ant.rentathing.controllers to javafx.fxml, org.testfx;
    opens ant.rentathing.classes.products to com.fasterxml.jackson.databind, org.testfx;
    opens ant.rentathing.classes.singleton to com.fasterxml.jackson.databind, org.testfx;
    opens ant.rentathing.controllers.displayHelper to javafx.fxml, org.testfx;

    exports ant.rentathing;
    exports ant.rentathing.controllers;
    exports ant.rentathing.classes;
    exports ant.rentathing.classes.products;
    exports ant.rentathing.classes.singleton;
    exports ant.rentathing.controllers.displayHelper;

}
