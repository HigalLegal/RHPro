package com.rhpro.javafx.controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextField;
import com.rhpro.dto.inputs.FuncionarioInput;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class CadastroFuncionarioController implements Initializable{

    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldSobrenome;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private DatePicker datePickerDataNasc;
    @FXML
    private TextField textFieldSalario;
    @FXML
    private SVGPath buttonConfirmar;
    @FXML
    private SVGPath buttonCancelar;
    @FXML
    private TextField textFieldIrf;

    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private FuncionarioInput funcionario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void handleButtonCofirm(){
        // Instanciando o Funcionario e enviado ao controlador
        FuncionarioInput funcionarioInput = FuncionarioInput.builder()
                .nome(textFieldNome.getText())
                .sobrenome(textFieldSobrenome.getText())
                .cpf(textFieldCpf.getText())
                .emailCorporativo(textFieldEmail.getText())
                .folhaDePagamentoID(null)
                .salarioHora(new BigDecimal(textFieldSalario.getText()))
                .dataDeNascimento(datePickerDataNasc.getValue())
                .porcentagemIRF(new BigDecimal(textFieldIrf.getText()))
                .build();

        buttonConfirmClick = true;
        dialogStage.close();

    }
    public void handleButtonCancel(){
        dialogStage.close();
    }
}
