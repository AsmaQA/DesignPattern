package com.utils.screen.shot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.config.reader.ConfigReader;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;

public class ScreenShotUtils {
    private WebDriver driver;
    private ATUTestRecorder recorder;
    private String methodName; // Changer static en instance pour permettre plusieurs méthodes

    private ConfigReader configReader;

    public ScreenShotUtils(WebDriver driver) {
        this.driver = driver;
        this.configReader = new ConfigReader(); // Initialiser ConfigReader dans le constructeur
    }

    public String getVideoFileName() {
        return "Video_" + methodName + "_" + configReader.getActualDateTime();
    }

    public void startRecording() throws ATUTestRecorderException {
        recorder = new ATUTestRecorder(configReader.videoLocation(), getVideoFileName(), false);
        recorder.start();
    }

    public void setupBeforeMethod(ITestResult result) {
        this.methodName = result.getMethod().getMethodName();
        try {
            startRecording();
        } catch (ATUTestRecorderException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() throws ATUTestRecorderException {
        if (recorder != null) {
            recorder.stop();
        }
    }

    public String takeScreenshot(String imageName) throws IOException {
        String actualImageName = configReader.screenShotLocation() + "\\" + imageName + "-" + configReader.getActualDateTime() + ".png";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFileImg = new File(actualImageName);
        FileUtils.copyFile(sourceFile, destFileImg);
        return actualImageName;
    }
}
