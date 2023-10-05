package pageObjectModel;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v116.network.Network;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.Waiter;

import java.util.Optional;

public class ContactPO {

    private final WebDriver driver;

    @Getter
    @FindBy (id = "comment")
    WebElement commentInputField;
    @Getter
    @FindBy (id = "rating")
    WebElement ratingScale;
    @Getter
    @FindBy (id = "captcha")
    WebElement captchaQuestion;
    @Getter
    @FindBy (id = "captchaControl")
    WebElement capchaInputField;
    @Getter
    @FindBy (id = "submitButton")
    WebElement submitButton;
    @Getter
    @FindBy (css = "pre[style = 'word-wrap: break-word; white-space: pre-wrap;']")
    WebElement apiFeedbacksResponses;


    public ContactPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Leave a comment received as string
    public void leaveComment(String comment) {
        Waiter.waitUntilElementIsVisible(driver, commentInputField, 3);
        commentInputField.sendKeys(comment);
    }

    // Leave a rating (click on the rating scale)
    public void leaveRating() {
        Waiter.waitUntilElementIsVisible(driver, ratingScale, 3);
        ratingScale.click();
    }

    // Get the numbers of captcha and solve it
    public void solveCaptcha() {
        Waiter.waitUntilElementIsVisible(driver, captchaQuestion, 3);
        Waiter.pause(500);
        String captchaText = captchaQuestion.getText();
        String[] tokens = captchaText.split("(?=[+\\-*/])|(?<=[+\\-*/])");
        int result = Integer.parseInt(tokens[0]);
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            int num = Integer.parseInt(tokens[i + 1]);
            if (operator.equals("+")) {
                result += num;
            } else if (operator.equals("-")) {
                result -= num;
            } else if (operator.equals("*")) {
                result *= num;
            }
        }
        capchaInputField.sendKeys(Integer.toString(result));
        submitButton.click();
    }

    // Intercept network request, store it, format it and post a 0-star rating in the console
    public void interceptNetworkRequest(WebDriver driver, String containsUrl) {
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.requestWillBeSent(), requestSent -> {
            if (requestSent.getRequest().getUrl().contains(containsUrl)) {
                String postData = requestSent.getRequest().getPostData().toString()
                        .replace("[", "").replace("]", "");
                String requestUrl = requestSent.getRequest().getUrl();
                String requestMethod = requestSent.getRequest().getMethod()
                        .replace("'", "");
                String referPolicy = requestSent.getRequest().getReferrerPolicy().toString();
                String requestHeaders = "\"accept\": \"application/json, text/plain, */*\",\n" +
                        "    \"accept-language\": \"hr-HR,hr;q=0.9,en-US;q=0.8,en;q=0.7\",\n" +
                        "    \"content-type\": \"application/json\",\n" +
                        "    \"sec-ch-ua\": \"\\\"Google Chrome\\\";v=\\\"117\\\", \\\"Not;A=Brand\\\";v=\\\"8\\\", \\\"Chromium\\\";v=\\\"117\\\"\",\n" +
                        "    \"sec-ch-ua-mobile\": \"?0\",\n" +
                        "    \"sec-ch-ua-platform\": \"\\\"Windows\\\"\",\n" +
                        "    \"sec-fetch-dest\": \"empty\",\n" +
                        "    \"sec-fetch-mode\": \"cors\",\n" +
                        "    \"sec-fetch-site\": \"same-origin\"";
                String editedPostData = postData.replace("Optional","")
                        .replaceAll("\"rating\":\\d+", "\"rating\":0");

                String editedData = ("fetch(" +  "\"" + requestUrl + "\"" + ", {" + "\r\n" +
                        "\"headers\": " + "{" + "\r\n" + requestHeaders + "\r\n" + "}," + "\r\n" +
                        "\"ReferrerPolicy\":" +  "\"" +  referPolicy +  "\"" +  "," + "\r\n" +
                        "\"body\":" + "\r\n" + "JSON.stringify(" +editedPostData + ")"  +  "," + "\r\n" +
                        "\"method\":" +  "\"" +  requestMethod + "\"" + "}");

                // Send the edited data through the console
                String script = "console.log(" + editedData + "));";
                ((JavascriptExecutor) driver).executeScript(script);
            }
        });
    }
}