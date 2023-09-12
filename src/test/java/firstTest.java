import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import setup.DriverManager;
import pageObjectModel.HomePagePO;
import pageObjectModel.LoginPO;

public class firstTest extends DriverManager {

    private HomePagePO homePagePO;
    private DriverManager driverManager;
    private LoginPO loginPO;
    private WebDriver driver;


    @BeforeTest
    //Method to set up driver and instantiate page object instances before every test
    public void setUp() {
        driverManager = new DriverManager();
        driver = driverManager.getDriver();
        homePagePO = new HomePagePO(driver);
        loginPO = new LoginPO(driver);
    }

    @Test
    public void testA() throws InterruptedException {
//        reportingManager.createTest("testA");
        driver.get("https://juice-shop.herokuapp.com/#/");
        Thread.sleep(1000);
        homePagePO.openLoginPage();
        Thread.sleep(1000);
        loginPO.loginValidUser();
        Thread.sleep(3000);
//        Assert.assertEquals(5, 10);
//        reportingManager.logTestStatus("pass", "you did it");
    }




    @AfterTest
    public void tearDown(){
        driverManager.quitDriver();
    }
}
