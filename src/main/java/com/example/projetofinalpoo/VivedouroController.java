package com.example.projetofinalpoo;

import dominio.Cuidador;
import dominio.Medicacao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import persistencia.Conexao;
import persistencia.VivedouroDAO;
import dominio.Vivedouro;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VivedouroController {

    @FXML
    private Button adicionar;

    @FXML
    private TableView<Vivedouro> tabelaVivedouro;

    @FXML
    private TableColumn<?, ?> tamanho;

    @FXML
    private TableColumn<?, ?> tipoVivedouro;

    @FXML
    private TableColumn<?, ?> idVivedouro;

    Conexao conexao = new Conexao();
    VivedouroDAO vivedouroDAO = new VivedouroDAO(conexao.getConexao());

    public void initialize() {
        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        idVivedouro.setCellValueFactory(new PropertyValueFactory<>("idVivedouro"));
        tipoVivedouro.setCellValueFactory(new PropertyValueFactory<>("vivedouro"));
        tamanho.setCellValueFactory(new PropertyValueFactory<>("tamanho"));
        TableColumn<Vivedouro, Void> colunaBotoes = new TableColumn<>("");
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

                deleteButton.setOnMouseClicked(event -> {
                    try {
                        deleteButton(event);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                editButton.setOnMouseClicked(event -> {
                    try {
                        editButton(event);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                HBox managebtn = new HBox(editButton, deleteButton);
                managebtn.setStyle("-fx-alignment:center");
                HBox.setMargin(deleteButton, new javafx.geometry.Insets(2, 2, 0, 3));
                HBox.setMargin(editButton, new javafx.geometry.Insets(2, 3, 0, 2));

                setGraphic(managebtn);
                setText(null);
            }

            private void deleteButton(MouseEvent event) throws SQLException {
                Vivedouro vivedouro = getTableView().getItems().get(getIndex());

                ObservableList<Vivedouro> listaObservable = tabelaVivedouro.getItems();
                listaObservable.remove(vivedouro);
                tabelaVivedouro.setItems(listaObservable);
                Conexao conexao = new Conexao();
                vivedouroDAO.excluirVivedouro(vivedouro.getIdVivedouro());
                conexao.fecharConexao();
            }
            private void editButton(MouseEvent event) throws IOException {

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Vivedouro vivedouro = getTableView().getItems().get(getIndex());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("editVivedouro.fxml"));
                Parent root = loader.load();

                EditVivedouroController editVivedouro = loader.getController();
                editVivedouro.editarVivedouro(vivedouro);

                Scene editScene = new Scene(root);
                editScene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/criarDados.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(editScene);
                stage.setOnShown(e -> currentStage.close());
                stage.show();
            }
        });

        tabelaVivedouro.getColumns().add(colunaBotoes);
    }

    private void carregarDadosNaTableView() {
        try {
            List<Vivedouro> listaVivedouros = vivedouroDAO.listarVivedouros();
            ObservableList<Vivedouro> listaObservable = FXCollections.observableArrayList(listaVivedouros);
            tabelaVivedouro.setItems(listaObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void adicionarVivedouro(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addVivedouro.fxml"));
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
