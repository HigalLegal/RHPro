package com.rhpro.javafx.controllers;

import com.rhpro.controllers.PontoController;
import com.rhpro.dto.inputs.PontoInput;
import com.rhpro.dto.outputs.PontoOutput;
import com.rhpro.javafx.util.AnchorPaneUtils;
import com.rhpro.javafx.util.Icon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

/**
 * FXML Controller class
 *
 * @author Is Whatever
 */
@Data
@Component
@FxmlView("/view/TelaPresenca.fxml")
public class TelaPresencaController implements Initializable {

    @Autowired
    private PontoController pontoController;

    @Autowired
    private FxWeaver fxWeaver;

    @FXML
    private AnchorPane menuPresenca;
    @FXML
    private SVGPath buttonHome;
    @FXML
    private SVGPath buttonEditar;
    @FXML
    private CheckBox checkBoxMes;
    @FXML
    private TextField textNameFunc;

    // TABELA
    @FXML
    private TableView<PontoOutput> tableViewPresenca;
    @FXML
    private TableColumn<PontoOutput, String> textNome;
    @FXML
    private TableColumn<PontoOutput, String> textHoraChegada;
    @FXML
    private TableColumn<PontoOutput, String> textHoraSaida;
    @FXML
    private TableColumn<PontoOutput, String> textData;
    @FXML
    private TableColumn<PontoOutput, String> textHoras;

    // Lista de todos os pontos
    private ObservableList<PontoOutput> observableListPontoOutputList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        carregarTableView();

        // Adicionando listener para a TextField de nome
        textNameFunc.textProperty().addListener((observable, oldValue, newValue) -> filtrarPorNome(newValue));
    }

    // Carregar valores para a tabela
    public void carregarTableView() {
        textNome.setCellValueFactory(new PropertyValueFactory<>("funcionario"));
        textHoraChegada.setCellValueFactory(new PropertyValueFactory<>("horaChegada"));
        textHoraSaida.setCellValueFactory(new PropertyValueFactory<>("horaSaida"));
        textData.setCellValueFactory(new PropertyValueFactory<>("data"));
        textHoras.setCellValueFactory(new PropertyValueFactory<>("horasTrabalhadas"));

        // Chamada do controlador com a Lista de todos os pontos feitos.
        List<PontoOutput> pontoOutputList = pontoController.todosOsPontos();

        if (pontoOutputList != null) {
            // Conversão para listaObservavel.
            observableListPontoOutputList = FXCollections.observableArrayList(pontoOutputList);

            // Setando os valores na tabela.
            tableViewPresenca.setItems(observableListPontoOutputList);
        }
    }

    // Método para filtrar os pontos pela data selecionada
//    private void filtrarPorData(LocalDate selectedDate) {
//        ObservableList<PontoOutput> filteredList = FXCollections.observableArrayList();
//        for (PontoOutput ponto : observableListPontoOutputList) {
//            if (selectedDate != null) {
//                if (checkBoxMes.isSelected()) {
//                    if (ponto.getData().getYear() == selectedDate.getYear() && ponto.getData().getMonth() == selectedDate.getMonth()) {
//                        filteredList.add(ponto);
//                    }
//                } else {
//                    if (ponto.getData().isEqual(selectedDate)) {
//                        filteredList.add(ponto);
//                    }
//                }
//            }
//        }
//        tableViewPresenca.setItems(filteredList);
//    }

    // Método para filtrar os pontos pelo nome digitado
    private void filtrarPorNome(String nome) {
        if (nome != null && observableListPontoOutputList !=null) {
            ObservableList<PontoOutput> filteredList = FXCollections.observableArrayList();
            for (PontoOutput ponto : observableListPontoOutputList) {
                if (ponto.getFuncionario().toLowerCase().contains(nome.toLowerCase())) {
                    filteredList.add(ponto);
                }
            }
            if(!filteredList.isEmpty()){
                tableViewPresenca.setItems(filteredList);
            }
        }
    }

    // Ação para o Botão de Voltar.
    @FXML
    public void handleHome() throws IOException {
//        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
        AnchorPane a = (AnchorPane) fxWeaver.loadView(MenuPrincipalController.class);
        AnchorPaneUtils.setAnchorPane(a);
        menuPresenca.getChildren().setAll(a);
    }

    // Ação para o Botão de Criar um novo Ponto.
    @FXML
    public void handlePonto() throws IOException {
        PontoInput pontoInput = new PontoInput();
        boolean buttonConfirmClick = showFXMLCadastroPonto(pontoInput);
//        if (buttonConfirmClick){
//            pontoController.criar(pontoInput);
//        }

    }

    private boolean showFXMLCadastroPonto(PontoInput pontoInput) throws IOException {
        Parent root = fxWeaver.loadView(CadastroPontoController.class);

        CadastroPontoController controller = fxWeaver.getBean(CadastroPontoController.class);
        controller.setPontoInput(pontoInput);

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastrar Ponto");
        dialogStage.getIcons().add(Icon.Image());
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

        return controller.isButtonConfirmClick();
    }
}
