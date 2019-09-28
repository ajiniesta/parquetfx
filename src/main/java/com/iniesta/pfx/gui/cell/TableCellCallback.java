package com.iniesta.pfx.gui.cell;

import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructField;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class TableCellCallback implements Callback<TableColumn.CellDataFeatures<Row, String>, ObservableValue<String>> {
	private final StructField field;
	private int index;

	public TableCellCallback(StructField field, int index) {
		this.field = field;
		this.index = index;
	}

	@Override
	public ObservableValue<String> call(CellDataFeatures<Row, String> param) {
		String initialValue = "NULL";
		
		Object object = param.getValue().get(index);
		initialValue = extract(object);
		return new SimpleStringProperty(initialValue);
	}

	private String extract(Object inner) {
		return inner != null ? inner.toString() : "(NULL)";
	}

}
