package com.empresa.javafx_mongo;

import com.mongodb.client.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.bson.Document;
import org.bson.types.ObjectId;

public class HelloController {
    @FXML
    private TableView<Document> tableView;

    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        String url = "mongodb+srv://lector:lector@cluster0.hbso5zx.mongodb.net/";
        MongoClient conexion = MongoClients.create(url);
        MongoDatabase database = conexion.getDatabase("practica1");
        MongoCollection<Document> collection = database.getCollection("clientes");

        ObservableList<Document> data = FXCollections.observableArrayList();
        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                data.add(cursor.next());
            }
        } finally {
            cursor.close();
        }

        tableView.getItems().clear();
        tableView.getColumns().clear();

        if (!data.isEmpty()) {
            Document firstDoc = data.get(0);
            for (String key : firstDoc.keySet()) {
                TableColumn<Document, String> column = new TableColumn<>(key);
                column.setCellValueFactory(param -> {
                    Object value = param.getValue().get(key);
                    if (value instanceof ObjectId) {
                        value = value.toString();
                    } else if (value instanceof Integer) {
                        value = Integer.toString((Integer) value);
                    }
                    return value != null ? new javafx.beans.property.SimpleStringProperty((String) value) : null;
                });
                tableView.getColumns().add(column);
            }
            tableView.setItems(data);
        }
    }
}
