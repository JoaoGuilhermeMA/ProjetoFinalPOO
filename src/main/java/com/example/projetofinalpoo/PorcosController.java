package com.example.projetofinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class PorcosController {
    @FXML
    private TableView<?> tabelaPorcos;
    @FXML
    private TableColumn<?, ?> idade;
    @FXML
    private TableColumn<?, ?> vivedouro;
    @FXML
    private TableColumn<?, ?> peso;
    @FXML
    private TableColumn<?, ?> vacinas;
    @FXML
    private TableColumn<?, ?> raca;
    @FXML
    private TableColumn<?, ?> idPorco;
    @FXML
    private TableColumn<?, ?> cuidador;
    @FXML
    private TableColumn<?, ?> sexo;

    @FXML
    void adicionarPorco(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addPorco.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/criarDados.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void voltarInicio(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("telaInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
}
