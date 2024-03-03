package com.rhpro.javafx.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.rhpro.controllers.FuncionarioController;
import com.rhpro.controllers.impl.FuncionarioControllerImpl;
import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;
import com.rhpro.entities.Funcionario;
import com.rhpro.javafx.util.AnchorPaneUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * FXML Controller class
 *
 * @author Is Whatever
 */
@Data
@ComponentScan(basePackages = {"com.rhpro.javafx"})

public class TelaFuncionarioListaController implements Initializable {

    @FXML
    private TableView<FuncionarioOutputAll> viewFuncionario;
    @FXML
    private TableColumn<FuncionarioInput, String> viewFuncionarioNome;
    @FXML
    private TableColumn<FuncionarioInput, String> viewFuncionarioCpf;
    @FXML
    private SVGPath buttonAdcionar;
    @FXML
    private SVGPath buttonEditar;
    @FXML
    private SVGPath buttonExcluir;
    @FXML
    private SVGPath buttonHome;
    @FXML
    private Label tabelaFuncionarioCodigo;
    @FXML
    private Label tabelaFuncionarioNome;
    @FXML
    private Label tabelaFuncionarioCpf;
    @FXML
    private Label tabelaFuncionarioSobreNome;
    @FXML
    private Label tabelaFuncionarioEmail;
    @FXML
    private Label tabelaFuncionarioDataNascimento;
    @FXML
    private Label tabelaFuncionarioSalario;
    @FXML
    private AnchorPane menuFuncionario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        carregarTableView();

