package com.example.projetofinalpoo;

import dominio.Porco;
import dominio.Vendidos;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import persistencia.Conexao;
import persistencia.PorcoDAO;
import persistencia.VendidosDAO;

import java.io.IOException;
import java.sql.SQLException;

public class AddVenda {

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField campoCPF;

    @FXML
    private TextField campoPreco;

    @FXML
    private DatePicker selectData;

    Conexao cox = new Conexao();
    private Porco porco;

    @FXML
    void onClickCadastrar(ActionEvent event) throws SQLException {
        VendidosDAO vendidosDAO = new VendidosDAO(cox.getConexao());
        PorcoDAO porcoDAO = new PorcoDAO(cox.getConexao());

        Vendidos novaVenda = new Vendidos();
        novaVenda.setCpfComprador(campoCPF.getText());
        novaVenda.setValorTotal(Float.parseFloat(campoPreco.getText()));
        novaVenda.setIdAnimal(this.porco.getIdAnimal());
        java.sql.Date sqlDate = java.sql.Date.valueOf(selectData.getValue());
        novaVenda.setDataVenda(sqlDate);

        porcoDAO.atualizarStatusVendaPorco(this.porco.getIdAnimal(),false);
        porcoDAO.editarPorco(this.porco);

        vendidosDAO.adicionarVenda(novaVenda);

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
    void onClickVoltar(ActionEvent event) {

    }

    public void setPorcoId(Porco porco) {
        this.porco = porco;
    }

}
