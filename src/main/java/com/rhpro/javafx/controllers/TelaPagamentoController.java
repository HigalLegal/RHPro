package com.rhpro.javafx.controllers;

import com.rhpro.javafx.util.AnchorPaneUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaPagamentoController implements Initializable {

    @FXML
    private AnchorPane menuPagamento;
    @FXML
    private SVGPath buttonBaixa;
    @FXML
    private SVGPath buttonHome;
    @FXML
    private SVGPath buttonEditar;

    @FXML
    public void handleHome() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
        // Ultilitario para dimensionar a tela // Funciona para Resposividade da tela.
        AnchorPaneUtils.setAnchorPane(a);
        menuPagamento.getChildren().setAll(a);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
