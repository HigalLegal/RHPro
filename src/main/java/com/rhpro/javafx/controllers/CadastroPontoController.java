package com.rhpro.javafx.controllers;

import com.rhpro.controllers.FuncionarioController;
import com.rhpro.controllers.PontoController;
import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.inputs.PontoInput;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.util.converter.LocalTimeStringConverter;
import lombok.Data;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author Is Whatever
 */

@Data
@Component
@FxmlView("/view/CadastroPonto.fxml")
public class CadastroPontoController implements Initializable {

    @Autowired
    private PontoController pontoController;

    @Autowired
    private FuncionarioController funcionarioController;

    @Autowired
    private FxWeaver fxWeaver;

    // Variaveis de controle.
    private Stage dialogStage;
    private boolean buttonConfirmClick = false;
    private PontoInput pontoInput;
    private ObservableList<FuncionarioOutputAll> observableListFuncionarios;
    private FuncionarioOutputAll selectFunc = new FuncionarioOutputAll();

    // FXML
    @FXML
    private AnchorPane panelCadastrarPonto;
    @FXML
    private AnchorPane panelPonto;
    @FXML
    private TextField searchFuncionario;
    @FXML
    private TableView<FuncionarioOutputAll> tableFuncionario;
    @FXML
    private TableColumn<FuncionarioInput, String> viewColumnNome;
    @FXML
    private SVGPath buttonConfirmar;
    @FXML
    private SVGPath buttonCancelar;
    @FXML
    private Spinner<LocalTime> spinnerHoraEntrada;
    @FXML
    private Spinner<LocalTime> spinnerHoraSaida;

    // Spinner
    LocalTime minTime = LocalTime.MIN;
    LocalTime maxTime = LocalTime.MAX;
    LocalTime nowTime = LocalTime.now();

    // Criando um SpinnerValueFactory com os valores mínimo, máximo e inicial
    SpinnerValueFactory<LocalTime> valueFactoryEntrada = new SpinnerValueFactory<>() {
        @Override
        public void decrement(int steps) {
            setValue(getValue().minusMinutes(steps));
        }
        @Override
        public void increment(int steps) {
            setValue(getValue().plusMinutes(steps));
        }
    };
    SpinnerValueFactory<LocalTime> valueFactorySaida = new SpinnerValueFactory<>() {
        @Override
        public void decrement(int steps) {
            setValue(getValue().minusMinutes(steps));
        }
        @Override
        public void increment(int steps) {
            setValue(getValue().plusMinutes(steps));
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Deixando A aba do cadastro invisivel até que o usuario clique em um funcionario na tabela.
        panelPonto.setVisible(false);

        // Conversor
        valueFactoryEntrada.setConverter(new LocalTimeStringConverter());
        valueFactorySaida.setConverter(new LocalTimeStringConverter());
        // Setando valor como hora atual
        valueFactoryEntrada.setValue(nowTime);
        valueFactorySaida.setValue(nowTime);
        // Passando os valores de fábrica para os Spinner
        spinnerHoraEntrada.setValueFactory(valueFactoryEntrada);
        spinnerHoraSaida.setValueFactory(valueFactorySaida);

        carregarTableView();
        
        // Observador da seleção de algum funcionario na lista.
        tableFuncionario.getSelectionModel().selectedItemProperty().addListener((observableValue, oldvalue, newvalue) -> selecionarFuncionario(newvalue));

        // Observador da pesquisa por nome.
        searchFuncionario.textProperty().addListener((observable, oldValue, newValue) -> filtrarPorNome(newValue));
    }

    private void selecionarFuncionario(FuncionarioOutputAll newvalue) {
        panelPonto.setVisible(true);
        selectFunc.setId(newvalue.getId());
    }

    private void carregarTableView() {
        viewColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        // Substituir pela chamada do controlador reponsavel pela conexão com o BD
        List<FuncionarioOutputAll> funcionarios = funcionarioController.listarTodos();

        if(funcionarios != null) {
            // Conversão da lista para uma lista observavel
            observableListFuncionarios = FXCollections.observableArrayList(funcionarios);
            // Setando os campos usando a lista
            tableFuncionario.setItems(observableListFuncionarios);
        }
    }
    private void filtrarPorNome(String nome) {
        if (nome != null && observableListFuncionarios !=null) {
            ObservableList<FuncionarioOutputAll> filteredList = FXCollections.observableArrayList();
            for (FuncionarioOutputAll funcionario : observableListFuncionarios) {
                if (funcionario.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    filteredList.add(funcionario);
                }
            }
            if(!filteredList.isEmpty()){
                tableFuncionario.setItems(filteredList);
            }
        }
    }

    public void handleButtonConfirmar(){
        if(spinnerHoraSaida.getValue().isBefore(spinnerHoraEntrada.getValue())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Cadastrar Ponto");
            alert.setHeaderText("Hora de Saida Inferior a de Entrada!");
            alert.show();
        } else if (spinnerHoraSaida.getValue().equals(spinnerHoraEntrada.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro ao Cadastrar Ponto");
            alert.setHeaderText("Hora de Saida Igual a de Entrada!");
            alert.show();
        }else
        {
            buttonConfirmClick = true;
            PontoInput ponto = PontoInput.builder()
                    .horaChegada(LocalDate.now().atTime(spinnerHoraEntrada.getValue()))
                    .horaSaida(LocalDate.now().atTime(spinnerHoraSaida.getValue()))
                    .funcionarioId(selectFunc.getId())
                    .build();

            this.pontoInput = ponto;

            pontoController.criar(ponto);

            dialogStage.close();
        }
        TelaPresencaController telaPresencaController = fxWeaver.getBean(TelaPresencaController.class);
        telaPresencaController.carregarTableView();
    }
    public void handleButtonCancelar(){
        dialogStage.close();
    }
}
