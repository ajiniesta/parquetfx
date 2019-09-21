package com.iniesta.pfx.file;

import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkReaderFile {

	private String master;

	public SparkReaderFile() {
		this.master = "local[2]"; // Get from config, to allow connect to distributed process
	}
	
	public List<Row> read(String inputFile) {
		return read(inputFile, null);
	}
	
	public List<Row> read(String inputFile, String query) {
		SparkSession spark = SparkSession
				  .builder()
				  .master(master)
				  .appName("Local spark reader files")
				  .getOrCreate();
		Dataset<Row> parquetFileDF = spark.read().parquet(inputFile);
		parquetFileDF.createOrReplaceTempView("inner");
		if(query!=null) {
			parquetFileDF = spark.sql(query);
		}
		List<Row> data = parquetFileDF.collectAsList();
		spark.close();
		return data;
	}
}
