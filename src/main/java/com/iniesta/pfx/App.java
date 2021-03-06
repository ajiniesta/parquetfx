package com.iniesta.pfx;

import com.iniesta.pfx.gui.ParquetFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Parquetfx.fxml"));
//		loader.setController(new ParquetFx());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setWidth(800);
		stage.setHeight(600);
		stage.setTitle("ParquetFx");
//		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("fxml/images/icon.png")));
		stage.show();
	}
}
