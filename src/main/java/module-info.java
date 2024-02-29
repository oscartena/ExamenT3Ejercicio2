module com.example.exament3ejercicio2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.example.exament3ejercicio2 to javafx.fxml;
    exports com.example.exament3ejercicio2;
}