module com.example.javafxarchetypefxml {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.example.javafxarchetypefxml to javafx.fxml;
    exports com.example.javafxarchetypefxml;
}