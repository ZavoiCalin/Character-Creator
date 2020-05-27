module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jfxrt;
    requires rt;

    opens org.example to javafx.fxml;
    exports org.example;
}