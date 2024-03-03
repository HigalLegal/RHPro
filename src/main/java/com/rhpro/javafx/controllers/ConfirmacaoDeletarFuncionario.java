package com.rhpro.javafx.controllers;

import com.rhpro.dto.inputs.FuncionarioInput;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import lombok.Data;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

@Data
public class ConfirmacaoDeletarFuncionario implements Initializable {
    @FXML
    private Label textFuncionario;
    @FXML
    private SVGPath buttonConfirmar;
    @FXML
    private SVGPath buttonCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private FuncionarioInput funcionario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void buttonConfirmar(){
        buttonConfirmClick = true;
        dialogStage.close();
    }

    public void buttonCancelar(){
        dialogStage.close();
    }
}