        viewFuncionario.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> selecionarItemTableViewFuncionario(newValue));
    }

    private void selecionarItemTableViewFuncionario(FuncionarioOutputAll funcionarioOut) {
        if(funcionarioOut != null) {
            // Instanciando o controller de funcionario
            FuncionarioController funcionarioController = new FuncionarioControllerImpl();
            // Usando a lista de funcionarios pelo Id criei o objeto de um dos funcionarios.
            FuncionarioOutputOne funcionarioOutputOne = funcionarioController.retornarPorId(funcionarioOut.getId());
            // Agora só alterar os campos do Grid do funcionario usando get dos campos.
            tabelaFuncionarioCodigo.setText(String.valueOf(funcionarioOutputOne.getId()));
            tabelaFuncionarioNome.setText(String.valueOf(funcionarioOutputOne.getNome()));
            tabelaFuncionarioCpf.setText(String.valueOf(funcionarioOutputOne.getCpf()));
            tabelaFuncionarioSobreNome.setText(String.valueOf(funcionarioOutputOne.getSobrenome()));
            tabelaFuncionarioEmail.setText(String.valueOf(funcionarioOutputOne.getEmailCorporativo()));
            tabelaFuncionarioDataNascimento.setText(String.valueOf(funcionarioOutputOne.getDataDeNascimento()));
            tabelaFuncionarioSalario.setText(String.valueOf(funcionarioOutputOne.getSalarioHora()));
        }
    }

    // Carregamento da parte View Nome e CPF
    public void carregarTableView() {
        viewFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        viewFuncionarioCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        // Substituir pela chamada do controlador reponsavel pela conexão com o BD
        FuncionarioController funcionarioController = new FuncionarioControllerImpl(); // Supondo que você tenha uma implementação dessa interface
        List<FuncionarioOutputAll> funcionarios = funcionarioController.listarTodos();

        // Conversão da lista para uma lista observavel
        ObservableList<FuncionarioOutputAll> observableListFuncionarios = FXCollections.observableArrayList(funcionarios);
        // Setando os campos usando a lista
        viewFuncionario.setItems(observableListFuncionarios);
    }

    @FXML
    public void handleHome() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MenuPrincipal.fxml")));
        // Ultilitario para dimensionar a tela // Funciona para Resposividade da tela.
        AnchorPaneUtils.setAnchorPane(a);
        menuFuncionario.getChildren().setAll(a);
    }

    @FXML
    public void hadleButtonAdicionar() throws IOException {
        FuncionarioInput funcionarioInput = new FuncionarioInput();
        boolean buttonConfirmClick = showFXMLAnchorPaneCadastrosClientesDialog(funcionarioInput);
        if (buttonConfirmClick) {
            FuncionarioController funcionarioController = new FuncionarioControllerImpl();
            funcionarioController.criar(funcionarioInput);
        }
    }

    @FXML
    public void handleButtonExcluir() throws IOException {
        FuncionarioOutputAll funcionario = viewFuncionario.getSelectionModel().getSelectedItem();
        // Instanciando o controller de funcionario
        FuncionarioController funcionarioController = new FuncionarioControllerImpl();
        // Usando a lista de funcionarios pelo Id criei o objeto de um dos funcionarios.
        if (funcionario == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        } else {
            FuncionarioOutputOne funcionarioOutputOne = funcionarioController.retornarPorId(funcionario.getId());
            // Converter Output Input
            FuncionarioInput funcionarioInput = covertOutToInputFuncionario(funcionarioOutputOne);

            if (funcionarioInput != null) {
                boolean buttonCofirmClick = confirmarDeletarFuncionario(funcionarioInput);
                if (buttonCofirmClick) {
                    // Envio para Controlador o cadastro Cliente
                    funcionarioController.deletar(funcionarioOutputOne.getId());
                    carregarTableView();
                }
            }
        }
    }

    @FXML
    public void handleButtonEditar() throws IOException {
        FuncionarioOutputAll funcionario = viewFuncionario.getSelectionModel().getSelectedItem();
        // Instanciando o controller de funcionario
        FuncionarioController funcionarioController = new FuncionarioControllerImpl();
        // Usando a lista de funcionarios pelo Id criei o objeto de um dos funcionarios.
        if (funcionario == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        } else {
            FuncionarioOutputOne funcionarioOutputOne = funcionarioController.retornarPorId(funcionario.getId());
            // Converter Output Input
            FuncionarioInput funcionarioInput = covertOutToInputFuncionario(funcionarioOutputOne);

            if (funcionarioInput != null) {
                boolean buttonCofirmClick = showFXMLAnchorPaneCadastrosClientesDialog(funcionarioInput);
                if (buttonCofirmClick) {
                    // Envio para Controlador o cadastro Cliente
                    funcionarioController.criar(funcionarioInput);
                    carregarTableView();
                }
            }
        }

    }


    // Tela de Editar ou Adicionar Funcionario;
    private boolean showFXMLAnchorPaneCadastrosClientesDialog(FuncionarioInput funcionario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(CadastroFuncionarioController.class.getResource("/view/CadastroFuncionario.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Stage do Dialogo.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro Funcionario");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando o Cliente no Controller.
        CadastroFuncionarioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);
        System.out.println("Funcionario foi setado" + funcionario);

        // Mostrar Dialog e esperar pela confirmação ou cancelação.
        dialogStage.showAndWait();

        return controller.isButtonConfirmClick();

    }

    private boolean confirmarDeletarFuncionario(FuncionarioInput funcionario) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ConfirmacaoDeletarFuncionario.class.getResource("/view/ConfirmacaoDeletarFuncionario.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Stage do Dialogo.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Confirmação");
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        // Deixando a telinha sem a barra
        dialogStage.initStyle(StageStyle.UNDECORATED);

        // Setando o Cliente no Controller.
        ConfirmacaoDeletarFuncionario controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario);

        // Mostrar Dialog e esperar pela confirmação ou cancelação.
        dialogStage.showAndWait();

        return controller.isButtonConfirmClick();
    }

    private FuncionarioInput covertOutToInputFuncionario(FuncionarioOutputOne funcionarioOutputOne) {

        return FuncionarioInput.builder().nome(funcionarioOutputOne.getNome()).sobrenome(funcionarioOutputOne.getSobrenome()).cpf(funcionarioOutputOne.getCpf()).emailCorporativo(funcionarioOutputOne.getEmailCorporativo()).dataDeNascimento(LocalDate.now()) // FALTA FAZER A CONVERSÃO
                .salarioHora(BigDecimal.valueOf(20.0)) // FALTA FAZER A CONVERSÃO
                .build();
    }
}
