package com.example.projetofinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

import java.io.IOException;

public class MedicacaoController {


    @FXML
    private TableColumn<?, ?> mlporkg;

    @FXML
    private TableColumn<?, ?> nomeMedicacao;

    @FXML
    private TableColumn<?, ?> idMedicacao;
    @FXML
    void adicionarPorco(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("telaMedicacao.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
}
