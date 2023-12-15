package com.example.projetofinalpoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistencia.Conexao;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Conexao.conectar();
        Scene telaAbertura = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("OinkApp");
        stage.setScene(telaAbertura);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}