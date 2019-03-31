package Pages;

import org.openqa.selenium.WebDriver;

public class BaseApplicationPage {

    protected WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BaseApplicationPage(WebDriver driver){
        this.driver = driver;
    }
}
