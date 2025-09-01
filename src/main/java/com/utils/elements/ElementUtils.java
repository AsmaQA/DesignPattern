package com.utils.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class ElementUtils   {

	WebDriver driver;
	
 public ElementUtils(WebDriver driver){	 
	  
	 this.driver = driver; 
	 
      }
 	
	public void sendKeys(By locator, String keysToSend) {
		WebElement element = driver.findElement(locator);
		element.clear(); 
		element.sendKeys(keysToSend);
		
	}

	// Vérifier si un élément est affiché
	public boolean isDisplayed(By locator) {
		return driver.findElement(locator).isDisplayed();
	}

	// Vérifier si un élément est sélectionné (pour les cases à cocher ou les
	// boutons radio)
	public boolean isSelected(By locator) {
		return driver.findElement(locator).isSelected();
	}

	// Soumettre un élément (par exemple, un formulaire)
	public void submit(By locator) {
		driver.findElement(locator).submit();
	}

	// Vérifier si un élément est activé
	public boolean isEnabled(By locator) {
		return driver.findElement(locator).isEnabled();
	}

	// Effacer le contenu d'un élément
	public void clear(By locator) {
		driver.findElement(locator).clear();
	}

	// Cliquer sur un élément
	public void click(By locator) {
		driver.findElement(locator).click();
	}

	// Obtenir la taille d'un élément
	public Dimension getSize(By locator) {
		return driver.findElement(locator).getSize();
	}

	
}
