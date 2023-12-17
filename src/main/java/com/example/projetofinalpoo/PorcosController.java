package com.example.projetofinalpoo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import persistencia.Conexao;
import persistencia.PorcoDAO;
import dominio.Porco;

public class PorcosController {

    @FXML
    private TableView<Porco> tabelaPorcos;

    @FXML
    private TableColumn<Porco, Integer> idPorco;

    @FXML
    private TableColumn<Porco, Integer> idade;

    @FXML
    private TableColumn<Porco, String> vivedouro;

    @FXML
    private TableColumn<Porco, Double> peso;

    @FXML
    private TableColumn<Porco, String> vacinas;

    @FXML
    private TableColumn<Porco, String> raca;

    @FXML
    private TableColumn<Porco, String> cuidador;

    @FXML
    private TableColumn<Porco, String> sexo;

    Conexao conexao = new Conexao();
    PorcoDAO porcoDAO = new PorcoDAO(conexao.getConexao());

    public void initialize() {
        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        idPorco.setCellValueFactory(new PropertyValueFactory<>("idAnimal"));
        idade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        vivedouro.setCellValueFactory(new PropertyValueFactory<>("vivedouro"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        vacinas.setCellValueFactory(new PropertyValueFactory<>("medicacao"));
        raca.setCellValueFactory(new PropertyValueFactory<>("raca"));
        cuidador.setCellValueFactory(new PropertyValueFactory<>("cuidador"));
        sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
    }

    private void carregarDadosNaTableView() {
        try {
            List<Porco> listaPorcos = porcoDAO.listarPorcosComRelacoes();
            ObservableList<Porco> listaObservable = FXCollections.observableArrayList(listaPorcos);
            tabelaPorcos.setItems(listaObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
