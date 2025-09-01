package com.utils.wait;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.config.reader.ConfigReader;

public class WaitUtils {

	WebDriver driver;
	WebDriverWait wait;
	private static ConfigReader configReader = new ConfigReader();

	// Constructeur
	public WaitUtils(WebDriver driver) {
		this.driver = driver; // Initialisation du driver
		  this.wait = new WebDriverWait(driver, Duration.ofSeconds(configReader.getDurationTime())); // Timeout configuré
	}

	// Attendre qu'une alerte soit présente
	public void alertIsPresent() {
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void Textispresent(By locator, String Text) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, Text));
	}

	// Attendre qu'un élément soit cliquable
	public void elementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// Attendre qu'un élément soit sélectionné
	public void elementToBeSelected(By locator) {
		wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	// Attendre qu'un cadre soit disponible et y basculer
	public void frameToBeAvailableAndSwitchToIt(By frameLocator) {
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	// Attendre que l'élément soit invisible
	public void invisibilityOfTheElementLocated(By locator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	// Attendre qu'un élément avec un texte spécifique soit invisible
	public void invisibilityOfElementWithText(By locator, String text) {
		wait.until(ExpectedConditions.invisibilityOfElementWithText(locator, text));
	}

	// Attendre la présence de tous les éléments situés par un sélecteur
	public void presenceOfAllElementsLocatedBy(By locator) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	// Attendre la présence d'un élément dans le DOM
	public void presenceOfElementLocated(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// Attendre qu'un certain texte soit présent dans un élément situé
	public void textToBePresentInElementLocated(By locator, String text) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	// Attendre qu'une valeur de texte soit présente dans un élément
	public void textToBePresentInElementValue(By locator, String value) {
		wait.until(ExpectedConditions.textToBePresentInElementValue(locator, value));
	}

	// Attendre que le titre de la page soit égal à une valeur spécifique
	public void titleIs(String title) {
		wait.until(ExpectedConditions.titleIs(title));
	}

	// Attendre que le titre de la page contienne une valeur spécifique
	public void titleContains(String title) {
		wait.until(ExpectedConditions.titleContains(title));
	}

	// Attendre qu'un élément soit visible
	public void visibilityOf(By locator) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
	}

	// Attendre que tous les éléments soient visibles
	public void visibilityOfAllElements(List<WebElement> elements) {
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	// Attendre que tous les éléments situés par un sélecteur soient visibles
	public void visibilityOfAllElementsLocatedBy(By locator) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// Attendre qu'un élément soit visible
	public void visibilityOfElementLocated(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static ConfigReader getConfigReader() {
		return configReader;
	}

	public static void setConfigReader(ConfigReader configReader) {
		WaitUtils.configReader = configReader;
	}
}
