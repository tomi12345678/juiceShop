package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import setup.DriverManager;
import setup.ScreenshotManager;

public class ScreenshotListener extends TestListenerAdapter {

    private WebDriver driver;


    // When test result is failure, capture
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        driver = DriverManager.getDriver();
        captureScreenshot(testName);
    }

    private void captureScreenshot(String testName) {
        if (driver != null) {
            try {
                ScreenshotManager.takeScreenshot(driver, testName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
