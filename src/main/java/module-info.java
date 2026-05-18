
module com.example.towerdefensefinale {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.towerdefensefinale to javafx.fxml;
    exports com.example.towerdefensefinale;
}