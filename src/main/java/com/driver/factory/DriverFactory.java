package com.driver.factory;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager; 

public class DriverFactory {
    
    // Le driver WebDriver utilisé pour les tests
    public WebDriver driver;

    // ThreadLocal stocke le WebDriver de manière isolée pour chaque thread (utile pour l'exécution de tests en parallèle)
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
 

    public WebDriver initializeBrowser(String browserName, boolean headlessMode) {
        // 1. Utilisation de ConfigReader pour lire les propriétés depuis le fichier data.properties
        

        // 2. Condition pour Chrome
        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions(); // Options pour configurer Chrome
            options.addArguments("--kiosk"); // Lance Chrome en plein écran
            options.addArguments("--disable-namespace-sandbox"); // Désactive le sandboxing
            options.addArguments("ignore-certificate-errors"); // Ignore les erreurs de certificats
            options.setExperimentalOption("excludeSwitches", Arrays.asList("test-type")); // Désactive certains avertissements
            options.addArguments("--safebrowsing-disable-download-protection"); // Désactive la protection de téléchargement
            options.addArguments("--no-sandbox"); // Nécessaire pour les environnements avec des ressources limitées
            options.addArguments("--disable-dev-shm-usage"); // Surmonte les problèmes de ressources sur certains systèmes
            if (headlessMode) { // Si le mode headless est activé
                options.addArguments("--headless"); // Active le mode headless
                options.addArguments("--window-size=1920,1080"); // Définit la taille de la fenêtre en mode headless
            }

            // 3. Configuration du driver pour Chrome avec WebDriverManager
            WebDriverManager.chromedriver().setup(); // Télécharge et configure la bonne version du driver Chrome
            tdriver.set(new ChromeDriver(options)); // Initialise le driver avec les options spécifiées

        // 4. Condition pour Firefox
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions(); // Options pour configurer Firefox
            if (headlessMode) { // Si le mode headless est activé
                options.addArguments("--headless"); // Active le mode headless pour Firefox
                options.addArguments("--window-size=1920,1080"); // Définit la taille de la fenêtre en mode headless
            }

            // 5. Configuration du driver pour Firefox avec WebDriverManager
            WebDriverManager.firefoxdriver().setup(); // Télécharge et configure la bonne version du driver Firefox
            tdriver.set(new FirefoxDriver(options)); // Initialise le driver avec les options spécifiées

        // 6. Condition pour Edge
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions option = new EdgeOptions(); // Options pour configurer Edge
            option.setAcceptInsecureCerts(true); // Accepte les certificats non sécurisés
            option.addArguments("ignore-certificate-errors"); // Ignore les erreurs de certificats
            option.addArguments("--remote-allow-origins=*"); // Autorise les origines distantes
            if (headlessMode) { // Si le mode headless est activé
                option.addArguments("--headless"); // Active le mode headless pour Edge
                option.addArguments("--window-size=1920,1080"); // Définit la taille de la fenêtre en mode headless
            }

            // 7. Configuration du driver pour Edge avec WebDriverManager
            WebDriverManager.edgedriver().setup(); // Télécharge et configure la bonne version du driver Edge
            tdriver.set(new EdgeDriver(option)); // Initialise le driver avec les options spécifiées

        // 8. Gestion du cas où le navigateur n'est pas défini
        } else {
            System.out.println("****** BROWSER IS NOT DEFINED");
        }

        // 9. Retourne l'instance WebDriver correspondant au navigateur initialisé
        return tdriver.get();
    }
}
