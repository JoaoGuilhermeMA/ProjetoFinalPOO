package com.example.projetofinalpoo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import persistencia.Conexao;
import persistencia.VivedouroDAO;
import dominio.Vivedouro;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VivedouroController {

    @FXML
    private Button adicionar;

    @FXML
    private TableView<Vivedouro> tabelaVivedouro;

    @FXML
    private TableColumn<?, ?> tamanho;

    @FXML
    private TableColumn<?, ?> tipoVivedouro;

    @FXML
    private TableColumn<?, ?> idVivedouro;

    Conexao conexao = new Conexao();
    VivedouroDAO vivedouroDAO = new VivedouroDAO(conexao.getConexao());

    public void initialize() {
        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        idVivedouro.setCellValueFactory(new PropertyValueFactory<>("idVivedouro"));
        tipoVivedouro.setCellValueFactory(new PropertyValueFactory<>("vivedouro"));
        tamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
    }

    private void carregarDadosNaTableView() {
        try {
            List<Vivedouro> listaVivedouros = vivedouroDAO.listarVivedouros();
            ObservableList<Vivedouro> listaObservable = FXCollections.observableArrayList(listaVivedouros);
            tabelaVivedouro.setItems(listaObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void adicionarVivedouro(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addVivedouro.fxml"));
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
