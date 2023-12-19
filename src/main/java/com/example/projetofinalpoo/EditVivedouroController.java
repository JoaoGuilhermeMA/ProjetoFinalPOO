package com.example.projetofinalpoo;

import dominio.Cuidador;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import persistencia.Conexao;
import persistencia.CuidadoDAO;
import persistencia.VivedouroDAO;

import java.io.IOException;
import java.sql.SQLException;

public class EditVivedouroController {

    @FXML
    private Button voltar;

    @FXML
    private Button salvarDados;

    @FXML
    private TextField tipoVivedouro;

    @FXML
    private TextField tamanhoVivedouro;

    private Vivedouro vivedouroEditar;

    public void initialize() {
        editarVivedouro(null);
    }

    @FXML
    void voltarVivedouro(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("vivedouro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/tableView.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public void editarVivedouro(Vivedouro vivedouro) {
        this.vivedouroEditar = vivedouro;

        if (vivedouro != null) {
            tipoVivedouro.setText(vivedouro.getVivedouro());
            tamanhoVivedouro.setText(vivedouro.getTamanho());
        }
    }
    @FXML
    void clickAlterar(ActionEvent event) throws SQLException {
        Conexao conexao = new Conexao();
        VivedouroDAO vivedouroDAO = new VivedouroDAO(conexao.getConexao());
        vivedouroEditar.setVivedouro(tipoVivedouro.getText());
        vivedouroEditar.setTamanho(tamanhoVivedouro.getText());
        vivedouroDAO.editarVivedouro(vivedouroEditar);
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("vivedouro.fxml"));
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