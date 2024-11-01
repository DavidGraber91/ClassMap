module org.example.tala {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.tala to javafx.fxml;
    exports org.example.tala;
}