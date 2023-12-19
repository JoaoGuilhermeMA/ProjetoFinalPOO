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
import persistencia.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditPorcoController {
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
    private Porco porcoEditar;

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

    public void initialize() {
        editarPorco(null);
        carregarDados();
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
    public void editarPorco(Porco porco) {
        this.porcoEditar = porco;

        if (porco != null) {
            selectSexo.setValue(porco.getSexo());
            campoRaca.setText(porco.getRaca());
            selectVivedouro.setValue(porco.getVivedouro());
            campoIdade.setText(String.valueOf(porco.getIdade()));
            campoPeso.setText(porco.getPeso());
            selectMedicacao.setValue(porco.getMedicacao());
            selectCuidador.setValue(porco.getCuidador());
        }
    }
    @FXML
    void clickAlterar(ActionEvent event) throws SQLException {
        Conexao conexao = new Conexao();
        PorcoDAO porcoDAO = new PorcoDAO(conexao.getConexao());
        porcoEditar.setIdade(Integer.parseInt(campoIdade.getText()));
        porcoEditar.setPeso(campoPeso.getText());
        porcoEditar.setRaca(campoRaca.getText());
        porcoEditar.setSexo(selectSexo.getValue());
        porcoEditar.setCuidador(selectCuidador.getValue());
        porcoEditar.setMedicacao(selectMedicacao.getValue());
        porcoEditar.setVivedouro(selectVivedouro.getValue());
        porcoDAO.editarPorco(porcoEditar);
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
}
