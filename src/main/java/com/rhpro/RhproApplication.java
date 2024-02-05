package com.rhpro;

import com.rhpro.javafx.MainFX;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RhproApplication {

	public static void main(String[] args) {
		Application.launch(MainFX.class, args);
	}

}
