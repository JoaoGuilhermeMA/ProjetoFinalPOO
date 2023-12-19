package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Medicacao;
import dominio.Porco;
import dominio.Vivedouro;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
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
            List<Cuidador> cuidadores = cuidadorDAO.listarCuidadores();
            selectCuidador.getItems().addAll(cuidadores);

            List<Medicacao> medicacoes = medicacaoDAO.listarMedicacoes();
            selectMedicacao.getItems().addAll(medicacoes);

            List<Vivedouro> vivedouros = vivedouroDAO.listarVivedouros();
            selectVivedouro.getItems().addAll(vivedouros);

            selectSexo.getItems().addAll("Masculino", "Feminino");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        carregarDados();
    }

    @FXML
    void onClickCadastrar(ActionEvent event) throws SQLException {
        PorcoDAO porcoDAO = new PorcoDAO(cox.getConexao());

        Porco novoPorco = new Porco();
        novoPorco.setIdade(Integer.parseInt(campoIdade.getText()));
        novoPorco.setPeso(campoPeso.getText());
        novoPorco.setRaca(campoRaca.getText());
        novoPorco.setSexo(selectSexo.getValue());
        novoPorco.setCuidador(selectCuidador.getValue());
        novoPorco.setMedicacao(selectMedicacao.getValue());
        novoPorco.setVivedouro(selectVivedouro.getValue());

        porcoDAO.adicionarPorco(novoPorco);

        cox.fecharConexao();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sucesso");
        alert.setHeaderText(null);
        alert.setContentText("Dados atualizados com sucesso!");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle(
                "-fx-background-color: white;" +
                        "-fx-border-color: #00E676;" +
                        "-fx-border-width: 2px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-padding: 10px;" +
                        "-fx-max-width: 300px"
        );
        alert.showAndWait();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Platform.runLater(() -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                currentStage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("porcos.fxml"));
                Parent root;
                try {
                    root = loader.load();
                    Scene editScene = new Scene(root);
                    editScene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/tableView.css").toExternalForm());
                    Stage stage = new Stage();
                    stage.setScene(editScene);
                    stage.show();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }));
            timeline.play();
        });
    }

    @FXML
    void voltarPorco(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("porcos.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/tableView.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}
