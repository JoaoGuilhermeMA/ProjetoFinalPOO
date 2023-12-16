package com.example.projetofinalpoo;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
public class VendasController {

    @FXML
    private TableColumn<?, ?> idAnimal;
    @FXML
    private TableColumn<?, ?> dataVenda;
    @FXML
    private TableColumn<?, ?> valorVenda;
    @FXML
    private TableColumn<?, ?> cpfComprador;
    @FXML
    private TableView<?> tabelaVendas;
    @FXML
    private TableColumn<?, ?> idVenda;
}
