package AutomationResources;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public WebDriver create(BrowserType browserType) throws Exception {
        switch (browserType){
            case Chrome:
                return getChromeDriver();
            default:
                throw new Exception("No such browser exists!");
        }
    }

    private WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", System.getenv("ChromeDriver"));

        ChromeDriver chromeDriver = new ChromeDriver();
        return chromeDriver;
    }
}