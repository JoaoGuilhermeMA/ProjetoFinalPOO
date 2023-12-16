package com.example.projetofinalpoo;

import dominio.Cuidador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import persistencia.Conexao;
import javafx.scene.control.TableView;
import persistencia.CuidadoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {

    @FXML
    private TableColumn<?, ?> colunaID;

    @FXML
    private TableColumn<?, ?> colunaNome;

    @FXML
    private TableColumn<?, ?> colunaProfissao;

    @FXML
    private TableColumn<?, ?> colunaSalario;

    @FXML
    private TableColumn<?, ?> colunaTelefone;


    @FXML
    private TableView<Cuidador> tableViewCuidadores;

    Conexao conexao = new Conexao();
    CuidadoDAO cuidadorDAO = new CuidadoDAO(conexao.getConexao());
    public void initialize() {

        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        // Configurando as colunas para corresponder aos atributos da classe Cuidador
        // Supondo que colunaID seja do tipo Integer, colunaNome, colunaProfissao sejam Strings, e colunaSalario, colunaTelefone sejam Float
        colunaID.setCellValueFactory(new PropertyValueFactory<>("idCuidador"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaProfissao.setCellValueFactory(new PropertyValueFactory<>("profissao"));
        colunaSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
    }

    private void carregarDadosNaTableView() {
        try {
            List<Cuidador> listaCuidadores = cuidadorDAO.listarCuidadores();
            ObservableList<Cuidador> listaObservable = FXCollections.observableArrayList(listaCuidadores);
            tableViewCuidadores.setItems(listaObservable);
        } catch (SQLException e) {
            // Lidar com exceções
            e.printStackTrace();
        }
    }


    @FXML
    void adicionarFuncionario(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addFuncionario.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
}
