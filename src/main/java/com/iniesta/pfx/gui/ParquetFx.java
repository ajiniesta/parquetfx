package com.iniesta.pfx.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ParquetFx {

	private static int newCounter = 1;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TabPane tabPane;

    @FXML
    void initialize() {
    }
    
    @FXML
    void openFileAction(ActionEvent event) {
    	FileChooser fc = new FileChooser();
    	fc.setTitle("Open parquet file");
    	Stage stage = new Stage();
    	File file = fc.showOpenDialog(stage);
    	
    	openTableFile(file.getName(), file.getAbsolutePath());
    	
    }

	private void openTableFile(String tabName, String path) {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/TableFile.fxml"));
    	try {
			Parent load = loader.load();
			addToTabPane(tabName, (VBox)load);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	TableFile controller = loader.<TableFile>getController();
    	controller.initParam(path);
	}

	private void addToTabPane(String name, VBox load) {
		Tab tab = new Tab(name);
		AnchorPane anchor = new AnchorPane();
//		anchor.setMinHeight(AnchorPane.USE_PREF_SIZE);
//		anchor.setMinWidth(AnchorPane.USE_PREF_SIZE);
//		anchor.setMaxHeight(AnchorPane.USE_PREF_SIZE);
//		anchor.setMaxWidth(AnchorPane.USE_PREF_SIZE);
//		anchor.setPrefHeight(AnchorPane.USE_PREF_SIZE);
//		anchor.setPrefWidth(AnchorPane.USE_PREF_SIZE);
//		anchor.getChildren().add(load);
//		load.setMinHeight(AnchorPane.USE_PREF_SIZE);
//		load.setMinWidth(AnchorPane.USE_PREF_SIZE);
//		load.setMaxHeight(AnchorPane.USE_PREF_SIZE);
//		load.setMaxWidth(AnchorPane.USE_PREF_SIZE);
//		load.setPrefHeight(AnchorPane.USE_PREF_SIZE);
//		load.setPrefWidth(AnchorPane.USE_PREF_SIZE);
		AnchorPane.setBottomAnchor(load, 0.0);
		AnchorPane.setTopAnchor(load, 0.0);
		AnchorPane.setLeftAnchor(load, 0.0);
		AnchorPane.setRightAnchor(load, 0.0);
		tab.setContent(load);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);
	}
	

    @FXML
    void onCloseAction(ActionEvent event) {
    	Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();
    	tabPane.getTabs().remove(selectedTab);
    }

    @FXML
    void onNewAction(ActionEvent event) {
    	String tabName = "New "+(newCounter++);
		openTableFile(tabName, null);
    }

    @FXML
    void onQuitAction(ActionEvent event) {
    	System.exit(0);
    }
    
}
