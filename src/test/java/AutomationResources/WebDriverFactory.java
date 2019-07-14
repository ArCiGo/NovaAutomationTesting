package AutomationResources;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import utilities.Log;

import static utilities.OperatingSystem.getOperatingSystem;

public class WebDriverFactory {

    public WebDriver create(BrowserType browserType) throws Exception {
        switch (browserType) {
            case Chrome:
                return getChromeDriver();
            default:
                throw new Exception("No such browser exists!");
        }
    }

    private WebDriver getChromeDriver() {
        switch (getOperatingSystem()) {
            case WINDOWS:
                System.setProperty("webdriver.chrome.driver", System.getenv("ChromeDriver"));
                break;
            case MAC:
                System.setProperty("webdriver.chrome.driver", "/Users/arcigo/Downloads/chromedriver/chromedriver");
                break;
            default:
                Log.info("Unknown Operating System");
        }

        ChromeDriver chromeDriver = new ChromeDriver();
        return chromeDriver;
    }
}