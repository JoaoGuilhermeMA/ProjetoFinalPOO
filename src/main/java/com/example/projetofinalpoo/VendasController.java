package com.example.projetofinalpoo;

import dominio.Vendidos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import persistencia.Conexao;
import persistencia.VendidosDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VendasController {

    @FXML
    private TableColumn<Vendidos, Integer> idAnimal;

    @FXML
    private TableColumn<Vendidos, String> cpfComprador;

    @FXML
    private TableColumn<Vendidos, String> dataVenda;

    @FXML
    private TableColumn<Vendidos, Float> valorVenda;

    @FXML
    private TableView<Vendidos> tabelaVendas;

    private final VendidosDAO vendidosDAO = new VendidosDAO(new Conexao().getConexao());

    public void initialize() {
        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        idAnimal.setCellValueFactory(new PropertyValueFactory<>("idAnimal"));
        cpfComprador.setCellValueFactory(new PropertyValueFactory<>("cpfComprador"));
        // Se a data for um tipo diferente de String, você precisa ajustar o tipo da coluna
        dataVenda.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
        valorVenda.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
    }

    private void carregarDadosNaTableView() {
        try {
            List<Vendidos> listaVendas = vendidosDAO.listarVendas();
            ObservableList<Vendidos> listaObservable = FXCollections.observableArrayList(listaVendas);
            tabelaVendas.setItems(listaObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
