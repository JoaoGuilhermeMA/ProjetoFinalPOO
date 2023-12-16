package com.example.projetofinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaPorcosController {
    @FXML
    void adicionarPorco(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addPorco.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
}
