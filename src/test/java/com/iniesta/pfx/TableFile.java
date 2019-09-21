package com.iniesta.pfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TableFile extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/TableFile.fxml"));
//		loader.setController(new ParquetFx());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setWidth(800);
		stage.setHeight(600);
		stage.setTitle("Table File");
//		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("fxml/images/icon.png")));
		stage.show();
	}
}
