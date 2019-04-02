package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseApplicationPage {

    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BaseApplicationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
