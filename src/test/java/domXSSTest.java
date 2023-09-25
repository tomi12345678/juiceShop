import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import setup.DriverManager;
import setup.Waiter;

public class domXSSTest extends DriverManager {

    private HomePagePO homePagePO;
    private WebDriver driver;


    @BeforeMethod
    //Method to set up driver and instantiate page object instances before every test
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
    }

    // Test that site is vulnerable to simple DOM XSS attacks
    @Test
    public void domXSSAttackTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.dismissPopup();
        homePagePO.searchFunction("<iframe src='javascript:alert(`xss`)'>");
        Waiter.waitForAlertAndAccept(driver, 5);
        System.out.println("Site is vulnerable to DOM XSS attacks.");
    }
}
