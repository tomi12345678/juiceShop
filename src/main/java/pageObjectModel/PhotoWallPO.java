package pageObjectModel;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.Waiter;

public class PhotoWallPO {

    private final WebDriver driver;

    @Getter
    @FindBy (xpath = "//img[contains(@src, '😼-#zatschi-#whoneedsfourlegs-1572600969477.jpg')]")
    WebElement catImage;
    @Getter
    @FindBy (xpath = "//img[contains(@src, '-%23zatschi-%23whoneedsfourlegs-')]")
    WebElement updatedCatImage;


    public PhotoWallPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Assert cat image is present
    public void catImagePresent() {
        Waiter.waitUntilElementIsVisible(driver, catImage, 3);
        assert catImage.isDisplayed();
    }

    // Fix the encoding of the src element of catImage (replace # with %23)
    public void updateCatImageSrc() {
        String src = catImage.getAttribute("src");
        String newSrc = src.replace("#", "%23");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('src', arguments[1]);", catImage, newSrc);
        Waiter.waitUntilElementIsVisible(driver, updatedCatImage, 3);
    }

    // Assert cat image is not displayed properly
    public void assertCatImageIsBroken() {
        Boolean picture = (Boolean) ((JavascriptExecutor)driver) .executeScript("return arguments[0].complete " +
                "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", catImage);
        assert !picture;
        System.out.println("Cat picture is not displayed properly, the element is broken!");
    }

    // Assert cat image is displayed properly
    public void assertCatImageIsDisplayedProperly() {
        Boolean picture = (Boolean) ((JavascriptExecutor)driver) .executeScript("return arguments[0].complete " +
                "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", updatedCatImage);
        assert picture;
        System.out.println("Cat picture is properly displayed.");

    }
}
