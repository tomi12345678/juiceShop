package setup;

import lombok.experimental.UtilityClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

@UtilityClass
public class Waiter {

    public static void waitUntilElementIsVisible (WebDriver webDriver, WebElement webElement, int timeoutInSeconds) {
        try {
            WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
            waiter.until((driver) -> ExpectedConditions.visibilityOf(webElement).apply(driver));
        }
        catch (TimeoutException e) {
            throw new TimeoutException( "Waiting for visibility of element was not successful." + webElement);
        }
    }

    public static void waitUntilElementIsInvisible (WebDriver webDriver, WebElement webElement, int timeoutInSeconds) {
        try {
            WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
            waiter.until((driver) -> ExpectedConditions.invisibilityOf(webElement).apply(driver));
        }
        catch (TimeoutException e) {
            throw new TimeoutException( "Waiting for invisibility of element was not successful." + webElement);
        }
    }

    public static void waitUntilElementIsClickable (WebDriver webDriver, WebElement webElement, int timeoutInSeconds) {
        try {
            WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
            waiter.until((driver) -> ExpectedConditions.elementToBeClickable(webElement).apply(driver));
        }
        catch (TimeoutException e) {
            throw new TimeoutException( "Waiting for element to be clickable was not successful." + webElement);
        }
    }

    public static void waitForAlertAndAccept (WebDriver webDriver, int timeoutInSeconds) {
        try {
            WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
            waiter.until(ExpectedConditions.alertIsPresent());
            Alert alert = webDriver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert Text: " + alertText);
            alert.accept();
        } catch (Exception e) {
            Assert.fail("No alert found.");
        }
    }
}