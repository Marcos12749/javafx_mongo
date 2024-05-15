module com.empresa.javafx_mongo {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;


    opens com.empresa.javafx_mongo to javafx.fxml;
    exports com.empresa.javafx_mongo;
}