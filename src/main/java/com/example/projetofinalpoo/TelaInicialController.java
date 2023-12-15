package com.example.projetofinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TelaInicialController {

    @FXML
    void telaPorcos(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("telaPorcos.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void telaMedicacao(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("telaMedicacao.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setScene(scene);
        stage.show();
    }
}
