package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Vivedouro;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import persistencia.Conexao;
import persistencia.CuidadoDAO;
import persistencia.VivedouroDAO;

import java.io.IOException;
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
    @FXML
    void voltarVivedouro(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("vivedouro.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/tableView.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

}

