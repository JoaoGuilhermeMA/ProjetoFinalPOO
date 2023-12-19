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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import persistencia.Conexao;
import persistencia.PorcoDAO;
import dominio.Porco;

public class PorcosController {

    @FXML
    private TableView<Porco> tabelaPorcos;

    @FXML
    private TableColumn<Porco, Integer> idPorco;

    @FXML
    private TableColumn<Porco, Integer> idade;

    @FXML
    private TableColumn<Porco, String> vivedouro;

    @FXML
    private TableColumn<Porco, Double> peso;

    @FXML
    private TableColumn<Porco, String> vacinas;

    @FXML
    private TableColumn<Porco, String> raca;

    @FXML
    private TableColumn<Porco, String> cuidador;

    @FXML
    private TableColumn<Porco, String> sexo;

    Conexao conexao = new Conexao();
    PorcoDAO porcoDAO = new PorcoDAO(conexao.getConexao());

    public void initialize() {
        configurarTableView();
        carregarDadosNaTableView();
    }

    private void configurarTableView() {
        idPorco.setCellValueFactory(new PropertyValueFactory<>("idAnimal"));
        idade.setCellValueFactory(new PropertyValueFactory<>("idade"));
        vivedouro.setCellValueFactory(new PropertyValueFactory<>("vivedouro"));
        peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
        vacinas.setCellValueFactory(new PropertyValueFactory<>("medicacao"));
        raca.setCellValueFactory(new PropertyValueFactory<>("raca"));
        cuidador.setCellValueFactory(new PropertyValueFactory<>("cuidador"));
        sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        TableColumn<Porco, Void> colunaBotoes = new TableColumn<>("");
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
                Porco porco = getTableView().getItems().get(getIndex());

                ObservableList<Porco> listaObservable = tabelaPorcos.getItems();
                listaObservable.remove(porco);
                tabelaPorcos.setItems(listaObservable);
                Conexao conexao = new Conexao();
                porcoDAO.excluirPorco(porco.getIdAnimal());
                conexao.fecharConexao();
            }
            private void editButton(MouseEvent event) throws IOException {

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Porco porco = getTableView().getItems().get(getIndex());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("editPorco.fxml"));
                Parent root = loader.load();

                EditPorcoController editPorco = loader.getController();
                editPorco.editarPorco(porco);

                Scene editScene = new Scene(root);
                editScene.getStylesheets().add(getClass().getResource("/com/example/projetofinalpoo/criarDados.css").toExternalForm());
                Stage stage = new Stage();
                stage.setScene(editScene);
                stage.setOnShown(e -> currentStage.close());
                stage.show();
            }
        });

        tabelaPorcos.getColumns().add(colunaBotoes);
    }

    private void carregarDadosNaTableView() {
        try {
            List<Porco> listaPorcos = porcoDAO.listarPorcosAtivos();
            ObservableList<Porco> listaObservable = FXCollections.observableArrayList(listaPorcos);
            tabelaPorcos.setItems(listaObservable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void adicionarPorco(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("addPorco.fxml"));
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
