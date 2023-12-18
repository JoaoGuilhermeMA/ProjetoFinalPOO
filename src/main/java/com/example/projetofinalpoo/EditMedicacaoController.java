package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Medicacao;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import persistencia.Conexao;
import persistencia.CuidadoDAO;
import persistencia.MedicacaoDAO;

import java.io.IOException;
import java.sql.SQLException;

public class EditMedicacaoController {

    @FXML
    private TextField quantidadeMedicacao;
    @FXML
    private Button voltar;
    @FXML
    private Button salvarDados;
    @FXML
    private TextField nomeMedicacao;

    private Medicacao medicacaoEditar;
    public void initialize() {
        editarMedicacao(null);
    }

    @FXML
    void voltarMedicacao(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("funcionario.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/tableView.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void editarMedicacao(Medicacao medicacao) {
        this.medicacaoEditar = medicacao;

        // Preencha os campos com os dados do funcionário
        if (medicacao != null) {
            nomeMedicacao.setText(medicacao.getNomeMedicacao());
            quantidadeMedicacao.setText(String.valueOf(medicacao.getMlPorKg()));
        }
    }
    @FXML
    void clickAlterar(ActionEvent event) throws SQLException {
        Conexao conexao = new Conexao();
        MedicacaoDAO medDAO = new MedicacaoDAO(conexao.getConexao());
        medicacaoEditar.setNomeMedicacao(nomeMedicacao.getText());
        float quantidadeMl = Float.parseFloat(quantidadeMedicacao.getText());
        medicacaoEditar.setMlPorKg(quantidadeMl);
        medDAO.editarMedicacao(medicacaoEditar);
        conexao.fecharConexao();

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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("medicacao.fxml"));
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
}
