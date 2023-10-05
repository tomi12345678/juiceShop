import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.HomePagePO;
import pageObjectModel.RegistrationPO;
import setup.DriverManager;

public class repetitiveRegistrationTest extends DriverManager {

    private HomePagePO homePagePO;
    private RegistrationPO registrationPO;
    private WebDriver driver;


    // Method to set up driver and instantiate page object instances before every test
    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        registrationPO = new RegistrationPO(driver);
    }

    // Enter registration data, so that password and confirmed password are different and complete registration
    @Test
    public void passwordMissmatchTest() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        homePagePO.openLoginPage();
        registrationPO.enterRegistrationData();
        registrationPO.getRegisterEmailField().sendKeys(generatedString);
        registrationPO.getRegisterPasswordField().sendKeys("123");
        registrationPO.clickRegisterAndAssertRegistration();
        System.out.println("User is registered, even when password and confirmed password are mismatched!");
    }
}
