import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import setup.DriverManager;
import pageObjectModel.HomePagePO;
import pageObjectModel.LoginPO;
import pageObjectModel.RegistrationPO;

public class loginTest extends DriverManager {

    private HomePagePO homePagePO;
    private LoginPO loginPO;
    private RegistrationPO registrationPO;
    private WebDriver driver;


    // Method to set up driver and instantiate page object instances before every test
    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        loginPO = new LoginPO(driver);
        registrationPO = new RegistrationPO(driver);
    }

    // Try and login as an existing user. As the site often deletes created user, if the user doesn't exist, register user
    @Test(priority = -1)
    public void loginUserTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.openLoginPage();
        try{
            loginPO.loginValidUser();
            homePagePO.verifyUserIsLoggedIn();
        }
        catch (TimeoutException e) {
            registrationPO.enterRegistrationData();
            registrationPO.clickRegisterAndAssertRegistration();
            loginPO.loginValidUser();
        }
        homePagePO.verifyUserIsLoggedIn();
    }

    // Test that the site is vulnerable to simple SQL injections
    @Test
    public void loginSQLInjectionTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.openLoginPage();
        loginPO.simpleSQLInjection();
        homePagePO.verifyUserIsLoggedIn();
    }
}
