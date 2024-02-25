package com.rhpro.javafx.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import com.rhpro.javafx.util.AnchorPaneUtils;

public class MenuPrincipalController implements Initializable {

    @FXML
    private Button menuPrincipalFuncionarios;
    @FXML
    private Button menuPrincipalPresenca;
    @FXML
    private Button menuPrincipalPagamento;
    @FXML
    private Button menuPrincipalConfiguracoes;
    @FXML
    private AnchorPane menuPrincipal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML // Ação para tela Funcionario
    public void handleMenuFuncionario() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaFuncionarioLista.fxml"));
        // Ultilitario para dimensionar a tela // Funciona para Resposividade da tela.
        AnchorPaneUtils.setAnchorPane(a);
        menuPrincipal.getChildren().setAll(a);
    }

    @FXML // Ação para tela de Presenca
    public void handleMenuPresenca() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaPresenca.fxml"));
        // Ultilitario para dimensionar a tela // Funciona para Resposividade da tela.
        AnchorPaneUtils.setAnchorPane(a);
        menuPrincipal.getChildren().setAll(a);
    }

    @FXML // Ação para tela de Pagamento
    public void handleMenuPagamento() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/TelaPagamento.fxml"));
        // Ultilitario para dimensionar a tela // Funciona para Resposividade da tela.
        AnchorPaneUtils.setAnchorPane(a);
        menuPrincipal.getChildren().setAll(a);
    }

    @FXML // Ação para tela de Configuração
    public void handleMenuConfig() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MenuConfiguracao.fxml"));
        // Ultilitario para dimensionar a tela // Funciona para Resposividade da tela.
        AnchorPaneUtils.setAnchorPane(a);
        menuPrincipal.getChildren().setAll(a);
    }
}
