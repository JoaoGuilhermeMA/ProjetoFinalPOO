package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Vivedouro;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import persistencia.Conexao;
import persistencia.CuidadoDAO;
import persistencia.VivedouroDAO;

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

}

