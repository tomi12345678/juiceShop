package setup;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class DriverManager {

    private WebDriver driver;

    // Clear resolution cache and set up ChromeDriver
    public WebDriver getDriver() {
        WebDriverManager.chromedriver().clearResolutionCache().setup();
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();

        }
        return driver;
    }

    // Quit driver and clear driver cache after tearing down WebDriver
    public  void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        WebDriverManager.chromedriver().clearDriverCache();
    }
}

