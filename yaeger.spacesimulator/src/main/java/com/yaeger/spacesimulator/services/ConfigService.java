package com.yaeger.spacesimulator.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * A {@code ConfigService} is used to retrieve values from the configuration
 * file.
 *
 */
public class ConfigService {

	/**
	 * Used to retrieve a value for the given key.
	 *
	 * @param key the key of the value to return as a {@code String}.
	 * @return the retrieved value as a {@code String}.
	 */
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
