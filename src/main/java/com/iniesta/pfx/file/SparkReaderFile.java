package com.iniesta.pfx.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import com.iniesta.pfx.session.SparkSessionSingleton;

public class SparkReaderFile {

	public SparkReaderFile() {
	}

	public List<Row> read(File inputFile) {
		return read(inputFile, null);
	}

	public List<Row> read(File inputFile, String query) {
		Dataset<Row> parquetFileDF = null;
		List<Row> data = new ArrayList<>();
		SparkSession spark = SparkSessionSingleton.getInstance().getSparkSession();
		if(inputFile!=null && inputFile.exists()) {
			parquetFileDF = spark.read().parquet(inputFile.getAbsolutePath());
			parquetFileDF.createOrReplaceTempView(extractAlias(inputFile));
		}
		if (query != null && !query.isEmpty()) {
			parquetFileDF = spark.sql(query);
		}
		if(parquetFileDF!=null) {
			data = parquetFileDF.collectAsList();
		}
		return data;
	}

	public String extractAlias(File inputFile) {
		String name = inputFile.getName();
		String[] chunks = name.split("\\.");
		if (chunks.length > 0) {
			return chunks[0];
		} else {
			return "inner";
		}
	}

}
