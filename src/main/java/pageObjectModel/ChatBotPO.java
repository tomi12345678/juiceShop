package pageObjectModel;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.Waiter;

import java.util.List;


public class ChatBotPO {

    private final WebDriver driver;

    @Getter
    @FindBy(id = "message-input")
    WebElement chatInputField;
    @Getter
    @FindBy(css = "div[class = 'speech-bubble-left']")
    List<WebElement> speechBubbleLeft;


    public ChatBotPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Send a message to the chatbot
    public void sendMessageInChat(String message) {
        chatInputField.sendKeys(message);
        chatInputField.sendKeys(Keys.ENTER);
    }

    public String chatBotResponse(int i) {
        Waiter.pause(500);
        Waiter.waitUntilElementIsClickable(driver, speechBubbleLeft.get(i), 5);
        return speechBubbleLeft.get(i).getText();
    }
}