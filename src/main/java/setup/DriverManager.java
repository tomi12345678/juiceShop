package setup;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import java.util.Properties;

public class DriverManager {

    private static WebDriver driver;
    private static ConfigFileReader configFileReader;


    public  DriverManager() {
        configFileReader = new ConfigFileReader();
    }

    //Method to initialize driver and return it if it is not already present.
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    @AfterMethod
    //Method that shuts down browser. Called in the @AfterTest teardown methods, in test classes.
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    // Method that initializes the driver, and gets the browser from application.properties
    private static void initializeDriver() {
        Properties properties = configFileReader.readProperties();
        String browser = properties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().clearResolutionCache().setup();
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().clearResolutionCache().setup();
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().clearResolutionCache().setup();
            InternetExplorerOptions options = new InternetExplorerOptions();
            driver = new InternetExplorerDriver(options);
        } else {
            throw new IllegalArgumentException("Unknown browser detected. Supported browsers are: Chrome, Firefox, IE.");
        }
        driver.manage().window().maximize();
    }

    @BeforeSuite
    // Delete previous screenshots before starting the test suite
    public void suiteSetup() {
        ScreenshotManager.deletePreviousScreenshots();
    }
}

