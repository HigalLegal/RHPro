package com.rhpro.javafx.javafx.configs;

import static com.rhpro.javafx.MainFX.StageReadyEvent;

import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;

public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
    }
}
