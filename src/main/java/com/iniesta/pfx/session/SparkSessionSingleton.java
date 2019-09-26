package com.iniesta.pfx.session;

import org.apache.spark.sql.SparkSession;

public class SparkSessionSingleton {

	private static SparkSessionSingleton instance;
	private String master;
	private SparkSession sparkSession;

	private SparkSessionSingleton() {
		this.master = "local[2]"; // Get from config, to allow connect to distributed process
		sparkSession = createSparkSession();
	}
	
	public synchronized static SparkSessionSingleton getInstance() {
		if(instance == null) {
			instance = new SparkSessionSingleton();
		}
		return instance;
	}
	
	private SparkSession createSparkSession() {
		SparkSession spark = SparkSession
				  .builder()
				  .master(master)
				  .appName("Local spark reader files")
				  .getOrCreate();
		return spark;
	}
	
	public SparkSession getSparkSession() {
		return sparkSession;
	}
}
