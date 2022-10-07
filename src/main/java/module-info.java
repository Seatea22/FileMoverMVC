module com.example.filemovermvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.api.filemovermvc to javafx.fxml;
    exports com.api.filemovermvc;
}