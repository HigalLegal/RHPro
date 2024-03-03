package com.rhpro.javafx.controllers;

import com.rhpro.dto.inputs.FuncionarioInput;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import lombok.Data;
import javafx.scene.control.Label;
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

    public void handleButtonConfirmar(){
        buttonConfirmClick = true;
        dialogStage.close();
    }

    public void handleButtonCancelar(){
        dialogStage.close();
    }

    public void setFuncionario(FuncionarioInput funcionario){
        this.funcionario = funcionario;
        this.textFuncionario.setText(funcionario.getNome());

    }

}
