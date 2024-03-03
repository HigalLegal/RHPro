package com.rhpro.javafx.controllers;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Alert;
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


    public void setFuncionario(FuncionarioInput funcionario){
        // Set para preencher os campos caso for editar um funcionario existente.
        this.funcionario = funcionario;
        this.textFieldNome.setText(funcionario.getNome());
        this.textFieldSobrenome.setText(funcionario.getSobrenome());
        this.textFieldCpf.setText(funcionario.getCpf());
        this.textFieldEmail.setText(funcionario.getEmailCorporativo());
        this.textFieldIrf.setText(String.valueOf(funcionario.getPorcentagemIRF()));
        this.textFieldSalario.setText(String.valueOf(funcionario.getSalarioHora()));
        this.datePickerDataNasc.setValue(funcionario.getDataDeNascimento());
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void handleButtonCofirm(){
        // Validação
        if(validarDadosFuncionario()) {
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

    }
    public void handleButtonCancel(){
        dialogStage.close();
    }

    public boolean validarDadosFuncionario(){
        String errorMsg = "";

        if(textFieldNome.getText() == null || textFieldNome.getText().isEmpty()){
            errorMsg += "Nome Inválido!\n";
        }
        if(textFieldSobrenome.getText() == null || textFieldSobrenome.getText().isEmpty()){
            errorMsg += "Sobre Nome Inválido!\n";
        }
        if(textFieldCpf.getText() == null || textFieldCpf.getText().isEmpty()){
            errorMsg += "CPF Inválido!\n";
        }
        if(textFieldSalario.getText() == null || textFieldSalario.getText().isEmpty()){
            errorMsg += "Salario Inválido!\n";
        }
        if(textFieldEmail.getText() == null || textFieldEmail.getText().isEmpty()){
            errorMsg += "Email Inválido!\n";
        }
        if(textFieldIrf.getText() == null || textFieldIrf.getText().isEmpty()){
            errorMsg += "IRF Inválido!\n";
        }
        if(datePickerDataNasc.getValue() == null || datePickerDataNasc.getValue() == null){
            errorMsg += "Data de Nascimento Inválido!\n";
        }

        if(errorMsg.isEmpty())
            return true;
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no Cadastro");
            alert.setHeaderText("Campos Invalidos!");
            alert.setContentText(errorMsg);
            alert.show();
            return false;
        }
    }
}
