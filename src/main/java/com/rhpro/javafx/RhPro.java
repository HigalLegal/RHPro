package com.rhpro.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class RhPro extends Application {

    public static void main(String[] args) {
        launch(args);
        System.out.println("TESTE");
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafx/view/MenuPrincipal.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("RHPro");
        stage.setResizable(false);
        stage.show();

    }
}
