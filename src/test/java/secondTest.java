import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import pageObjectModel.LoginPO;
import setup.DriverManager;

public class secondTest extends DriverManager {

    private HomePagePO homePagePO;
    private DriverManager driverManager;
    private LoginPO loginPO;
    private WebDriver driver;


    @BeforeMethod
    //Method to set up driver and instantiate page object instances before every test
    public void setUp() {
//        driverManager = new DriverManager();
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        loginPO = new LoginPO(driver);
    }

    @Test
    public void loginSQLInjectionTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.openLoginPage();
        loginPO.simpleSQLInjection();
        homePagePO.verifyUserIsLoggedIn();
    }

//    @AfterMethod
//    public void tearDown(){
//        quitDriver();
//    }
}
