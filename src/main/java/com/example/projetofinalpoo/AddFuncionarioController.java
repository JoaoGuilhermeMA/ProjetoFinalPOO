package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Medicacao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import persistencia.Conexao;
import persistencia.CuidadoDAO;
import persistencia.MedicacaoDAO;

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
}
