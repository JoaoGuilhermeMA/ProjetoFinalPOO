package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Medicacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import persistencia.Conexao;
import persistencia.CuidadoDAO;
import persistencia.MedicacaoDAO;

import java.io.IOException;
import java.sql.SQLException;

public class AddFuncionarioController {
    @FXML
    private Button btnCadastrar;

    @FXML
    private TextField labelCPF;

    @FXML
    private TextField labelNome;

    @FXML
    private TextField labelProfissao;

    @FXML
    private TextField labelSalario;

    @FXML
    private TextField labelTelefone;

    @FXML
    void clickCadastrar(ActionEvent event) throws SQLException {
        Conexao conexao = new Conexao();
        Cuidador cud = new Cuidador();
        CuidadoDAO cudDAO = new CuidadoDAO(conexao.getConexao());
        cud.setCpf(labelCPF.getText());
        cud.setNome(labelNome.getText());
        cud.setTelefone(labelTelefone.getText());
        cud.setProfissao(labelProfissao.getText());
        float salario = Float.parseFloat(labelSalario.getText());
        cud.setSalario(salario);
        cudDAO.adicionarCuidador(cud);
        conexao.fecharConexao();
    }

    @FXML
    void voltarFuncionario(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("funcionario.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/tableView.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
