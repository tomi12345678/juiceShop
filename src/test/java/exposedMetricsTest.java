import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import setup.DriverManager;


public class exposedMetricsTest extends DriverManager {

    private WebDriver driver;


    // Method to set up driver and instantiate page object instances before every test
    @BeforeMethod
    public void setUp() {
        driver = getDriver();
    }

    // Test is to open the metrics site, so the test is very simple
    @Test()
    public void findExposedMetricsTest() {
        driver.get("https://juice-shop.herokuapp.com/metrics");
        assert driver.getCurrentUrl().contains("metrics");
    }
}
