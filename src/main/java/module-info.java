module com.example.e_commerce {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.e_commerce to javafx.fxml;
    exports com.example.e_commerce;
}