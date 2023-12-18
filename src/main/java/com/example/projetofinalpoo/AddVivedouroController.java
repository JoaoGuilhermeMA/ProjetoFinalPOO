package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Vivedouro;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import persistencia.Conexao;
import persistencia.CuidadoDAO;
import persistencia.VivedouroDAO;

import java.io.IOException;
import java.sql.SQLException;

public class AddVivedouroController {
    @FXML
    private TextField campoTamanho;

    @FXML
    private TextField campoVivedouro;

    @FXML
    private Button salvarDados;

    @FXML
    void onClick(ActionEvent event) throws SQLException {
        Conexao conexao = new Conexao();
        Vivedouro vivedouro = new Vivedouro();
        VivedouroDAO vivDAO = new VivedouroDAO(conexao.getConexao());
        vivedouro.setVivedouro(campoVivedouro.getText());
        vivedouro.setTamanho(campoTamanho.getText());
        vivDAO.adicionarVivedouro(vivedouro);
        conexao.fecharConexao();
    }
    @FXML
    void voltarVivedouro(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("vivedouro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/tableView.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}

