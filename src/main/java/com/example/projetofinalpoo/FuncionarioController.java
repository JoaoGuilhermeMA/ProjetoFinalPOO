package com.example.projetofinalpoo;

import dominio.Cuidador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import javafx.scene.control.TableView;
import persistencia.CuidadoDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {

    @FXML
    private TableColumn<?, ?> colunaID;
    @FXML
    private TableColumn<?, ?> colunaNome;
    @FXML
    private TableColumn<?, ?> colunaProfissao;
    @FXML
    private TableColumn<?, ?> colunaSalario;
    @FXML
    private TableColumn<?, ?> colunaTelefone;
    @FXML
    private TableView<Cuidador> tableViewCuidadores;

    Conexao conexao = new Conexao();
    CuidadoDAO cuidadorDAO = new CuidadoDAO(conexao.getConexao());
    public void initialize() {
        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        colunaID.setCellValueFactory(new PropertyValueFactory<>("idCuidador"));
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaProfissao.setCellValueFactory(new PropertyValueFactory<>("profissao"));
        colunaSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));
        colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        TableColumn<Cuidador, Void> colunaBotoes = new TableColumn<>("");
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
                HBox.setMargin(deleteButton, new Insets(2, 2, 0, 3));
                HBox.setMargin(editButton, new Insets(2, 3, 0, 2));

                setGraphic(managebtn);
                setText(null);
            }

            private void deleteButton(MouseEvent event) throws SQLException {
                Cuidador cuidador = getTableView().getItems().get(getIndex());

                ObservableList<Cuidador> listaObservable = tableViewCuidadores.getItems();
                listaObservable.remove(cuidador);
                tableViewCuidadores.setItems(listaObservable);
                Conexao conexao = new Conexao();
                cuidadorDAO.excluirCuidador(cuidador.getIdCuidador());
                conexao.fecharConexao();
            }

            private void editButton(MouseEvent event) throws IOException {

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Cuidador cuidador = getTableView().getItems().get(getIndex());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("editFuncionario.fxml"));
                Parent root = loader.load();

                EditFuncionarioController editFuncionarioController = loader.getController();
                editFuncionarioController.editarFuncionario(cuidador);

                Scene editScene = new Scene(root);
                editScene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/criarDados.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(editScene);
                stage.setOnShown(e -> currentStage.close());
                stage.show();
            }
        });

        tableViewCuidadores.getColumns().add(colunaBotoes);
    }

    private void carregarDadosNaTableView() {
        try {
            List<Cuidador> listaCuidadores = cuidadorDAO.listarCuidadores();
            ObservableList<Cuidador> listaObservable = FXCollections.observableArrayList(listaCuidadores);
            tableViewCuidadores.setItems(listaObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void adicionarFuncionario(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addFuncionario.fxml"));
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
