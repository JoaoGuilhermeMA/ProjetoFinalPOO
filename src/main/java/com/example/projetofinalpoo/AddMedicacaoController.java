package com.example.projetofinalpoo;

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
import persistencia.MedicacaoDAO;

import java.io.IOException;
import java.sql.SQLException;

public class AddMedicacaoController {
    @FXML
    private Button buttonCadastrar;

    @FXML
    private TextField labelMedName;

    @FXML
    private TextField labelMedQntd;

    @FXML
    void onCLickButtonCadastrar(ActionEvent event) {
        try {
            Conexao conexao = new Conexao();
            Medicacao med = new Medicacao();
            MedicacaoDAO medDAO = new MedicacaoDAO(conexao.getConexao());

            // Obtendo e configurando o nome da medicação
            med.setNomeMedicacao(labelMedName.getText());

            // Convertendo o texto do TextField para float
            try {
                float mlPorKg = Float.parseFloat(labelMedQntd.getText());
                med.setMlPorKg(mlPorKg);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida para mlPorKg: " + labelMedQntd.getText());
                // Trate a exceção se a conversão falhar
                // Você pode exibir uma mensagem de erro para o usuário ou tomar outra ação apropriada aqui
            }

            // Adicionando a medicação ao banco de dados
            medDAO.adicionarMedicacao(med);

            // Fechando a conexão com o banco de dados
            conexao.fecharConexao();

        } catch (SQLException e) {
            // Trate a exceção se ocorrer um erro relacionado ao banco de dados
            e.printStackTrace();
            // Você pode exibir uma mensagem de erro para o usuário ou tomar outra ação apropriada aqui
        }
    }

    @FXML
    void voltarMedicacao(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("medicacao.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/tableView.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}
