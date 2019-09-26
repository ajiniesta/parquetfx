package com.iniesta.pfx;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import com.iniesta.pfx.file.SparkReaderFile;

public class SparkReaderFileTest {

	@Test
	public void extractAlias() {
		SparkReaderFile sparkReaderFile = new SparkReaderFile();
		String actual = sparkReaderFile.extractAlias(new File("/tmp/people.parquet"));
		assertEquals("people", actual);
	}
}
