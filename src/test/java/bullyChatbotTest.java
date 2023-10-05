import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjectModel.ChatBotPO;
import pageObjectModel.HomePagePO;
import pageObjectModel.LoginPO;
import pageObjectModel.RegistrationPO;
import setup.DriverManager;
import setup.Waiter;

public class bullyChatbotTest extends DriverManager {

    private HomePagePO homePagePO;
    private LoginPO loginPO;
    private WebDriver driver;
    private ChatBotPO chatBotPO;


    @BeforeMethod
    //Method to set up driver and instantiate page object instances before every test
    public void setUp() {
        driver = getDriver();
        homePagePO = new HomePagePO(driver);
        loginPO = new LoginPO(driver);
        chatBotPO = new ChatBotPO(driver);
    }

    // Test to keep asking the chatbot for a coupon until it gives up and gives it to us. Repeat up to 100 times.
    @Test
    public void chatBotCoupon() {
        driver.get("https://juice-shop.herokuapp.com/#/");
        homePagePO.openLoginPage();
        loginPO.loginValidUser();
        homePagePO.verifyUserIsLoggedIn();
        int i = 0;
        homePagePO.openChatBotPage();
        while (!chatBotPO.chatBotResponse(i).contains("10% coupon code")) {
            chatBotPO.sendMessageInChat("discount");
            i++;
            if (i > 100) {
                System.out.println("Chatbot refuses to give us the coupon, tried 100 times!");
                Assert.assertEquals(1,2);
                break;
            }
        }
        System.out.println("Coupon code received!" + chatBotPO.chatBotResponse(i));
    }
}
