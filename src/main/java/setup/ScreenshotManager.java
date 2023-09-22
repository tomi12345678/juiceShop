package setup;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotManager {

    // Take a screenshot and save it
    public static void takeScreenshot(WebDriver driver, String testName) throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./reports/screenshots/" + testName + ".png"));
    }

    // Deletes all existing screenshots
    public static void deletePreviousScreenshots() {
        File screenshotDirectory = new File("./reports/screenshots");
        if (screenshotDirectory.exists() && screenshotDirectory.isDirectory()) {
            File[] screenshotFiles = screenshotDirectory.listFiles();
            if (screenshotFiles != null) {
                for (File file : screenshotFiles) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }
}
