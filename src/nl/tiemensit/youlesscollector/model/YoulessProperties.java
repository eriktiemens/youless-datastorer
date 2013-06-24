package nl.tiemensit.youlesscollector.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class YoulessProperties {
	private static YoulessProperties instance;
	
	private Properties properties;
	
	public static YoulessProperties getInstance() {
		if (instance == null) {
			instance = new YoulessProperties();
		}
		return instance;
	}
	
	private YoulessProperties() {
		try {
			properties = readProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Properties readProperties() throws Exception {
		File file = new File("./dataproperties.xml");
		InputStream stream = new FileInputStream(file); 
		properties = new Properties();
		properties.loadFromXML(stream);
		return properties;
    }
	 
	 public String getValueByKey(String key) {
		 return (String) properties.get(key);
	 }
	 
	 public boolean getBoolValueByKey(String key) {
		 return Boolean.valueOf(getBoolValueByKey(key));
	 }

}
