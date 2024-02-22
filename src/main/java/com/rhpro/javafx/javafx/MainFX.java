package com.rhpro.javafx.javafx;

import com.rhpro.RhproApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class MainFX extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(RhproApplication.class)
                .run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    // START DA CENA
    @Override
    public void start(Stage stage) throws IOException {
        // Evento do Higao
        applicationContext.publishEvent(new StageReadyEvent(stage));
        // Informando o xml a ser trocado na cena
        Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
        Scene scene = new Scene(root);

        // Cena e titulo
        stage.setScene(scene);
        stage.setTitle("RHPro");
        stage.setResizable(false);
        stage.show();
    }

    public static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }

    }
}