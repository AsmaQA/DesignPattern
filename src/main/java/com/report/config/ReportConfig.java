package com.report.config;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.config.reader.ConfigReader;
import com.utils.screen.shot.ScreenShotUtils;

public class ReportConfig {
	private ExtentReports extent;
	private ExtentSparkReporter spark;
	private WebDriver driver;
	ConfigReader configReader = new ConfigReader();
	ScreenShotUtils capture = new ScreenShotUtils(driver);
	ITestResult result;
    private static com.aventstack.extentreports.ExtentTest test;

	public ReportConfig(WebDriver driver) throws IOException {
		this.driver=driver;
		this.capture=new ScreenShotUtils(driver);
		
		String reportName = "target/Spark/SparkReport_" + configReader.getActualDateTime() + ".html";
		spark = new ExtentSparkReporter(reportName);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle(configReader.getDocumentTitle());
		spark.config().setReportName(configReader.reportName());
		spark.config().setTimeStampFormat(configReader.getActualDateTime());

		extent = new ExtentReports();
		extent.attachReporter(spark);
		
	}

	public ExtentReports getExtent() {
		return extent;
	}
	
	public ExtentTest logTestName(String methodName) {
	    // Initialiser 'test' avec le nom de la méthode
	    test = extent.createTest(methodName);
	    return test;
	}

	public ExtentTest logTestName() {
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		return extent.createTest(methodName);
		 
	}
	public Status afterMethod(ITestResult result) throws IOException {
	    // Assurez-vous que 'test' est bien initialisé
	    if (test == null) {
	        logTestName(result.getMethod().getMethodName());
	    }

	    // Prendre la capture d'écran et l'ajouter au rapport
	    test.addScreenCaptureFromPath(capture.takeScreenshot(result.getMethod().getMethodName()));

	    // Log de la vidéo ajoutée au rapport
	    test.log(Status.INFO, "Vidéo ajoutée au rapport : <a href='" + configReader.videoLocation() + "'>Cliquez ici pour voir la vidéo</a>");

	    // Vérification du statut du test et retour du statut
	    if (result.getStatus() == ITestResult.SUCCESS) {
	        test.log(Status.PASS, "Test passed.");
	        return Status.PASS; // Retourne le statut de succès
	    } else if (result.getStatus() == ITestResult.FAILURE) {
	        test.log(Status.FAIL, "Test failed: " + result.getThrowable().getMessage());
	        return Status.FAIL; // Retourne le statut d'échec
	    } else {
	        test.log(Status.SKIP, "Test skipped.");
	        return Status.SKIP; // Retourne le statut de saut
	    }
	}

	public void flush() {
		extent.flush();
	}

}
