import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import setup.DriverManager;
import pageObjectModel.HomePagePO;
import pageObjectModel.LoginPO;


public class loginTest extends DriverManager {

    private HomePagePO homePagePO;
    private LoginPO loginPO;
    private WebDriver driver;


    @BeforeMethod
    //Method to set up driver and instantiate page object instances before every test
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        loginPO = new LoginPO(driver);
    }

    @Test
    public void loginUserTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.openLoginPage();
        loginPO.loginValidUser();
        homePagePO.verifyUserIsLoggedIn();
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
