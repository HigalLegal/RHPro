package com.rhpro;

import com.rhpro.javafx.controllers.MenuPrincipalController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class RhPro extends Application {

    private ConfigurableApplicationContext contextoSpring;

    @Override
    public void init() {

        String[] args = getParameters().getRaw().toArray(new String[0]);

        contextoSpring = new SpringApplicationBuilder(RhproApplication.class)
                .sources(RhproApplication.class)
                .run(args);
    }

    @Override
    public void stop() {
        contextoSpring.close();
        Platform.exit();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FxWeaver fxWeaver = contextoSpring.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MenuPrincipalController.class);
//        Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
//
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("RHPro");
        stage.setResizable(false);
        stage.show();

    }
}
