package com.rhpro.javafx.util;

import javafx.scene.image.Image;
import lombok.Data;

@Data
public class Icon {
    public static Image Image(){
        return new Image(Icon.class.getResourceAsStream("/view/public/rh-scaled.png"));
    }
}
