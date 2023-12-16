package com.example.projetofinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class VendasController {

    @FXML
    private TableColumn<?, ?> idAnimal;
    @FXML
    private TableColumn<?, ?> dataVenda;
    @FXML
    private TableColumn<?, ?> valorVenda;
    @FXML
    private TableColumn<?, ?> cpfComprador;
    @FXML
    private TableView<?> tabelaVendas;
    @FXML
    private TableColumn<?, ?> idVenda;

    @FXML
    void voltarInicio(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("telaInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }

}
