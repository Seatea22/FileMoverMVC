module com.example.filemovermvc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.main.filemovermvc to javafx.fxml;
    exports com.main.filemovermvc;
}