package pageObjectModel;
import lombok.Getter;

import setup.ConfigFileReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Properties;
import setup.Waiter;

public class LoginPO {

    private final WebDriver driver;
    public ConfigFileReader configFileReader;

    @Getter
    @FindBy (id = "email")
    WebElement emailField;
    @Getter
    @FindBy (id = "password")
    WebElement passwordField;
    @Getter
    @FindBy (id = "loginButton")
    WebElement loginButtonConfirmation;


    public LoginPO(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
         configFileReader = new ConfigFileReader();
    }

    //Logs in user with valid credentials
    public void loginValidUser() {
        Properties properties = configFileReader.readProperties();
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        Waiter.waitUntilElementIsVisible(driver, emailField, 3);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButtonConfirmation.click();
    }
}
