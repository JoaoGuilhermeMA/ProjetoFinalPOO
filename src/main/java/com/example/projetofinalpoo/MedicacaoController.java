package com.example.projetofinalpoo;

import dominio.Medicacao;
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
import persistencia.Conexao;
import persistencia.MedicacaoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MedicacaoController {


    @FXML
    private TableColumn<?, ?> mlporkg;
    @FXML
    private TableColumn<?, ?> nomeMedicacao;
    @FXML
    private TableView<Medicacao> tabelaMedicacao;
    @FXML
    private TableColumn<?, ?> idMedicacao;

    Conexao conexao = new Conexao();
    MedicacaoDAO medicacaoDAO = new MedicacaoDAO(conexao.getConexao());

    public void initialize() {
        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        idMedicacao.setCellValueFactory(new PropertyValueFactory<>("idMedicacao"));
        nomeMedicacao.setCellValueFactory(new PropertyValueFactory<>("nomeMedicacao"));
        mlporkg.setCellValueFactory(new PropertyValueFactory<>("mlPorKg"));
    }

    private void carregarDadosNaTableView() {
        try {
            List<Medicacao> listaMedicacoes = medicacaoDAO.listarMedicacoes();
            ObservableList<Medicacao> listaObservable = FXCollections.observableArrayList(listaMedicacoes);
            tabelaMedicacao.setItems(listaObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void adicionarMedicacao(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addMedicacao.fxml"));
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
