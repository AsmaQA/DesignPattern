package com.config.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class ConfigReader {
	
     Properties properties;
     
     
	// Constructeur pour charger le fichier properties
    public ConfigReader() {
        try (FileInputStream fis = new FileInputStream("C:/Users/sliti/eclipse-workspace/CoursSeleniumDesignPattern/src/test/resources/dataConfig/data.properties")) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            System.out.println("Impossible de charger le fichier properties : " + e.getMessage());
        }
    }
    
    
    public String getProperty(String key, String defaultValue) {
        // Retourne la propriété correspondante à la clé ou une valeur par défaut si la clé n'existe pas
        return properties.getProperty(key, defaultValue);
    }
    
    
    // Méthode pour obtenir la valeur d'une propriété
    public String getBrowserName() {
        return properties.getProperty("browser"); // "chrome" est la valeur par défaut si non spécifiée
    }
    
    
    public Boolean getBrowserMode() {
    	  String headlessValue = properties.getProperty("headless");
        return  Boolean.parseBoolean(headlessValue) ; // "chrome" est la valeur par défaut si non spécifiée
    }
    
    
    public long getDurationTime() {
        // Utiliser Long.parseLong pour retourner la durée comme un long
        return Integer.parseInt(properties.getProperty("durationmax")); // "3" est la valeur par défaut si non spécifiée
    }
    
    public String getActualDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(properties.getProperty("date"));
        return dateFormat.format(new Date());
    }
    
    public String getDocumentTitle() {
        return properties.getProperty("DocumentTitle");
    }
    public String reportName() {
        return properties.getProperty("ReportName");
    }
    
    public String videoLocation() {
        return properties.getProperty("VIDEO_LOCATION");
    }
    
    public String remplirUserName() {
        return properties.getProperty("usernameinvalid");
    }
    
    public String remplirPassword() {
        return properties.getProperty("passwordinvalid");
    }
    public String geterrormsg() {
        return properties.getProperty("errormsgLogin");
    }
	public String getUrl() {
		// TODO Auto-generated method stub
		return properties.getProperty("url");
	}
	
	public String screenShotLocation() {
		
		return properties.getProperty("SCREENSHOT_LOCATION");}
}
