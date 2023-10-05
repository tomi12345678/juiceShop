import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import setup.DriverManager;

public class scoreBoardTest extends DriverManager {

    private HomePagePO homePagePO;
    private WebDriver driver;


    // Method to set up driver and instantiate page object instances before every test
    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
    }

    // Test navigating to the scoreboard page
    @Test
    public void scoreBoardNavigationTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.dismissPopup();
        homePagePO.openScoreBoardPage();
    }
}
