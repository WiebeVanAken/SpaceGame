package com.yaeger.spacesimulator.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {

	public static String getValue(String key) {
		File configFile = new File("src/main/resources/config.properties");
		String value = "";

		try {
			FileReader reader = new FileReader(configFile);
			Properties props = new Properties();
			props.load(reader);

			value = props.getProperty(key);

			reader.close();
		} catch (FileNotFoundException ex) {
			// log
		} catch (IOException ex) {
			// log
		}
		return value;
	}

}
