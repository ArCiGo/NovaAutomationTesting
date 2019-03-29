package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseApplicationPage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public HeaderSection header = new HeaderSection(driver);

    public boolean isLoaded() {
        boolean isLoaded = driver.getCurrentUrl().equals("http://automationpractice.com/index.php") ? true : false;

        return isLoaded;
    }

    public void goTo() {
        driver.navigate().to("http://automationpractice.com/");
    }
}
