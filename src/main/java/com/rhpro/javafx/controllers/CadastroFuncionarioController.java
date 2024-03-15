package com.rhpro.javafx.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.rhpro.controllers.FuncionarioController;
import jakarta.annotation.Nullable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import com.rhpro.dto.inputs.FuncionarioInput;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import lombok.Data;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * FXML Controller class
 *
 * @author Is Whatever
 */
@Data
@Component
@FxmlView("/view/CadastroFuncionario.fxml")
public class CadastroFuncionarioController implements Initializable {

    @Autowired
    private FuncionarioController funcionarioController;

    @Autowired
    private FxWeaver fxWeaver;

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
    @FXML
    private Label idFuncionario;
    @FXML
    private Label idFolha;

    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private FuncionarioInput funcionario;


    public void setFuncionario(FuncionarioInput funcionario, @Nullable Long id){
        // Set para preencher os campos caso for editar um funcionario existente.
        this.funcionario = funcionario;
        this.textFieldNome.setText(funcionario.getNome());
        this.textFieldSobrenome.setText(funcionario.getSobrenome());
        this.textFieldCpf.setText(funcionario.getCpf());
        this.textFieldEmail.setText(funcionario.getEmailCorporativo());
        this.textFieldIrf.setText(String.valueOf(funcionario.getPorcentagemIRF()));
        this.textFieldSalario.setText(String.valueOf(funcionario.getSalarioHora()));
        this.datePickerDataNasc.setValue(funcionario.getDataDeNascimento());
        this.idFuncionario.setText(longParaString(id));
        this.idFolha.setText(longParaString(funcionario.getFolhaDePagamentoID()));
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idFuncionario.setVisible(false);
        idFolha.setVisible(false);
    }

    public void handleButtonCofirm() throws IOException {
        // Validação
        if(validarDadosFuncionario()) {
            // Instanciando o Funcionario e enviado ao controlador
            FuncionarioInput funcionarioInput = FuncionarioInput.builder()
                    .nome(textFieldNome.getText())
                    .sobrenome(textFieldSobrenome.getText())
                    .cpf(textFieldCpf.getText())
                    .emailCorporativo(textFieldEmail.getText())
                    .folhaDePagamentoID(pegarIdFolha())
                    .salarioHora(new BigDecimal(textFieldSalario.getText()))
                    .dataDeNascimento(datePickerDataNasc.getValue())
                    .porcentagemIRF(new BigDecimal(textFieldIrf.getText()))
                    .build();
            this.atualizarOuCriar(funcionarioInput, pegarIdDaLabel());
            buttonConfirmClick = true;
            dialogStage.close();
        }
        TelaFuncionarioListaController telaLista = fxWeaver.getBean(TelaFuncionarioListaController.class);
        telaLista.carregarTableView();

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
        if(textFieldIrf.getText() == null || textFieldIrf.getText().isEmpty() || Objects.equals(textFieldIrf.getText(), "null")){
            errorMsg += "IRF Inválido!\n";
        }
        if(datePickerDataNasc.getValue() == null || datePickerDataNasc.getValue() == null){
            errorMsg += "Data de Nascimento Inválido!\n";
        }

        if(errorMsg.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmado");
            alert.setHeaderText("Ação feita com sucesso!");
            alert.setContentText("Ação realizada com sucesso");
            alert.show();
            return true;
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no Cadastro");
            alert.setHeaderText("Campos Invalidos!");
            alert.setContentText(errorMsg);
            alert.show();
            return false;
        }
    }

    private void atualizarOuCriar(FuncionarioInput funcionarioInput, Long id) {
        if(id == null) {
            funcionarioController.criar(funcionarioInput);
        } else {
            funcionarioController.atualizar(id, funcionarioInput);
        }
    }

    private String longParaString(Long valor) {
        if(valor == null) {
            return "";
        }
        return String.valueOf(valor);
    }

    private Long pegarIdFolha() {
        if(idFolha.getText() == null || idFolha.getText().isEmpty()) {
            return null;
        }
        return Long.parseLong(idFolha.getText());
    }

    private Long pegarIdDaLabel() {
        if(idFuncionario.getText() == null || idFuncionario.getText().isEmpty()) {
            return null;
        } else {
            return Long.parseLong(idFuncionario.getText());
        }
    }
}
