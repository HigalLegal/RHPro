package com.rhpro.javafx.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MenuConfiguracao implements Initializable {

    @FXML
    private AnchorPane menuConfiguracao;
    @FXML
    private SVGPath buttonHome;
    @FXML
    private Button buttonSobre;
    @FXML
    private Button buttonRestaurar;
    @FXML
    private ComboBox<String> boxLanguage;
    @FXML
    private DatePicker dateDataSys;
    @FXML
    private CheckBox checkPagamento;
    @FXML
    private CheckBox checkMoeda;
    @FXML
    private CheckBox checkEasyMode;

    public void handleHome() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
        menuConfiguracao.getChildren().setAll(a);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
