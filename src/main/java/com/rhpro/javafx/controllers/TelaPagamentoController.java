package com.rhpro.javafx.controllers;

import com.rhpro.controllers.FolhaDePagamentoController;
import com.rhpro.dto.outputs.FolhaDePagamentoOutput;
import com.rhpro.javafx.util.AnchorPaneUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import lombok.Data;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

@Data
@Component
@FxmlView("/view/TelaPagamento.fxml")
public class TelaPagamentoController implements Initializable {

    @Autowired
    private FolhaDePagamentoController folhaDePagamentoController;

    @Autowired
    private FxWeaver fxWeaver;

    // FXML
    @FXML
    private AnchorPane menuPagamento;
    @FXML
    private SVGPath buttonBaixa;
    @FXML
    private SVGPath buttonHome;
    @FXML
    private SVGPath buttonEditar;
    @FXML
    private TextField searchFuncionario;
    @FXML
    private DatePicker dateData;
    @FXML
    private CheckBox checkBoxMes;

    // Tabela
    @FXML
    private TableView<FolhaDePagamentoOutput> tableViewFolhaPagamento;
    @FXML
    private TableColumn<FolhaDePagamentoOutput, String> textFuncionario;
    @FXML
    private TableColumn<FolhaDePagamentoOutput, String> textPorcentagemIrf;
    @FXML
    private TableColumn<FolhaDePagamentoOutput, String> textValorIrf;
//    @FXML
//    private TableColumn<FolhaDePagamentoOutput, String> textEmissao;
    @FXML
    private TableColumn<FolhaDePagamentoOutput, String> textSalarioLiguido;

    // Filtros
    private ObservableList<FolhaDePagamentoOutput> observableList;


    @FXML
    public void handleHome() throws IOException {
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
        AnchorPane a = (AnchorPane) fxWeaver.loadView(MenuPrincipalController.class);
        // Ultilitario para dimensionar a tela // Funciona para Resposividade da tela.
        AnchorPaneUtils.setAnchorPane(a);
        menuPagamento.getChildren().setAll(a);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarViewFolhaDePagamento();
        // Adicionando listener para o DatePicker
//        dateData.setOnAction(event -> filtrarPorData(dateData.getValue()));
        searchFuncionario.textProperty().addListener((observable, oldValue, newValue) -> filtrarPorNome(newValue));
    }

    private void carregarViewFolhaDePagamento() {
        textFuncionario.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        textPorcentagemIrf.setCellValueFactory(new PropertyValueFactory<>("porcentagemIRF"));
        textValorIrf.setCellValueFactory(new PropertyValueFactory<>("valorIRF"));
        textSalarioLiguido.setCellValueFactory(new PropertyValueFactory<>("salarioLiquido"));

//        FolhaDePagamentoController folhaDePagamentoController = new FolhaDePagamentoImpl();
        List<FolhaDePagamentoOutput> listFolhaPagamento = folhaDePagamentoController.listarTodos();

        if (listFolhaPagamento != null){
            // Filtro
            observableList = FXCollections.observableArrayList(listFolhaPagamento);
            tableViewFolhaPagamento.setItems(observableList);
        }
    }
    private void filtrarPorNome(String funcionario) {
        if (funcionario != null && observableList !=null) {
            ObservableList<FolhaDePagamentoOutput> filteredList = FXCollections.observableArrayList();
            for (FolhaDePagamentoOutput folha : observableList) {
                if (folha.getFuncionario().toLowerCase().contains(funcionario.toLowerCase())) {
                    filteredList.add(folha);
                }
            }
            if(!filteredList.isEmpty()){
                tableViewFolhaPagamento.setItems(filteredList);
            }
        }
    }
//    private void filtrarPorData(LocalDate selectedDate) {
//        ObservableList<FolhaDePagamentoOutput> filteredList = FXCollections.observableArrayList();
//        for (FolhaDePagamentoOutput folha : observableList) {
//            if (selectedDate != null) {
//                if (checkBoxMes.isSelected()) {
//                    if (folha.getEmissao().getYear() == selectedDate.getYear() && folha.getEmissao().getMonth() == selectedDate.getMonth()) {
//                        filteredList.add(folha);
//                    }
//                } else {
//                    if (folha.getEmissao().isEqual(selectedDate)) {
//                        filteredList.add(folha);
//                    }
//                }
//            }
//        }
//        tableViewFolhaPagamento.setItems(filteredList);
//    }
}
