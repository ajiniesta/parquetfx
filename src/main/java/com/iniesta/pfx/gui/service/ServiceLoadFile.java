package com.iniesta.pfx.gui.service;

import java.util.List;

import org.apache.spark.sql.Row;

import com.iniesta.pfx.file.SparkReaderFile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ServiceLoadFile extends Service<ObservableList<Row>> {

	private String inputFile;
	private String query;

	public ServiceLoadFile(String inputFile) {
		this.inputFile = inputFile;
	}
	
	public ServiceLoadFile(String inputFile, String query) {
		this.inputFile = inputFile;
		this.query = query;
	}
	
	@Override
	protected Task<ObservableList<Row>> createTask() {		
		return new Task<ObservableList<Row>>() {
			@Override
			protected ObservableList<Row> call() throws Exception {
				SparkReaderFile srf = new SparkReaderFile();
				List<Row> inputRows = srf.read(inputFile, query);
				return FXCollections.observableArrayList(inputRows);
			}
		};
	}
	
}
