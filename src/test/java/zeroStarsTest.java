import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import pageObjectModel.ContactPO;
import setup.DriverManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class zeroStarsTest extends DriverManager {

    private HomePagePO homePagePO;
    private ContactPO contactPO;
    private WebDriver driver;


    // Method to set up driver and instantiate page object instances before every test
    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        contactPO = new ContactPO(driver);
    }

    // Test that you can post a request with 0-star rating and assert it has been posted
    @Test()
    public void findExposedMetricsTest() {
        driver.get("https://juice-shop.herokuapp.com");
        homePagePO.dismissPopup();
        homePagePO.openContactPage();
        contactPO.leaveComment("Terrible Product!!!");
        contactPO.leaveRating();
        contactPO.solveCaptcha();
        contactPO.interceptNetworkRequest(driver, "Feedbacks");
        contactPO.getSubmitButton().click();
        driver.navigate().to("https://juice-shop.herokuapp.com/api/Feedbacks/");
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = dateObj.format(formatter);
        assert contactPO.getApiFeedbacksResponses().getText().contains("Terrible Product!!! (anonymous)\",\"rating\":0," +
                "\"createdAt\":\"" + date);
        System.out.println("Assert passed, a 0-star rating has been successfully posted!");

    }
}
