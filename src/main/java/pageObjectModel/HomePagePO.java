package pageObjectModel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePO {

//    private final WebDriver driver;

    @Getter
    @FindBy (id = "navbarAccount")
    WebElement accountButton;
    @Getter
    @FindBy (id = "navbarLoginButton")
    WebElement loginButton;
    @Getter
    @FindBy (css = "button[aria-label = 'Close Welcome Banner']")
    WebElement dismissPopup;


    //
    public HomePagePO(WebDriver driver) {
//        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Open the login page
    public void openLoginPage(){
        dismissPopup.click();
        accountButton.click();
        loginButton.click();
    }
}
