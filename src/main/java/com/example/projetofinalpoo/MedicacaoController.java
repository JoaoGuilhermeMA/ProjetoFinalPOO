package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Medicacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import persistencia.Conexao;
import persistencia.MedicacaoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MedicacaoController {


    @FXML
    private TableColumn<?, ?> mlporkg;
    @FXML
    private TableColumn<?, ?> nomeMedicacao;
    @FXML
    private TableView<Medicacao> tabelaMedicacao;
    @FXML
    private TableColumn<?, ?> idMedicacao;

    Conexao conexao = new Conexao();
    MedicacaoDAO medicacaoDAO = new MedicacaoDAO(conexao.getConexao());

    public void initialize() {
        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        idMedicacao.setCellValueFactory(new PropertyValueFactory<>("idMedicacao"));
        nomeMedicacao.setCellValueFactory(new PropertyValueFactory<>("nomeMedicacao"));
        mlporkg.setCellValueFactory(new PropertyValueFactory<>("mlPorKg"));

        TableColumn<Medicacao, Void> colunaBotoes = new TableColumn<>("");
        colunaBotoes.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Excluir");
            private final Button editButton = new Button("Editar");

            {
                deleteButton.setStyle(
                        "-fx-cursor: hand ;"
                                + "-fx-font-size: 14px;"
                                + "-fx-background-color: #ff1744;"
                                + "-fx-text-fill: white;"
                );

                editButton.setStyle(
                        "-fx-cursor: hand ;"
                                + "-fx-font-size: 14px;"
                                + "-fx-background-color: #00E676;"
                                + "-fx-text-fill: white;"
                );

                deleteButton.setOnMouseClicked(this::handleDeleteButtonClick);
                editButton.setOnMouseClicked(this::handleEditButtonClick);

                HBox managebtn = new HBox(editButton, deleteButton);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(deleteButton, new javafx.geometry.Insets(2, 2, 0, 3));
                HBox.setMargin(editButton, new javafx.geometry.Insets(2, 3, 0, 2));

                setGraphic(managebtn);
                setText(null);
            }

            private void handleDeleteButtonClick(MouseEvent event) {
                System.out.println("Excluindo");
            }

            private void handleEditButtonClick(MouseEvent event) {
                System.out.println("Editando");
            }
        });

        tabelaMedicacao.getColumns().add(colunaBotoes);
    }

    private void carregarDadosNaTableView() {
        try {
            List<Medicacao> listaMedicacoes = medicacaoDAO.listarMedicacoes();
            ObservableList<Medicacao> listaObservable = FXCollections.observableArrayList(listaMedicacoes);
            tabelaMedicacao.setItems(listaObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void adicionarMedicacao(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addMedicacao.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/criarDados.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void voltarInicio(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("telaInicial.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setScene(scene);
        stage.show();
    }
}
