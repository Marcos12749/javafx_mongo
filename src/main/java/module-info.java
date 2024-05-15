module com.empresa.javafx_mongo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.empresa.javafx_mongo to javafx.fxml;
    exports com.empresa.javafx_mongo;
}