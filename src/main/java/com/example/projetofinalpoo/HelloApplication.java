package com.example.projetofinalpoo;

import dominio.Medicacao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistencia.Conexao;
import persistencia.MedicacaoDAO;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("entrar.fxml"));
        Scene telaAbertura = new Scene(fxmlLoader.load(), 1000, 700);
        stage.setTitle("OinkApp");
        stage.setScene(telaAbertura);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}