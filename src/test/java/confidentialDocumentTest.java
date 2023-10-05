import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import setup.DriverManager;
import setup.Waiter;

public class confidentialDocumentTest extends DriverManager {

    private HomePagePO homePagePO;
    private WebDriver driver;


    @BeforeMethod
    //Method to set up driver and instantiate page object instances before every test
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
    }

    // Test to access a file that is not supposed to be accessed without permissions
    @Test()
    public void accessConfidentialDocumentTest() {
        driver.get("https://juice-shop.herokuapp.com/#/about");
        homePagePO.dismissPopup();
        homePagePO.getLegalDownloadLink().click();
        String destinationURL = driver.getCurrentUrl();
        System.out.println(destinationURL);
        String newDestinationURL = destinationURL.replace("legal.md","");
        driver.get(newDestinationURL);
        driver.findElement(By.cssSelector("a[href='acquisitions.md']")).click();
        assert driver.getCurrentUrl().contains("acquisitions.md");
    }
}
