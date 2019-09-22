package com.iniesta.pfx.gui;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import com.iniesta.pfx.gui.service.ServiceLoadFile;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class TableFile {

	@FXML
	private VBox parent;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private TextField queryTextfield;

	@FXML
	private ToolBar tb;

	@FXML
	private TableView<Row> data;

	private StructType schema;

	private String inputFile;

	@FXML
	void onQueryAction(ActionEvent event) {
		if (data.getColumns() != null) {
			data.getColumns().clear();
		}
		ServiceLoadFile slf = new ServiceLoadFile(inputFile, queryTextfield.getText());
		launchServiceLoadFile(slf);
	}

	public void initParam(String inputFile) {
		this.inputFile = inputFile;
		System.out.println("Initialize params....");
		ServiceLoadFile slf = new ServiceLoadFile(inputFile);
		launchServiceLoadFile(slf);
	}

	@FXML
	void initialize() {
		System.out.println("Default Initialize...");
	}

	private void launchServiceLoadFile(ServiceLoadFile slf) {
		slf.stateProperty().addListener((ChangeListener<State>) (observable, oldValue, newValue) -> {
			if (State.SUCCEEDED == newValue) {
				fillSchema(slf.getValue());
				fillTableColumns();
			}
		});
		data.itemsProperty().bind(slf.valueProperty());
		tb.disableProperty().bind(slf.runningProperty());
		data.disableProperty().bind(slf.runningProperty());
		progressBar.visibleProperty().bind(slf.runningProperty());
		slf.start();
	}

	private void fillTableColumns() {
		if (schema != null) {
			for (StructField field : schema.fields()) {
				addColumnToTable(field);
			}
		}

	}

	private void addColumnToTable(final StructField field) {
		TableColumn<Row, String> col = new TableColumn<>(field.name());
		col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Row, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Row, String> param) {
				String initialValue = "NULL";
				DataType dataType = field.dataType();
				if (dataType.equals(DataTypes.StringType)) {
					initialValue = param.getValue().getAs(field.name());
				} else if (dataType.equals(DataTypes.LongType)) {
					Long inner = param.getValue().getAs(field.name());
					initialValue = inner != null ? inner.toString() : "(NULL)";
				}
				return new SimpleStringProperty(initialValue);
			}
		});
		data.getColumns().add(col);
	}

	private void fillSchema(ObservableList<Row> value) {
		if (value != null && !value.isEmpty()) {
			Row firstRow = value.get(0);
			schema = firstRow.schema();
		}
	}

}
