package pageObjectModel;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.Waiter;

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
    WebElement dismissPopupButton;
    @Getter
    @FindBy (css = "button[aria-label = 'Open Sidenav']")
    WebElement sideNavButton;
    @Getter
    @FindBy (css = "a[aria-label = 'Open score-board']")
    WebElement scoreBoardButton;
    @Getter
    @FindBy (id = "searchQuery")
    WebElement searchBarIcon;
    @Getter
    @FindBy (id = "mat-input-0")
    WebElement searchInputField;
    @Getter
    @FindBy (id = "bonus_payload")
    WebElement bonusPayload;


    public HomePagePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Dismiss Welcome popup
    public void dismissPopup() {
        try {
            Waiter.waitUntilElementIsVisible(driver, dismissPopupButton, 5);
            dismissPopupButton.click();
        }
        catch (NoSuchElementException e) {
            System.out.println("There is no popup present!");
        }
    }
    // Open the login page
    public void openLoginPage(){
        dismissPopup();
        accountButton.click();
        Waiter.waitUntilElementIsClickable(driver, loginButton, 5);
        loginButton.click();
    }

    // Verify user is logged in by asserting the shopping cart is present
    public void verifyUserIsLoggedIn() {
        Waiter.waitUntilElementIsVisible(driver, shoppingCartButton, 2);
        assert shoppingCartButton.isDisplayed();
    }

    // Verify user is not logged in by asserting the shopping cart is not present
    public void verifyUserIsLoggedOut() {
        accountButton.click();
        Waiter.waitUntilElementIsInvisible(driver, shoppingCartButton, 5);
        assert !shoppingCartButton.isDisplayed();
    }

    // Navigate to the score board through the UI
    public void openScoreBoardPage() {
        sideNavButton.click();
        Waiter.waitUntilElementIsClickable(driver, scoreBoardButton, 2);
        scoreBoardButton.click();
        assert driver.getCurrentUrl().contains("score-board");
    }

    // Perform the search functionality with the given input string
    public void searchFunction(String searchInput) {
        searchBarIcon.click();
        searchInputField.sendKeys(searchInput);
        searchInputField.sendKeys(Keys.ENTER);
    }
}
