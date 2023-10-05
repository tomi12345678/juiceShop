import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import pageObjectModel.PhotoWallPO;
import setup.DriverManager;

public class missingEncodingTest extends DriverManager {

    private HomePagePO homePagePO;
    private WebDriver driver;
    private PhotoWallPO photoWallPO;


    // Method to set up driver and instantiate page object instances before every test
    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        photoWallPO = new PhotoWallPO(driver);
    }

    // Assert that a picture is not encoded properly, fix it and assert it is now being displayed properly
    @Test()
    public void missingPictureEncodingTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.openLoginPage();
        homePagePO.openPhotoWallPage();
        photoWallPO.catImagePresent();
        photoWallPO.assertCatImageIsBroken();
        photoWallPO.updateCatImageSrc();
        photoWallPO.assertCatImageIsDisplayedProperly();
    }
}
