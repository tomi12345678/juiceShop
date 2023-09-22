package pageObjectModel;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.Waiter;

import java.util.List;
import java.util.NoSuchElementException;

public class HomePagePO {

    private final WebDriver driver;

    @Getter
    @FindBy (id = "navbarAccount")
    WebElement accountButton;
    @Getter
    @FindBy (css = "button[aria-label = 'Show the shopping cart']")
    WebElement shoppingCartButton;
    @Getter
    @FindBy (id = "navbarLoginButton")
    WebElement loginButton;
    @Getter
    @FindBy (css = "button[aria-label = 'Close Welcome Banner']")
    WebElement dismissPopup;


    public HomePagePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Open the login page
    public void openLoginPage(){
        try {
            Waiter.waitUntilElementIsVisible(driver, dismissPopup, 5);
            dismissPopup.click();
        }
        catch (NoSuchElementException e) {
            System.out.println("There is no popup present!");
        }
        accountButton.click();
        Waiter.waitUntilElementIsClickable(driver, loginButton, 5);
        loginButton.click();
    }

    public void verifyUserIsLoggedIn() {
        Waiter.waitUntilElementIsVisible(driver, accountButton, 5);
        accountButton.click();
        Waiter.waitUntilElementIsVisible(driver, shoppingCartButton, 5);
        assert shoppingCartButton.isDisplayed();
    }

    public void verifyUserIsLoggedOut() {
        accountButton.click();
        Waiter.waitUntilElementIsInvisible(driver, shoppingCartButton, 5);
        assert !shoppingCartButton.isDisplayed();
    }
}
