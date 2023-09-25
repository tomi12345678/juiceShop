package pageObjectModel;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.ConfigFileReader;
import setup.Waiter;

import java.util.Properties;

public class RegistrationPO {

    private final WebDriver driver;
    public ConfigFileReader configFileReader;
    public final LoginPO loginPO;

    @Getter
    @FindBy (id = "emailControl")
    WebElement registerEmailField;
    @Getter
    @FindBy (id = "passwordControl")
    WebElement registerPasswordField;
    @Getter
    @FindBy (id = "repeatPasswordControl")
    WebElement registerRepeatPasswordField;
    @Getter
    @FindBy (css = "mat-select[name = 'securityQuestion']")
    WebElement registerSecurityQuestionDropdown;
    @Getter
    @FindBy (css = "span[class='mat-option-text']")
    WebElement registerSecurityQuestionOption;
    @Getter
    @FindBy (id = "securityAnswerControl")
    WebElement registerAnwserField;
    @Getter
    @FindBy (id = "registerButton")
    WebElement registerButton;


    public RegistrationPO(WebDriver driver){
        this.driver = driver;
        this.loginPO = new LoginPO(driver);
        PageFactory.initElements(driver, this);
         configFileReader = new ConfigFileReader();
    }

    //Logs in user with valid credentials
    public void registerUser() {
        Waiter.waitUntilElementIsClickable(driver, loginPO.getRegistrationPageButton(), 2);
        loginPO.getRegistrationPageButton().click();
        Properties properties = configFileReader.readProperties();
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        String securityAnswer = properties.getProperty("securityAnwser");
        Waiter.waitUntilElementIsVisible(driver, registerEmailField, 3);
        registerEmailField.sendKeys(email);
        registerPasswordField.sendKeys(password);
        registerRepeatPasswordField.sendKeys(password);
        registerSecurityQuestionDropdown.click();
        Waiter.waitUntilElementIsClickable(driver, registerSecurityQuestionOption, 2);
        registerSecurityQuestionOption.click();
        registerAnwserField.sendKeys(securityAnswer);
        registerButton.click();
    }
}
