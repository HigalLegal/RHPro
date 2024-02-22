package com.rhpro.javafx.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.rhpro.dto.inputs.FuncionarioInput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;


/**
 * FXML Controller class
 *
 * @author Is Whatever
 */
public class TelaFuncionarioListaController implements Initializable {

    @FXML
    private TableView<FuncionarioInput> viewFuncionario;
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
    private Label tabelaFuncionarioIrf;
    @FXML
    private AnchorPane menuFuncionario;

    private List<FuncionarioInput> listFuncionarios;

    // CONSULTA NO BANCO NECESSARIA AQUI
        // select de funcionarios...
    List<FuncionarioInput> listaFuncionarios = new ArrayList<>();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        carregarTableView();
    }

    // Carregamento da parte View Nome e CPF
    public void carregarTableView(){
        viewFuncionarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        viewFuncionarioCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        
        // Substituir pela chamada do controlador reponsavel pela conexão com o BD
        
        // Conversão da lista para uma lista observavel
        ObservableList<FuncionarioInput> observableListFuncionarios = FXCollections.observableArrayList(listaFuncionarios);
        // Setando os campos usando a lista
        viewFuncionario.setItems(observableListFuncionarios);
        System.out.println(observableListFuncionarios);
    }

    public void handleHome() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/MenuPrincipal.fxml")));
        menuFuncionario.getChildren().setAll(a);
    }
    
}
