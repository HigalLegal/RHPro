package com.rhpro.javafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaPresencaController implements Initializable {

    @FXML
    private AnchorPane menuPresenca;
    @FXML
    private SVGPath buttonHome;
    @FXML
    private SVGPath buttonEditar;
    @FXML
    private DatePicker datePicker;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleHome() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
        menuPresenca.getChildren().setAll(a);
    }
}
