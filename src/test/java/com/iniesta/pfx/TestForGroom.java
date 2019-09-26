package com.iniesta.pfx;

import java.util.List;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class TestForGroom {

	public static void main(String[] args) {
		SparkSession spark = SparkSession
				  .builder()
				  .master("local[2]")
				  .appName("Java Spark SQL basic example")
				  .config("spark.some.config.option", "some-value")
				  .getOrCreate();
		Dataset<Row> df = spark.read().json("src/test/resources/people.json");

//		df.show();
		
		
		Dataset<Row> parquetFileDF = spark.read().parquet("people.parquet");
//
//		// Parquet files can also be used to create a temporary view and then used in SQL statements
//		parquetFileDF.createOrReplaceTempView("parquetFile");
//		Dataset<Row> namesDF = spark.sql("SELECT name FROM parquetFile WHERE age BETWEEN 13 AND 19");
//		Dataset<String> namesDS = namesDF.map(
//		    (MapFunction<Row, String>) row -> "Name: " + row.getString(0),
//		    Encoders.STRING());
//		
		List<Row> data = parquetFileDF.collectAsList();
		System.out.println(data);
	}
}
