module com.example.bookregister2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bookregister2 to javafx.fxml;
    exports com.example.bookregister2;
}