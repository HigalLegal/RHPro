package com.rhpro.javafx.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.rhpro.controllers.FuncionarioController;
import com.rhpro.dto.inputs.FuncionarioInput;
import com.rhpro.dto.outputs.FolhaDePagamentoOutput;
import com.rhpro.dto.outputs.FuncionarioOutputAll;
import com.rhpro.dto.outputs.FuncionarioOutputOne;
import com.rhpro.entities.Funcionario;
import com.rhpro.javafx.util.AnchorPaneUtils;
import com.rhpro.javafx.util.Icon;
import jakarta.annotation.Nullable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
@FxmlView("/view/TelaFuncionarioLista.fxml")
public class TelaFuncionarioListaController implements Initializable {

    @Autowired
    private FuncionarioController funcionarioController;

    @Autowired
    private FxWeaver fxWeaver;

    @FXML
    private TableView<FuncionarioOutputAll> viewFuncionario;
    @FXML
    private TableColumn<FuncionarioInput, String> viewFuncionarioNome;
    @FXML
    private SVGPath buttonAdcionar;
    @FXML
    private SVGPath buttonEditar;
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
    @FXML
    private TextField searchFuncionario;

    // Filtro
    ObservableList<FuncionarioOutputAll> observableListFuncionarios;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TableColumn<FuncionarioOutputAll, Void> buttonColumn = new TableColumn<>("Ações");
        buttonColumn.setPrefWidth(100);

        buttonColumn.setCellFactory(param -> new TableCell<>() {
            private final Button excluirButton = new Button("Excluir");

            {

                excluirButton.setOnAction(event -> {
                    FuncionarioOutputAll funcionario = getTableView().getItems().get(getIndex());
                    excluirFuncionario(funcionario.getId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(new HBox(excluirButton));
                }
            }
        });

        // Carrega os valores na tabela
        carregarTableView();

        viewFuncionario.getColumns().add(buttonColumn);

        // Observador para linhas da tabela.
        viewFuncionario.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> selecionarItemTableViewFuncionario(newValue));

        // Observador da caixa de pesquisa.
        searchFuncionario.textProperty().addListener((observable, oldValue, newValue) -> filtrarPorNome(newValue));
    }

    private void selecionarItemTableViewFuncionario(FuncionarioOutputAll funcionarioOut) {
        if(funcionarioOut != null) {
            // Instanciando o controller de funcionario
//            FuncionarioController funcionarioController = new FuncionarioControllerImpl();
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

        // Substituir pela chamada do controlador reponsavel pela conexão com o BD
        List<FuncionarioOutputAll> funcionarios = funcionarioController.listarTodos();

        if(funcionarios != null) {
            // Conversão da lista para uma lista observavel
            observableListFuncionarios = FXCollections.observableArrayList(funcionarios);
            // Setando os campos usando a lista
            viewFuncionario.setItems(observableListFuncionarios);
        }
    }

    private void filtrarPorNome(String nome) {
        if (nome != null && observableListFuncionarios !=null) {
            ObservableList<FuncionarioOutputAll> filteredList = FXCollections.observableArrayList();
            for (FuncionarioOutputAll func : observableListFuncionarios) {
                if (func.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    filteredList.add(func);
                }
            }
            if(!filteredList.isEmpty()){
                viewFuncionario.setItems(filteredList);
            }
        }
    }

    @FXML
    public void handleHome() throws IOException {
//        AnchorPane a = (AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MenuPrincipal.fxml")));
        AnchorPane a = (AnchorPane) fxWeaver.loadView(MenuPrincipalController.class);
        // Ultilitario para dimensionar a tela // Funciona para Resposividade da tela.
        AnchorPaneUtils.setAnchorPane(a);
        menuFuncionario.getChildren().setAll(a);
    }

    @FXML
    public void hadleButtonAdicionar() throws IOException {
        FuncionarioInput funcionarioInput = new FuncionarioInput();
        boolean buttonConfirmClick = showFXMLAnchorPaneCadastrosClientesDialog(funcionarioInput, null);
        if (buttonConfirmClick) {
//            funcionarioController.criar(funcionarioInput);
        }
    }

    public void excluirFuncionario(Long id) {
        funcionarioController.deletar(id);
        carregarTableView();
    }





    @FXML
    public void handleButtonEditar() throws IOException {
        FuncionarioOutputAll funcionario = viewFuncionario.getSelectionModel().getSelectedItem();
        // Instanciando o controller de funcionario
        // Usando a lista de funcionarios pelo Id criei o objeto de um dos funcionarios.
        if (funcionario == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um funcionario na Tabela!");
            alert.show();
        } else {
            FuncionarioOutputOne funcionarioOutputOne = funcionarioController.retornarPorId(funcionario.getId());
            // Converter Output Input
            FuncionarioInput funcionarioInput = covertOutToInputFuncionario(funcionarioOutputOne);
            Long id = funcionarioOutputOne.getId();;
            funcionarioInput.setFolhaDePagamentoID(funcionarioOutputOne.getIdFolha());

            if (funcionarioInput != null) {
                boolean buttonCofirmClick = showFXMLAnchorPaneCadastrosClientesDialog(funcionarioInput, id);
                if (buttonCofirmClick) {
                    // Envio para Controlador o cadastro Cliente
//                    funcionarioController.atualizar(id, funcionarioInput);
                    carregarTableView();
                }
            }
        }

    }


    // Tela de Editar ou Adicionar Funcionario;
    private boolean showFXMLAnchorPaneCadastrosClientesDialog(FuncionarioInput funcionario,
                                                              @Nullable Long id) throws IOException {
        Parent root = fxWeaver.loadView(CadastroFuncionarioController.class);
        // Cast para o tipo correto se necessário (ex: AnchorPane)
        // AnchorPane root = (AnchorPane) fxWeaver.load("/view/CadastroFuncionario.fxml");

        // Crie e configure o Stage como você estava fazendo antes
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro Funcionario");
        dialogStage.getIcons().add(Icon.Image());
        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        // Setando o Cliente no Controller
        CadastroFuncionarioController controller = fxWeaver.getBean(CadastroFuncionarioController.class);
        controller.setDialogStage(dialogStage);
        controller.setFuncionario(funcionario, id);

        // Mostrar Dialog e esperar pela confirmação ou cancelação
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
