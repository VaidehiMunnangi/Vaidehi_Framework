package com.MercuryTravel.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	public static Properties readFromConfigFile(String filename) throws IOException {
		
		filename = filename.trim();
		FileInputStream reader = new FileInputStream(filename);
		Properties property = new Properties();
		property.load(reader);
		return property;
		
	}
}
