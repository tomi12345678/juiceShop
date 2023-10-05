import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import setup.DriverManager;

public class errorHandlingTest extends DriverManager {

    private HomePagePO homePagePO;
    private WebDriver driver;


    // Method to set up driver and instantiate page object instances before every test
    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        RestAssured.baseURI = "https://juice-shop.herokuapp.com";
    }

    // Send a GET request for a non-existing link and assert it is not handled
    @Test()
    public void reachUnhandledErrorTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.dismissPopup();
        Response response = RestAssured.get("/rest/43y0071 HTTP/1.1");
        System.out.println(response.getStatusLine());
        assert response.getStatusLine().contains("500 Internal Server Error");
    }
}
