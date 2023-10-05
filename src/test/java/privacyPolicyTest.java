import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import pageObjectModel.LoginPO;
import setup.DriverManager;

public class privacyPolicyTest extends DriverManager {

    private HomePagePO homePagePO;
    private LoginPO loginPO;
    private WebDriver driver;


    // Method to set up driver and instantiate page object instances before every test
    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        loginPO = new LoginPO(driver);
    }


    // Navigate to the privacy policy page and assert it is open properly
    @Test
    public void privacyPolicyPageTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.dismissPopup();
        homePagePO.openLoginPage();
        loginPO.loginValidUser();
        homePagePO.verifyUserIsLoggedIn();
        homePagePO.openPrivacyPolicyPage();
        assert driver.getCurrentUrl().contains("/privacy-security/privacy-policy");
    }
}
