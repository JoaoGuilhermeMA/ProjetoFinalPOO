package com.example.projetofinalpoo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Button btnEntrar;

    @FXML
    void onHelloButtonClick(ActionEvent event) {
        System.out.println("Botão Clicado");
    }

}