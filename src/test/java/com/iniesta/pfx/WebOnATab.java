package com.iniesta.pfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebOnATab extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		TabPane tabpane = new TabPane(); 
		Tab t1 = new Tab("uno", new Label("Hello..."));
		WebView web = new WebView();
		web.getEngine().load("http://www.google.es");
		Tab t2 = new Tab("web", web);
		tabpane.getTabs().addAll(t2);
		Scene scene = new Scene(tabpane, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}
}
