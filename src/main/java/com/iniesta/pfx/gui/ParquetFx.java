package com.iniesta.pfx.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
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
    	openTableFile(file.getName(), file);
    }

    @FXML
    void openDirectoryAction(ActionEvent event) {
    	DirectoryChooser fc = new DirectoryChooser();
    	fc.setTitle("Open parquet directory");
    	Stage stage = new Stage();
    	File file = fc.showDialog(stage);
    	openTableFile(file.getName(), file);
    }

    
	private void openTableFile(String tabName, File path) {
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
		load.setMinHeight(VBox.USE_COMPUTED_SIZE);
		load.setMinWidth(VBox.USE_COMPUTED_SIZE);
		load.setMaxHeight(VBox.USE_COMPUTED_SIZE);
		load.setMaxWidth(VBox.USE_COMPUTED_SIZE);
		load.setPrefHeight(VBox.USE_COMPUTED_SIZE);
		load.setPrefWidth(VBox.USE_COMPUTED_SIZE);
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
    
    @FXML
    void onAboutAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("About ParquetFX");
    	alert.setHeaderText("ParquetFX");
    	alert.setContentText("Read-Only viewer for parquet files.\nCreated by Antonio José Iniesta\nVisit github.com/ajiniesta/parquetfx\nDeveloped under Apache License 2.0");
    	alert.show();
    }
    
    @FXML
    void onMonitorAction(ActionEvent event) {
    	Tab tab = new Tab("Monitor");
    	WebView load = new WebView();
    	load.getEngine().load("http://localhost:4040");
		tab.setContent(load);
		tabPane.getTabs().add(tab);
		tabPane.getSelectionModel().select(tab);
    }
}
