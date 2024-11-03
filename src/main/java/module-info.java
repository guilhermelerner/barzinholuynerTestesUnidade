module exemplo.barzinholuyner {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens exemplo.barzinholuyner.main to javafx.fxml;
    opens exemplo.barzinholuyner.controller to javafx.fxml;
    opens exemplo.barzinholuyner.model to javafx.base;

    exports exemplo.barzinholuyner.main;
    exports exemplo.barzinholuyner.controller;
}