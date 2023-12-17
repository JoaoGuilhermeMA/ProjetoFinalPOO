package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Medicacao;
import dominio.Porco;
import dominio.Vivedouro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import persistencia.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddPorcoController {

    @FXML
    private TextField campoIdade;

    @FXML
    private TextField campoPeso;

    @FXML
    private TextField campoRaca;

    @FXML
    private Button salvarDados;

    @FXML
    private ChoiceBox<Cuidador> selectCuidador;

    @FXML
    private ChoiceBox<Medicacao> selectMedicacao;

    @FXML
    private ChoiceBox<String> selectSexo;

    @FXML
    private ChoiceBox<Vivedouro> selectVivedouro;

    Conexao cox = new Conexao();

    public void carregarDados() {
        CuidadoDAO cuidadorDAO = new CuidadoDAO(cox.getConexao());
        MedicacaoDAO medicacaoDAO = new MedicacaoDAO(cox.getConexao());
        VivedouroDAO vivedouroDAO = new VivedouroDAO(cox.getConexao());

        try {
            // Carregar dados para os ChoiceBox de Cuidador
            List<Cuidador> cuidadores = cuidadorDAO.listarCuidadores();
            selectCuidador.getItems().addAll(cuidadores);

            // Carregar dados para os ChoiceBox de Medicacao
            List<Medicacao> medicacoes = medicacaoDAO.listarMedicacoes();
            selectMedicacao.getItems().addAll(medicacoes);

            // Carregar dados para os ChoiceBox de Vivedouro
            List<Vivedouro> vivedouros = vivedouroDAO.listarVivedouros();
            selectVivedouro.getItems().addAll(vivedouros);

            // Adicionar opções de sexo
            selectSexo.getItems().addAll("Masculino", "Feminino");

        } catch (SQLException e) {
            e.printStackTrace();
            // Lida com a exceção de consulta SQL
        }
    }

    @FXML
    public void initialize() {
        carregarDados(); // Chama o método para carregar os dados ao inicializar o controlador
    }

    @FXML
    void onClickCadastrar(ActionEvent event) throws SQLException {
        PorcoDAO porcoDAO = new PorcoDAO(cox.getConexao());

        Porco novoPorco = new Porco();
        novoPorco.setIdade(Integer.parseInt(campoIdade.getText()));
        novoPorco.setPeso(campoPeso.getText());
        novoPorco.setRaca(campoRaca.getText());
        novoPorco.setSexo(selectSexo.getValue()); // Aqui, assumimos que o ChoiceBox contém valores String para o sexo

        // Supondo que os ChoiceBoxes já tenham sido preenchidos com os objetos corretos (Cuidador, Medicacao, Vivedouro)
        novoPorco.setCuidador(selectCuidador.getValue());
        novoPorco.setMedicacao(selectMedicacao.getValue());
        novoPorco.setVivedouro(selectVivedouro.getValue());
        System.out.println("medicacao: " + novoPorco.getMedicacao());
        System.out.println("vivedouro: " + novoPorco.getVivedouro());
        System.out.println("cuidador: " + novoPorco.getCuidador());

        porcoDAO.adicionarPorco(novoPorco);

        cox.fecharConexao();
    }

    @FXML
    void voltarPorco(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("porco.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
}
