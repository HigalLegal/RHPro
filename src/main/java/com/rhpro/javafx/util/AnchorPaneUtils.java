package com.rhpro.javafx.util;

import javafx.scene.layout.AnchorPane;

// CLASSE RESPONSAVEL PELO DIMENSIONAMENTO DA TELA.

public class AnchorPaneUtils {
    public static void setAnchorPane(AnchorPane a){
        // Definir as dimensões do AnchorPane com base no tamanho da janela
        AnchorPane.setTopAnchor(a, 0.0);
        AnchorPane.setRightAnchor(a, 0.0);
        AnchorPane.setBottomAnchor(a, 0.0);
        AnchorPane.setLeftAnchor(a, 0.0);
    }
}
